package com.example.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

	boolean existsByEmail(String email);

	boolean existsByCouponCode(String couponCode);
}
