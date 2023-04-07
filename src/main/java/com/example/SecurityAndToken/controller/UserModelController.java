package com.example.SecurityAndToken.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.SecurityAndToken.dto.GetUserRespon;
import com.example.SecurityAndToken.service.JwtService;
import com.example.SecurityAndToken.service.UserService;


@RequestMapping(value = "/user")
@RestController
@CrossOrigin
public class UserModelController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String hellouser() {
		return "hello user";
	}
	
	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
	public GetUserRespon getUser(HttpServletRequest httpServletRequest) throws IOException {
		String token=	httpServletRequest.getHeader("Authorization").substring(7);
		
		return userService.getUser(token);
	}
	

}
