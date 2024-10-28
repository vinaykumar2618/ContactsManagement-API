//package com.buildbot.tech.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserSetup implements CommandLineRunner {
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Override
//	public void run(String... args) throws Exception {
//		String rawPassword = "Admin123@";
//		String encodedPassword = passwordEncoder.encode(rawPassword);
//		System.out.println("Encoded Password: " + encodedPassword);
//	}
//}
