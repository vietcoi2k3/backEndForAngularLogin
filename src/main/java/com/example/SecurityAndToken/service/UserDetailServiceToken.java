package com.example.SecurityAndToken.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SecurityAndToken.model.UserModel;
import com.example.SecurityAndToken.repository.UserRepository;

@Service
public class UserDetailServiceToken implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("hello");
		UserModel userModel =new UserModel();
		userModel =userRepository.findByUsernameModel(username);
		System.out.println(userModel);
		if(userModel ==null)
		{
			throw new UsernameNotFoundException("????"+username);
		}
	     return userModel;
	}

}
