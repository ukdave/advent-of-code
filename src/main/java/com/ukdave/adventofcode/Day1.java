package com.ukdave.adventofcode;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Advent of Code - Day 1.
 * 
 * @author uk_dave
 */
public class Day1 {

	public static void main(final String[] args) throws IOException {
		int floor = 0;
		Integer basementPos = null;
		
		try (Reader in = new InputStreamReader(Day1.class.getResourceAsStream("/day1.txt"))) {
			for (int i = 0, c = in.read(); c != -1; c = in.read()) {
				if (c == '(') {
					floor++;
				} else if (c == ')') {
					floor--;
				}
				if (basementPos == null && floor == -1) {
					basementPos = i + 1;
				}
				i++;
			}
		}
		
		System.out.println(floor);
		System.out.println(basementPos);
	}
}
