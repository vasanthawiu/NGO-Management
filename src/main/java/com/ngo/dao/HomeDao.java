package com.ngo.dao;

import java.util.List;

import com.ngo.model.Donation;
import com.ngo.model.Participate;
import com.ngo.model.Refer;


public interface HomeDao {
	public int saveDonationData(Donation donation);
	public List<Donation> fetchDonationData();
	public int saveParticipateData(Participate participate);
	public List<Participate> fetchParticipateData();
	public int saveReferData(Refer refer);
	public List<Refer> fetchReferData();
}
