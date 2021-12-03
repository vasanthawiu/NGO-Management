package com.ngo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ngo.model.Donation;
import com.ngo.model.Participate;
import com.ngo.model.Refer;
import com.ngo.model.Response;
import com.ngo.service.HomeService;

@RestController

public class HomeController {
	private HomeService homeService;
	
	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}
	
	@PostMapping("/donation")
	public ResponseEntity<Response> saveDonationData(@RequestBody Donation donation) {
		return homeService.saveDonationData(donation);
	}
	
	@GetMapping("/donation")
	public ResponseEntity<Object> fetchDonationDetails() {
		return homeService.fetchDonationData();
	}
	
	@PostMapping("/participate")
	public ResponseEntity<Response> saveParticipateData(@RequestBody Participate participate) {
		return homeService.saveParticipateData(participate);
	}
	
	@GetMapping("/participate")
	public ResponseEntity<Object> fetchParticipateDetails() {
		return homeService.fetchParticipateData();
	}
	
	@PostMapping("/refer")
	public ResponseEntity<Response> saveReferData(@RequestBody Refer refer) {
		return homeService.saveReferData(refer);
	}
	
	@GetMapping("/refer")
	public ResponseEntity<Object> fetchReferDetails() {
		return homeService.fetchReferData();
	}
}
