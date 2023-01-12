package com.springboot.tutorial.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.tutorial.Exceptionhandling.RateException;
import com.springboot.tutorial.entity.Rate;
import com.springboot.tutorial.service.RateService;

@RestController
public class RateController {
	@Autowired
	private RateService rateService;
	private final Logger LOGGER = LoggerFactory.getLogger(Rate.class);

//This Method is used to return the Surcharge rates
	@GetMapping("/surcharge")
	public ResponseEntity<String> getSurcharge() {
		LOGGER.info("Inside getSurcharge()");
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> token = restTemplate.getForObject("https://adfolks.free.beeceptor.com/token", Map.class);
		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", "Bearer " + token.get("token")); // accessToken can be the secret key you generate.
		headers.setContentType(MediaType.APPLICATION_ATOM_XML);
		String requestJson = "{\"\":\"\"}";
		HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
		ResponseEntity<String> url = restTemplate.exchange("https://adfolks.free.beeceptor.com/charge", HttpMethod.GET,
				entity, String.class);
		return url;
	}

	@GetMapping("/searchrate/{id}")
	public Rate searchRateWithRateId(@PathVariable("id") Long id) throws RateException {
		LOGGER.info("Inside searchRate() method");
		return rateService.searchRateWithId(id);
	}

	@PostMapping("/saverate")
	public Rate saveRate(@RequestBody Rate rate) throws RuntimeException {
		LOGGER.info("inside saverate()");
		return rateService.addRate(rate);
	}

	@PostMapping("/updaterate/{id}")
	public Rate updateRate(@PathVariable("id") Long id, @RequestBody Rate rate) throws RuntimeException {
		LOGGER.info("inside updaterate()");
		return rateService.updateRate(id, rate);
	}

	@DeleteMapping("/deleterate/{id}")
	public String deleteRate(@PathVariable("id") Long id) throws RateException {
		LOGGER.info("inside deleterate()");
		rateService.deleteRate(id);
		return "Deleted the recorded";
	}
}
