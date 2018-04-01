package com.example.coupon.util

import spock.lang.Specification
import spock.lang.Unroll

class CouponCodeGeneratorTest extends Specification {


	@Unroll("#i")
	def "generate - 쿠폰번호 포맷 테스트"() {
		given:
		String regex = /^[A-Z0-9a-z]{4,4}\-[A-Z0-9a-z]{4,4}\-[A-Z0-9a-z]{4,4}\-[A-Z0-9a-z]{4,4}$/

		when:
		String result = CouponCodeGenerator.generate()
		println result

		then:
		result ==~ regex

		where:
		i << (1..10)
	}
}
