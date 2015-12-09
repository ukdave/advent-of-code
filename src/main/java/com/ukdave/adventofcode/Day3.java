package com.ukdave.adventofcode;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

/**
 * Advent of Code - Day 3.
 * 
 * @author uk_dave
 */
public class Day3 {

	public static void main(final String[] args) throws IOException {
		Set<Point> visitedHouses = new HashSet<>();
		
		Point santa = new Point();
		Point robot = new Point();
		
		visitedHouses.add(santa.getLocation());
		
		try (Reader in = new InputStreamReader(Day1.class.getResourceAsStream("/day3.txt"))) {
			Point player = santa;
			for (int c = in.read(); c != -1; c = in.read()) {
				if (c == '^') {
					player.y++;
				} else if (c == '>') {
					player.x++;
				} else if (c == 'v') {
					player.y--;
				} else if (c == '<') {
					player.x--;
				}
				
				visitedHouses.add(player.getLocation());
				
				if (player == santa) {
					player = robot;
				} else {
					player = santa;
				}
			}
		}
		
		System.out.println(visitedHouses.size()); // 2572, 2631
	}
}
