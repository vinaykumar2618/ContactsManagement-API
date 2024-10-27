package com.buildbot.tech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.buildbot.tech.binding.ContactsBinding;

@Service
public interface ContactsService {
	
	public ContactsBinding createContact (ContactsBinding contactInfo);
	public ContactsBinding mergeContacts(List<Integer> ids);
	public List<ContactsBinding> getAllContacts();
	
	public String updateContact(Integer id ,ContactsBinding contactInfo);
	
	public String deleteById(Integer id);
	
}
