package com.ngo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ngo.dao.HomeDao;
import com.ngo.model.Donation;
import com.ngo.model.Participate;
import com.ngo.model.Refer;
import com.ngo.model.Response;
import com.ngo.service.HomeService;


@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	private HomeDao homeDao;
	
	@Override
	public ResponseEntity<Response> saveDonationData(Donation donation) {
		Response response = new Response();
		int resultsRow = homeDao.saveDonationData(donation);
		if(resultsRow > 0) {
			 response.setMessage(HttpStatus.OK.toString());
			 response.setDescription("Donation Data Saved Successfully !!!");
			 response.setStatusCode(200);
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
	
	@Override
	public ResponseEntity<Object> fetchDonationData() {
		List<Donation> donationList = homeDao.fetchDonationData();
		if(null == donationList) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(donationList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response> saveParticipateData(Participate participate) {
		Response response = new Response();
		int resultsRow = homeDao.saveParticipateData(participate);
		if(resultsRow > 0) {
			 response.setMessage(HttpStatus.OK.toString());
			 response.setDescription("Participate Data Saved Successfully !!!");
			 response.setStatusCode(200);
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}

	@Override
	public ResponseEntity<Object> fetchParticipateData() {
		List<Participate> participateList = homeDao.fetchParticipateData();
		if(null == participateList) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(participateList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response> saveReferData(Refer refer) {
		Response response = new Response();
		int resultsRow = homeDao.saveReferData(refer);
		if(resultsRow > 0) {
			 response.setMessage(HttpStatus.OK.toString());
			 response.setDescription("Refer Data Saved Successfully !!!");
			 response.setStatusCode(200);
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}

	@Override
	public ResponseEntity<Object> fetchReferData() {
		List<Refer> referList = homeDao.fetchReferData();
		if(null == referList) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(referList,HttpStatus.OK);
	}
}