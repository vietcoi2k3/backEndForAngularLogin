package com.example.SecurityAndToken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.SecurityAndToken.dto.LoginRespon;
import com.example.SecurityAndToken.dto.RegisterRequest;
import com.example.SecurityAndToken.model.UserModel;
import com.example.SecurityAndToken.service.UserService;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public LoginRespon login(@RequestBody UserModel userModel) {
		String token = userService.login(userModel);
		LoginRespon loginRespon =new LoginRespon();
		loginRespon.setToken(token);
		loginRespon.setUsername(userModel.getUsername());
		return loginRespon;
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResponseEntity<LoginRespon> register(@RequestBody RegisterRequest registerRequest) {
	
		if(userService.register(registerRequest)==null) {
			System.out.println("ngu");
			
			return null;
		}
		
		LoginRespon loginRespon =new LoginRespon();
		loginRespon.setUsername(registerRequest.getUsername());
		loginRespon.setToken(userService.register(registerRequest));
		return ResponseEntity.ok(loginRespon);
		
		
	}
	
	
}
