package com.springboot.tutorial.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.tutorial.entity.Rate;
import com.springboot.tutorial.service.RateService;

@WebMvcTest(RateController.class)
class RateControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private RateService rateservice;
	private Rate rate;

	@BeforeEach
	void setUp() {
		Date date=new Date();
		rate = Rate.builder().rateDescription("This rate is for Jeans Clothes")
				.rateEffectiveDate(date).rateExpirationDate(date).amount(2000)
				.build();
	}
	@Test
	void saveDepartment() throws Exception {
		Rate inputRateItem = Rate.builder().rateDescription("This rate is for Jeans Clothes")
				.rateEffectiveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-12")).rateExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01")).amount(2000)
				.build();
		Mockito.when(rateservice.addRate(inputRateItem)).thenReturn(rate);
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/saverate")
			      .content(asJsonString(Rate.builder().rateDescription("This rate is for Jeans Clothes")
							.rateEffectiveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-12")).rateExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01")).amount(2000)
							.build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(status().isOk());
	}
	@Test
	void searchRateWithRateId() throws Exception {
		Rate inputRateItem = Rate.builder().rateDescription("This rate is for Jeans Clothes")
				.rateEffectiveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-12")).rateExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01")).amount(2000)
				.build();
		Mockito.when(rateservice.searchRateWithId(1L)).thenReturn(rate);
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/saverate")
			      .content(asJsonString(Rate.builder().rateDescription("This rate is for Jeans Clothes")
							.rateEffectiveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-12")).rateExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01")).amount(2000)
							.build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(status().isOk());
	}
	@Test
	void updateRateWithRateId() throws Exception {
		Rate inputRateItem = Rate.builder().rateDescription("This rate is for Sarees")
				.rateEffectiveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-12")).rateExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01")).amount(2000)
				.build();
		Mockito.when(rateservice.updateRate(1L,inputRateItem)).thenReturn(rate);
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/updaterate/1")
			      .content(asJsonString(Rate.builder().rateDescription("This rate is for Jeans Clothes")
							.rateEffectiveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-12")).rateExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01")).amount(2000)
							.build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(status().isOk());
	}
	@Test
	void deleteRateWithRateId() throws Exception {
		Rate inputRateItem = Rate.builder().rateDescription("This rate is for Jeans Clothes")
				.rateEffectiveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-12")).rateExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01")).amount(2000)
				.build();
		rateservice.deleteRate(1L);
		mockMvc.perform( MockMvcRequestBuilders
			      .delete("/deleterate/1")
			      .content("Deleted the recorded")
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(status().isOk());
	}
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
