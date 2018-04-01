package com.example.coupon.controller;

import com.example.coupon.repository.Coupon;
import com.example.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/api")
@Slf4j
public class CouponApiController {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@Autowired
	private CouponService couponService;

	@RequestMapping(value = "/coupons", method = RequestMethod.POST)
	@ResponseBody
	public String issueCouponCode(@RequestBody String email) {

		log.info("[IsuueCouponCode] email: {}", email);

		if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
			throw new IllegalArgumentException("Email 형식이 아닙니다.");
		}

		return couponService.generate(email);
	}

	@RequestMapping(value = "/coupons", method = RequestMethod.GET)
	@ResponseBody
	public Page<Coupon> find(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
		return couponService.find(pageable);
	}
}
