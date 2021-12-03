package com.ngo.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ngo.Utility;
import com.ngo.dao.HomeDao;
import com.ngo.model.Donation;
import com.ngo.model.Participate;
import com.ngo.model.Refer;


@Repository
public class HomeDaoImpl implements HomeDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Logger logger = LoggerFactory.getLogger(HomeDaoImpl.class);
	
	@Override
	public int saveDonationData(Donation donation) {
		String sql = "insert into donation"
				 + " (member_id, amount, donation_date, purpose)"
				 + " values(?,?,?,?)";
		int rowsCount = 0;
	
		try {
			rowsCount = jdbcTemplate.update(sql, new Object[] {Integer.valueOf(donation.getMemberId()),
					Float.valueOf(donation.getAmount()),
					Utility.getFormattedDate(donation.getDonationDate()),donation.getPurpose()});
		}catch (Exception e) {
			logger.error("Exception occured while saving donation data : "+e.getMessage());
		}
		return rowsCount;
	}
	
	@Override
	public List<Donation> fetchDonationData() {
		List<Donation> donationList = new ArrayList<Donation>();
		try {
			String sql = "SELECT donation_id as donationId, member_id as memberId, "
					+ "amount, donation_date as donationDate, purpose FROM donation";
			donationList = jdbcTemplate.query(sql, 
					new RowMapper<Donation>() {
				@Override
				public Donation mapRow(ResultSet rs, int rowNum) throws SQLException {
					Donation donation = new Donation();
					donation.setDonationId(String.valueOf(rs.getInt("donationId")));
					donation.setMemberId(String.valueOf(rs.getInt("memberId")));
					donation.setAmount(String.valueOf(rs.getFloat("amount")));
					donation.setDonationDate(Utility.getFormattedStringDate(rs.getDate("donationDate")));
					donation.setPurpose(rs.getString("purpose"));
					return donation;
				}
			  });
			
			return donationList;
		}catch (Exception e) {
			System.out.println("Exception occured while fetching donation list : "
					+e.getMessage());
			return null;
		}
	}

	@Override
	public int saveParticipateData(Participate participate) {
		String sql = "insert into participate"
				 + " (campaign_name, member_id, participate_date)"
				 + " values(?,?,?)";
		int rowsCount = 0;
	
		try {
			rowsCount = jdbcTemplate.update(sql, new Object[] {participate.getCampaignName(),
					Integer.valueOf(participate.getMemberId()),
					Utility.getFormattedDate(participate.getParticipateDate())});
		}catch (Exception e) {
			logger.error("Exception occured while saving participate data : "+e.getMessage());
		}
		return rowsCount;
	}

	@Override
	public List<Participate> fetchParticipateData() {
		List<Participate> participateList = new ArrayList<Participate>();
		try {
			String sql = "SELECT participate_id, campaign_name, "
					+ "member_id, participate_date FROM participate";
			participateList = jdbcTemplate.query(sql, 
					new RowMapper<Participate>() {
				@Override
				public Participate mapRow(ResultSet rs, int rowNum) throws SQLException {
					Participate participate = new Participate();
					participate.setParticipateId(String.valueOf(rs.getInt("participate_id")));
					participate.setCampaignName(rs.getString("campaign_name"));
					participate.setMemberId(String.valueOf(rs.getInt("member_Id")));
					participate.setParticipateDate(Utility.getFormattedStringDate(rs.getDate("participate_date")));
					return participate;
				}
			  });
			
			return participateList;
		}catch (Exception e) {
			System.out.println("Exception occured while fetching participate list : "
					+e.getMessage());
			return null;
		}
	}

	@Override
	public int saveReferData(Refer refer) {
		String sql = "insert into refer"
				 + " (member_id, name, contact_number, email_id, comments, status)"
				 + " values(?,?,?,?,?,?)";
		int rowsCount = 0;
	
		try {
			rowsCount = jdbcTemplate.update(sql, new Object[] {Integer.valueOf(refer.getMemberId()),
					refer.getMemberName(),refer.getContact(),refer.getMemberEmailId(),
					refer.getComment(),refer.getStatus()});
		}catch (Exception e) {
			logger.error("Exception occured while saving refer data : "+e.getMessage());
		}
		return rowsCount;
	}

	@Override
	public List<Refer> fetchReferData() {
		List<Refer> referList = new ArrayList<Refer>();
		try {
			String sql = "SELECT refer_id as referId,member_id as memberId, name as memberName, "
					+ "contact_number as contact, email_id as memberEmailId, comments as comment, "
					+ "status from refer";
			referList = jdbcTemplate.query(sql, 
					new BeanPropertyRowMapper<Refer>(Refer.class));
			
			return referList;
		}catch (Exception e) {
			System.out.println("Exception occured while fetching refer list : "
					+e.getMessage());
			return null;
		}
	}

}
