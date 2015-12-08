package com.ukdave.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advent of Code - Day 2.
 * 
 * @author uk_dave
 */
public class Day2 {

	public static void main(final String[] args) throws IOException {
		int paper = 0;
		int ribbon = 0;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(Day2.class.getResourceAsStream("/day2.txt")))) {
			Pattern p = Pattern.compile("([0-9]+)x([0-9]+)x([0-9]+)");
			
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				Matcher m = p.matcher(line);
				if (!m.matches()) {
					continue;
				}
				
				int w = Integer.parseInt(m.group(1));
				int h = Integer.parseInt(m.group(2));
				int l = Integer.parseInt(m.group(3));
				
				int side1area = l * w;
				int side2area = w * h;
				int side3area = h * l;
				int smallestSide = Math.min(Math.min(side1area, side2area), side3area);
				
				int side1perim = l + l + w + w;
				int side2perim = w + w + h + h;
				int side3perim = h + h + l + l;
				int smallestPerim = Math.min(Math.min(side1perim, side2perim), side3perim);
				
				int surfaceArea = (2 * side1area) + (2 * side2area) + (2 * side3area);
				int volume = w * h * l;
				
				paper += (surfaceArea + smallestSide);
				ribbon += (smallestPerim + volume);
			}
		}
		
		System.out.println(paper);
		System.out.println(ribbon);
	}
}
