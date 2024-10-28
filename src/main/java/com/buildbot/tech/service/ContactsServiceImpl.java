package com.buildbot.tech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.buildbot.tech.binding.ContactsBinding;
import com.buildbot.tech.entity.ContactsEntity;
import com.buildbot.tech.exception.ResourceNotFoundException;
import com.buildbot.tech.repo.ContactRepository;

@Service
public class ContactsServiceImpl implements ContactsService {

	private ContactRepository repo;

	public ContactsServiceImpl(ContactRepository repo) {
		this.repo = repo;

	}

	@Override
	public ContactsBinding createContact(ContactsBinding contactInfo) {
		ContactsEntity ce = new ContactsEntity();
		BeanUtils.copyProperties(contactInfo, ce);
		ContactsEntity savedContct = repo.save(ce);
		ContactsBinding cb = new ContactsBinding();
		BeanUtils.copyProperties(savedContct, cb);

		return cb;

	}

	@Override
	public ContactsBinding mergeContacts(List<Integer> ids) {
		// Retrieve the contacts to merge
		List<ContactsEntity> contacts = repo.findAllById(ids);

		if (contacts.isEmpty()) {
			throw new ResourceNotFoundException("No contacts found for the provided IDs.");
		}

		ContactsEntity mergedContact = new ContactsEntity();

		mergedContact.setFirstName(
				contacts.stream().map(ContactsEntity::getFirstName).filter(Objects::nonNull).findFirst().orElse(null));

		mergedContact.setLastName(
				contacts.stream().map(ContactsEntity::getLastName).filter(Objects::nonNull).findFirst().orElse(null));

		// Use emailAddress from ContactsBinding
		mergedContact.setEmailAddress(contacts.stream().map(ContactsEntity::getEmailAddress).filter(Objects::nonNull)
				.findFirst().orElse(null));

		mergedContact.setPhoneNumber(contacts.stream().map(ContactsEntity::getPhoneNumber).filter(Objects::nonNull)
				.findFirst().orElse(null));

		// Combine addresses if they are not null and distinct
		String combinedAddress = contacts.stream().map(ContactsEntity::getAddress).filter(Objects::nonNull).distinct()
				.collect(Collectors.joining(", "));
		mergedContact.setAddress(combinedAddress.isEmpty() ? null : combinedAddress);

		// Save the merged contact
		ContactsEntity savedContact = repo.save(mergedContact);

		// Delete the old contacts
		repo.deleteAll(contacts);

		// Convert the saved entity to ContactsBinding before returning
		ContactsBinding mergedContactBinding = new ContactsBinding();
		BeanUtils.copyProperties(savedContact, mergedContactBinding);

		return mergedContactBinding;
	}

	@Override
	public List<ContactsBinding> getAllContacts() {
		List<ContactsEntity> list = repo.findAll();
		List<ContactsBinding> bindingList = new ArrayList<>();
		for (ContactsEntity ce : list) {
			ContactsBinding cb = new ContactsBinding();
			BeanUtils.copyProperties(ce, cb);
			bindingList.add(cb);
		}
		return bindingList;
	}

	@Override
	public String updateContact(Integer id, ContactsBinding contactInfo) {

		Optional<ContactsEntity> byId = repo.findById(id);
		ContactsEntity existingContact = byId.get();
		if (contactInfo.getFirstName() != null) {
			existingContact.setFirstName(contactInfo.getFirstName());
		}
		if (contactInfo.getLastName() != null) {
			existingContact.setLastName(contactInfo.getLastName());
		}
		if (contactInfo.getPhoneNumber() != null) {
			existingContact.setPhoneNumber(contactInfo.getPhoneNumber());
		}

		if (contactInfo.getEmailAddress() != null) {
			existingContact.setEmailAddress(contactInfo.getEmailAddress());
		}
		if (contactInfo.getAddress() != null) {
			existingContact.setAddress(contactInfo.getAddress());
		}

		repo.save(existingContact);
		return "Student updted successfully";

	}

	@Override
	public String deleteById(Integer id) {
		Optional<ContactsEntity> byId = repo.findById(id);
		try {
			if (repo.existsById(id)) {
				repo.deleteById(id);
				return "success";
			} else {
				return "Contact not found";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Contact deleted successfully";

	}

}
