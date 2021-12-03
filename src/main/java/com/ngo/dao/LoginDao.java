package com.ngo.dao;

import com.ngo.model.Member;

public interface LoginDao {
	public Member login(Member user);
	public int doRegister(Member member);
}
