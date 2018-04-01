package com.example.coupon.service;

import com.example.coupon.util.CouponCodeGenerator;
import com.example.coupon.repository.Coupon;
import com.example.coupon.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;

	public String generate(String email) {
		validateDuplicateEmail(email);
		String couponCode = getUniqueCouponCode();
		couponRepository.save(createCoupon(email, couponCode));
		return couponCode;
	}

	private void validateDuplicateEmail(String email) {
		if (couponRepository.existsByEmail(email)) {
			throw new IllegalArgumentException("중복된 email 입니다. email: " + email);
		}
	}

	private String getUniqueCouponCode() {
		String couponCode;
		do {
			couponCode = CouponCodeGenerator.generate();
		} while (couponRepository.existsByCouponCode(couponCode));
		return couponCode;
	}

	private Coupon createCoupon(String email, String couponCode) {
		Coupon coupon = new Coupon();
		coupon.setEmail(email);
		coupon.setCouponCode(couponCode);
		coupon.setIssuedAt(LocalDateTime.now());
		return coupon;
	}

	public Page<Coupon> find(Pageable pageable) {
		return couponRepository.findAll(pageable);
	}
}
