package com.springboot.tutorial.service;

import com.springboot.tutorial.Exceptionhandling.RateException;
import com.springboot.tutorial.entity.Rate;


public interface RateService {
	Rate searchRateWithId(Long Id) throws RateException;
	Rate addRate(Rate rate) throws RuntimeException;
	Rate updateRate(Long id, Rate rate)  throws RuntimeException;
	void deleteRate(Long id) throws RateException;
}
