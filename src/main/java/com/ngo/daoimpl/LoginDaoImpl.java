package com.ngo.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ngo.dao.LoginDao;
import com.ngo.model.Member;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);
	
	@Override
	public Member login(Member user) {
		 String sql = "SELECT member_id FROM member WHERE email_id = ? AND member_password = ? ";
		 Member memberObj = null;
		 try {
			 memberObj =  jdbcTemplate.queryForObject(sql, 
				 new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member();
						member.setMemberId(String.valueOf(rs.getInt("member_id")));
						return member;
					}
				  }, 
				 new Object[] {user.getEmailId(),user.getPassword()});
		 }catch (Exception e) {
			 logger.error("Exception occured while checking member login : "
								+e.getMessage());
		 }
		return memberObj;
	}

	@Override
	public int doRegister(Member member) {
		String sql = "insert into member"
					 + " (member_name, email_id, member_password, "
					 + " member_address, contact_number )"
					 + " values(?,?,?,?,?)";
		int rows = 0;
		
		try {
		 rows = jdbcTemplate.update(sql, new Object[] {member.getMemberName(),
				 member.getEmailId(),member.getPassword(), member.getMemberAddress(),
				 member.getContactNum()});
		}catch (Exception e) {
			logger.error("Exception occured while saving member data : "+e.getMessage());
		}
	 return rows;
	}

}
