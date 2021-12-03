package com.ngo.serviceimpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ngo.dao.LoginDao;
import com.ngo.model.Member;
import com.ngo.model.Response;
import com.ngo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	private LoginDao loginDao;
	
	public LoginServiceImpl(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	@Override
	public Member login(Member user) {
		Member userObj = null;
		
		if(!Optional.ofNullable(user.getEmailId()).get().equals("") ||
				!Optional.ofNullable(user.getPassword()).get().equals("")) {
			userObj = loginDao.login(user);
		}
		return userObj;
	}

	@Override
	public ResponseEntity<Response> doRegister(Member member) {
		Response response = new Response();
		int resultsRow = loginDao.doRegister(member);
		if(resultsRow > 0) {
			 response.setMessage(HttpStatus.OK.toString());
			 response.setDescription("Member Registered Successfully !!!");
			 response.setStatusCode(200);
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
}
