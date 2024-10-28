//package com.buildbot.tech.admin;
//
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.buildbot.tech.entity.User;
//import com.buildbot.tech.repo.UserRepository;
//
//@Component
//public class Admin implements CommandLineRunner {
//
//	 private final UserRepository userRepository;
//	    private final PasswordEncoder passwordEncoder;
//
//	    public Admin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//	        this.userRepository = userRepository;
//	        this.passwordEncoder = passwordEncoder;
//	    }
//
//	    @Override
//	    public void run(String... args) throws Exception {
//	        // Create a default user if none exists
//	        if (userRepository.count() == 0) {
//	            User user = new User();
//	            user.setUsername("vikki"); // Set your user name here
//	            user.setPassword(passwordEncoder.encode("Admin123@")); // Set your password here
//	            user.setRoles(List.of("ROLE_ADMIN")); // Assign roles
//	            userRepository.save(user);
//	        }
//	    }
//
//}
