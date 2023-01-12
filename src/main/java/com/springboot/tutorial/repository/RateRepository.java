package com.springboot.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.tutorial.entity.Rate;

@Repository
public interface RateRepository  extends JpaRepository<Rate, Long>{

}
