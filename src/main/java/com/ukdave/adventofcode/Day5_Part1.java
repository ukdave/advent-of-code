package com.ukdave.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advent of Code - Day 5 Part 1.
 * 
 * @author uk_dave
 */
public class Day5_Part1 {

	public static void main(final String[] args) throws IOException {
		Pattern disallowedPattern = Pattern.compile("(ab|cd|pq|xy)");
		Pattern vowelsPattern = Pattern.compile("([aeiou].*){3}");
		Pattern twoInRowPattern = Pattern.compile("(.)\\1");
		
		int nice = 0;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(Day2.class.getResourceAsStream("/day5.txt")))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				
				Matcher disallowedMatcher = disallowedPattern.matcher(line);
				if (disallowedMatcher.find()) {
					continue;
				}
				
				Matcher vowelsMatcher = vowelsPattern.matcher(line);
				if (!vowelsMatcher.find()) {
					continue;
				}
				
				Matcher twoInRowMatcher = twoInRowPattern.matcher(line);
				if (!twoInRowMatcher.find()) {
					continue;
				}
				
				nice++;
				
			}
		}
		
		System.out.println(nice); // 238
	}
}
