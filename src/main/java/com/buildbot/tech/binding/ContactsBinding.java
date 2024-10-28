package com.buildbot.tech.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactsBinding {
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String   phoneNumber;
	private String address;
}
