package com.example.coupon.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Coupon {
	@Id
	@GeneratedValue
	private Long id;

	private String email;

	private String couponCode;

	private LocalDateTime issuedAt;
}
