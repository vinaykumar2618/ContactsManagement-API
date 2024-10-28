package com.buildbot.tech.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.buildbot.tech.entity.User;
import com.buildbot.tech.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {

	 @Autowired
	    private UserRepository userRepository; // Use your user repository
	
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        return new org.springframework.security.core.userdetails.User(
	                user.getUsername(), user.getPassword(), getAuthorities(user));
	    }

	    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
	        return user.getRoles().stream()
	                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
	                .collect(Collectors.toList());
	    }

	
}