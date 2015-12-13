package com.ukdave.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advent of Code - Day 8
 * 
 * @author uk_dave
 */
public class Day8 {

	public static int countInstances(final String string, final Pattern pattern) {
		int count = 0;
		for (Matcher m = pattern.matcher(string); m.find(); count++);
		return count;
	}

	public static void main(final String[] args) throws IOException {
		int codeLength = 0;
		int decodedLength = 0;
		int encodedLength = 0;

		Pattern p1 = Pattern.compile("\\\\\"");
		Pattern p2 = Pattern.compile("\\\\\\\\");
		Pattern p3 = Pattern.compile("[^\\\\]?\\\\x[0-9a-f]{2}");

		Pattern p4 = Pattern.compile("\"");
		Pattern p5 = Pattern.compile("\\\\");

		try (BufferedReader br = new BufferedReader(new InputStreamReader(Day8.class.getResourceAsStream("/day8.txt")))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				codeLength += line.length();

				String inner = line.substring(1, line.length() - 1);
				decodedLength += inner.length() - (countInstances(inner, p1) + countInstances(inner, p2) + (countInstances(inner, p3) * 3));

				encodedLength += 2 + line.length() + countInstances(line, p4) + countInstances(line, p5);
			}
		}

		System.out.println(codeLength - decodedLength); // 1333
		System.out.println(encodedLength - codeLength); // 2046
	}
}

