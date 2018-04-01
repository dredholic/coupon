package com.example.coupon.service

import com.example.coupon.repository.CouponRepository
import com.example.coupon.util.CouponCodeGenerator
import spock.lang.Specification
import spock.lang.Subject

class CouponServiceTest extends Specification {

	@Subject
	CouponService couponService

	CouponRepository couponRepository = Mock(CouponRepository)

	void setup() {
		GroovyMock(CouponCodeGenerator, global: true)
		couponService = new CouponService(
			couponRepository: couponRepository
		)
	}

	def "generate - email 존재할 경우 exception 발생"() {
		given:
		String email = "dredholic@gmail.com"
		couponRepository.existsByEmail(email) >> true

		when:
		couponService.generate(email)

		then: "Exception 발생"
		IllegalArgumentException ex = thrown()
		ex.message.contains("중복된 email 입니다. email: ")
	}

	def "generate"() {
		given:
		String email = "dredholic@gmail.com"
		couponRepository.existsByEmail(email) >> false

		when:
		String result = couponService.generate(email)

		then:
		result != null
	}
}
