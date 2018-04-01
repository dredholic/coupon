package com.example.coupon.controller

import com.example.coupon.service.CouponService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class CouponApiControllerTest extends Specification {
	
	@Subject
	CouponApiController couponApiController

	CouponService couponService = Mock(CouponService)

	void setup() {
		couponApiController = new CouponApiController(
				couponService: couponService
		)
	}

	@Unroll
	def "issueCouponCode(#email) - email 형식에 맞지 않으면 에러 발생"(String email) {
		when:
		couponApiController.issueCouponCode(email)

		then:
		IllegalArgumentException ex = thrown()
		ex.message == "Email 형식이 아닙니다."

		where:
		email << ["String", "aaa@bbb", "???@gmail.com"]
	}

	def "issueCouponCode - couponGenerator 에서 생성된 couponCode 리턴"() {
		given:
		String email = "dredholic@gmail.com"
		String expected = "ABCD-EFGH-IJKL-MNOP"

		when:
		String result = couponApiController.issueCouponCode(email)

		then:
		result == expected
		1 * couponService.generate(email) >> expected
	}
}
