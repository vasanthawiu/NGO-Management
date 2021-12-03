package com.ngo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ngo.model.Member;
import com.ngo.model.Response;
import com.ngo.service.LoginService;

@Controller
public class LoginController {
	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping("/") 
	public String index(){
		return "redirect:/login";
	}
	
	@GetMapping("/login") 
	public String getLogin(){
		return "LoginReg";
	}
	
	@GetMapping("/home") 
	public String home(){
		return "NgoHome";
	}
	
	@PostMapping("/login")
	public ResponseEntity<Member> login(@RequestBody Member user) throws Exception {
		Member userObj = loginService.login(user);
		if(null == userObj) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Member>(userObj,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Response> doRegister(@RequestBody Member member) {
		return loginService.doRegister(member);
	}
}
