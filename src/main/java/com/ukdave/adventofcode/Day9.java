package com.ukdave.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advent of Code - Day 9
 * 
 * @author uk_dave
 */
public class Day9 {

	public static void main(final String[] args) throws IOException {
		Map<String, Map<String, Integer>> distances = new HashMap<>();
		Set<String> cities = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(Day9.class.getResourceAsStream("/day9.txt")))) {
			Pattern p = Pattern.compile("(.*) to (.*) = ([0-9]+)");
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				Matcher m = p.matcher(line);
				if (m.matches()) {
					if (!distances.containsKey(m.group(1))) {
						distances.put(m.group(1), new HashMap<>());
					}
					if (!distances.containsKey(m.group(2))) {
						distances.put(m.group(2), new HashMap<>());
					}
					distances.get(m.group(1)).put(m.group(2), Integer.parseInt(m.group(3)));
					distances.get(m.group(2)).put(m.group(1), Integer.parseInt(m.group(3)));
					cities.add(m.group(1));
					cities.add(m.group(2));
				}
			}
		}

		List<List<String>> permutations = generatePermutations(new ArrayList<>(cities));
		int minDist = Integer.MAX_VALUE;
		int maxDist = 0;
		for (List<String> p : permutations) {
			int dist = calcDistance(p, distances);
			if (dist < minDist) {
				minDist = dist;
			}
			if (dist > maxDist) {
				maxDist = dist;
			}
			System.out.println(p + " = " + dist);
		}
		System.out.println("minDist = " + minDist);
		System.out.println("maxDist = " + maxDist);
	}

	public static List<List<String>> generatePermutations(final List<String> in) {
		List<List<String>> permutations = new ArrayList<>();
		generatePermutations(new ArrayList<>(in), new ArrayList<>(), permutations);
		return permutations;
	}

	private static void generatePermutations(final List<String> in, final List<String> permutation, final List<List<String>> permutations) {
		if (in.isEmpty()) {
			permutations.add(permutation);
		} else {
			for (int i = 0; i < in.size(); i++) {
				List<String> newIn = new ArrayList(in);
				List<String> newPermutation = new ArrayList(permutation);
				newPermutation.add(newIn.remove(i));
				generatePermutations(newIn, newPermutation, permutations);
			}
		}
	}

	public static int calcDistance(final List<String> cities, final Map<String, Map<String, Integer>> distances) {
		int dist = 0;
		for (int i = 0; i < cities.size() - 1; i++) {
			dist += distances.get(cities.get(i)).get(cities.get(i + 1));
		}
		return dist;
	}
}

