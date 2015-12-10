package com.ukdave.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advent of Code - Day 5 Part 2.
 * 
 * @author uk_dave
 */
public class Day5_Part2 {

	public static void main(final String[] args) throws IOException {
		Pattern rule1Pattern = Pattern.compile("(..).*\\1");
		Pattern rule2Pattern = Pattern.compile("(.).\\1");
		
		int nice = 0;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(Day2.class.getResourceAsStream("/day5.txt")))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				
				Matcher rule1Matcher = rule1Pattern.matcher(line);
				if (!rule1Matcher.find()) {
					continue;
				}
				
				Matcher rule2Matcher = rule2Pattern.matcher(line);
				if (!rule2Matcher.find()) {
					continue;
				}
				
				nice++;
				
			}
		}
		
		System.out.println(nice); // 69
	}
}
