package com.company.employeemanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.employeemanagement.entity.MyUserDetails;
import com.company.employeemanagement.entity.User;
import com.company.employeemanagement.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = this.userRepository.findByUsername(username);
		//Logging current user credentials
		System.out.println("***************************************");
		System.out.println(optionalUser.get());
		if(optionalUser.isPresent()) {
			User user = optionalUser.get(); 
			return new MyUserDetails(user);
		}
		throw new UsernameNotFoundException("Could not found username");
	}

}
