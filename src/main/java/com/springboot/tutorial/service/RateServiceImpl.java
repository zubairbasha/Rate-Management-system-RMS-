package com.springboot.tutorial.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.tutorial.Exceptionhandling.RateException;
import com.springboot.tutorial.entity.Rate;
import com.springboot.tutorial.repository.RateRepository;

@Service
public class RateServiceImpl implements RateService{
	@Autowired
	RateRepository raterepo;
	@Override
	public Rate searchRateWithId(Long Id)  throws RateException{
		Optional<Rate> rateOfProduct=raterepo.findById(Id);
		if(!rateOfProduct.isPresent()) {
			throw new RateException("RateId not found in RMS");
		}
		return rateOfProduct.get();
	}

	@Override
	public Rate addRate(Rate rate)  throws RuntimeException{
		try {
		return raterepo.save(rate) ;
		}catch(Exception e) {
			//System.out.println("Exception");
			throw new RuntimeException("Internal Server Error");
		}
	}


	@Override
	public Rate updateRate(Long id, Rate rate)  throws RuntimeException{
		try {
		Rate rateDb = raterepo.findById(id).get();
		if (Objects.nonNull(rate.getAmount()) && (rate.getAmount()!=0)) {
			rateDb.setAmount(rate.getAmount());
		}
		if (Objects.nonNull(rate.getRateExpirationDate()) && (rate.getRateExpirationDate()!=null)) {
			rateDb.setRateExpirationDate(rate.getRateExpirationDate());
		}
		if (Objects.nonNull(rate.getRateEffectiveDate()) && (rate.getRateEffectiveDate()!=null)) {
			rateDb.setRateEffectiveDate(rate.getRateEffectiveDate());
		}
		if (Objects.nonNull(rate.getRateDescription()) &&  !"".equalsIgnoreCase(rate.getRateDescription())) {
		rateDb.setRateDescription(rate.getRateDescription());
		}
		return raterepo.save(rateDb);
		}catch(RuntimeException r) {
			throw new RuntimeException("Internal Server Error");
		}
	}

	@Override
	public void deleteRate(Long id) throws RateException {
		try {
			raterepo.deleteById(id);
		}catch(Exception e) {
			throw new RateException("RateId not found in RMS");
		}
		
	}

}
