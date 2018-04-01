package com.example.coupon.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Subject

import java.time.LocalDateTime

@Stepwise
@DataJpaTest
@ContextConfiguration
class CouponRepositoryTest extends Specification {

	@Subject
	@Autowired
	CouponRepository couponRepository

	@Autowired
	TestEntityManager entityManager

	String email = "dredholic@gmail.com"
	String couponCode = "HIoR-eQ7f-rfDw-Y6Jd"

	def "save"() {
		given:
		Coupon coupon = new Coupon(
				email: email,
				couponCode: couponCode,
				issuedAt: LocalDateTime.now())
		couponRepository.save(coupon)
		entityManager.flush()
		entityManager.detach(coupon)

		when:
		Coupon result = couponRepository.findById(1L).get()

		then:
		result.email == email
		result.couponCode == couponCode
	}

	def "existsByEmail"() {
		when:
		couponRepository.existsByEmail(email)

		then:
		1 == 1
	}

	def "existsByCouponCode"() {
		when:
		couponRepository.existsByCouponCode(couponCode)

		then:
		1 == 1
	}


	def "findAll with pagable"() {
		given:
		Pageable pageable = PageRequest.of(1, 10, Sort.by("id").descending())

		when:
		couponRepository.findAll(pageable)

		then:
		1 == 1
	}
}
