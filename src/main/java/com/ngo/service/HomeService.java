package com.ngo.service;

import org.springframework.http.ResponseEntity;

import com.ngo.model.Donation;
import com.ngo.model.Participate;
import com.ngo.model.Refer;
import com.ngo.model.Response;

public interface HomeService {
	public ResponseEntity<Response> saveDonationData(Donation donation);
	public ResponseEntity<Object> fetchDonationData();
	public ResponseEntity<Response> saveParticipateData(Participate participate);
	public ResponseEntity<Object> fetchParticipateData();
	public ResponseEntity<Response> saveReferData(Refer refer);
	public ResponseEntity<Object> fetchReferData();
}
