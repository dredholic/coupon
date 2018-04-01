package com.example.coupon.util;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CouponCodeGenerator {

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	private static final char[] POSSIBLE_CHARACTORS = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};

	public static String generate() {
		return Stream.generate(CouponCodeGenerator::generatePartOfCoupon).limit(4).collect(Collectors.joining("-"));
	}

	private static String generatePartOfCoupon() {
		return String.format("%c%c%c%c",
			POSSIBLE_CHARACTORS[SECURE_RANDOM.nextInt(POSSIBLE_CHARACTORS.length)],
			POSSIBLE_CHARACTORS[SECURE_RANDOM.nextInt(POSSIBLE_CHARACTORS.length)],
			POSSIBLE_CHARACTORS[SECURE_RANDOM.nextInt(POSSIBLE_CHARACTORS.length)],
			POSSIBLE_CHARACTORS[SECURE_RANDOM.nextInt(POSSIBLE_CHARACTORS.length)]);
	}
}
