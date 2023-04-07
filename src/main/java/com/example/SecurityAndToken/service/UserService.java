package com.example.SecurityAndToken.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SecurityAndToken.dto.GetUserRespon;
import com.example.SecurityAndToken.dto.RegisterRequest;
import com.example.SecurityAndToken.model.UserModel;
import com.example.SecurityAndToken.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String login(UserModel userModel) {
		UserModel uModel = userRepository.findByUsernameModel(userModel.getUsername());
//		System.out.println(passwordEncoder.matches(userModel.getPassword(), uModel.getPassword()));
		if(passwordEncoder.matches(userModel.getPassword(), uModel.getPassword())&&userModel!=null)
		{
			return jwtService.generateToken(userModel.getUsername());
		}
		
		return null;
	}
	
	public String register(RegisterRequest registerRequest) {
		if (userRepository.CheckUsername(registerRequest.getUsername())==0) {
			UserModel userModel = new UserModel();
			userModel.setDayUpdate(new Date());
			userModel.setEmail(registerRequest.getEmail());
			userModel.setUsername(registerRequest.getUsername());
			userModel.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			String token =jwtService.generateToken(registerRequest.getUsername());
			userRepository.save(userModel);
			return token;
		}
		return null;
	}
	
	public GetUserRespon getUser(String token) throws IOException {
		UserModel userModel =userRepository.findByUsernameModel(jwtService.extractUsername(token));
		if(userModel==null)
		{
			throw new IOException("KO TON TẠI USERMODEL");
		}
		GetUserRespon getUserRespon =new GetUserRespon();
		getUserRespon.setDayUpdate(userModel.getDayUpdate());
		getUserRespon.setEmail(userModel.getEmail());
		getUserRespon.setStd(userModel.getSdt());
		getUserRespon.setUsername(userModel.getUsername());
		return getUserRespon;
	}
}
