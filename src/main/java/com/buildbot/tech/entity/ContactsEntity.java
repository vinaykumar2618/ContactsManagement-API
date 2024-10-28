package com.buildbot.tech.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactsEntity {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Integer contactId;
	@NotBlank(message = "First name cannot be blank")
	@Size(message = "Required alphabetic characters")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	@Size(message = "Required alphabetic characters")
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email should be valid")
	@Column(name = "EMAIL", unique = true)
	private String emailAddress;
	
	@Size(min = 10, max = 15)
	@Column(name = "PHONE_NO")
	private String phoneNumber;
	
	@NotBlank(message = "Optional")
	@Size(message = "Required alphabetic characters")
	@Column(name = "ADDRESS")
	private String address;

}
