package com.springboot.tutorial.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Long rateId;
	private String rateDescription;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rateEffectiveDate;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rateExpirationDate;
	@NotNull
	private Integer amount;
}
