package com.ngo.service;

import org.springframework.http.ResponseEntity;

import com.ngo.model.Member;
import com.ngo.model.Response;

public interface LoginService {
	public Member login(Member user);
	public ResponseEntity<Response> doRegister(Member patient);
}
