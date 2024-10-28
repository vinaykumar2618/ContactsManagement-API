package com.buildbot.tech.entity;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	 

	    private String username;

	    private String password;

	    @ElementCollection(fetch = FetchType.EAGER)
	    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	    @Column(name = "role")
	    private Set<String> roles;

}
