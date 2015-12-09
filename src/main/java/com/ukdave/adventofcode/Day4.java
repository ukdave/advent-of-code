package com.ukdave.adventofcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Advent of Code - Day 4.
 * 
 * @author uk_dave
 */
public class Day4 {

	public static void main(final String[] args) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("md5");
		
		String secret = "yzbqklnj";
		
		int i = 0;
		int num5 = -1;
		int num6 = -1;
		
		do {
			String str = secret + i;
			byte[] theDigest = md5.digest(str.getBytes());
			if (num5 == -1 && theDigest[0] == 0 && theDigest[1] == 0 && (theDigest[2] & 0xf0) == 0) {
				num5 = i;
			}
			if (num6 == -1 && theDigest[0] == 0 && theDigest[1] == 0 && theDigest[2] == 0) {
				num6 = i;
			}
			i++;
		} while (num5 == -1 || num6 == -1);
		
		System.out.println(num5); // 282749
		System.out.println(num6); // 9962624
	}
}
