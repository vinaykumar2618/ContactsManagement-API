package com.buildbot.tech.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildbot.tech.binding.ContactsBinding;
import com.buildbot.tech.service.ContactsServiceImpl;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class AdminController {

	private ContactsServiceImpl service;

	public AdminController(ContactsServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Create a new contact", response = ContactsBinding.class)
	public ResponseEntity<ContactsBinding> createContact(@RequestBody ContactsBinding contactInfo) {
		ContactsBinding cb = new ContactsBinding();
		BeanUtils.copyProperties(contactInfo, cb);
		ContactsBinding contact = service.createContact(contactInfo);
		return new ResponseEntity<ContactsBinding>(contact, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/getAllContacts")
	@ApiOperation(value = "Get all contacts", response = List.class)
	public ResponseEntity<List<ContactsBinding>> getAllContacts() {
		List<ContactsBinding> list = service.getAllContacts();
		if (list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Delete a contact by ID")
	public ResponseEntity<String> deleteStudentById(@PathVariable Integer id) {
		String byId = service.deleteById(id);
		if ("success".equals(byId)) {
			return new ResponseEntity<>("Contact deleted successfully", HttpStatus.OK);
		} else if ("Contact Not Found".equals(byId)) {
			return new ResponseEntity<>("No data Found", HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>("Failed to delete Contact", HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
