package com.ukdave.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advent of Code - Day 13
 * 
 * @author uk_dave
 */
public class Day13 {

	public static void main(final String[] args) throws IOException {
		Map<String, Map<String, Integer>> happinessMap = new HashMap<>();
		Set<String> people = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(Day13.class.getResourceAsStream("/day13.txt")))) {
			Pattern p = Pattern.compile("(.*) would (gain|lose) ([0-9]+) happiness units by sitting next to (.*).");
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				Matcher m = p.matcher(line);
				if (m.matches()) {
					String person1 = m.group(1);
					int units = Integer.parseInt(m.group(3));
					String person2 = m.group(4);
					if (m.group(2).equals("lose")) {
						units *= -1;
					}

					if (!happinessMap.containsKey(person1)) {
						happinessMap.put(person1, new HashMap<>());
					}
					happinessMap.get(person1).put(person2, units);
					people.add(person1);
				}
			}
		}

		List<List<String>> permutations = generatePermutations(new ArrayList<>(people));
		int maxHappiness = Integer.MIN_VALUE;
		for (List<String> p : permutations) {
			int happiness = calcHappiness(p, happinessMap);
			if (happiness > maxHappiness) {
				maxHappiness = happiness;
			}
			//System.out.println(p + " = " + happiness);
		}
		System.out.println("maxHappiness = " + maxHappiness); // 664


		// Add self
		happinessMap.put("Me", new HashMap<>());
		for (String person : people) {
			happinessMap.get(person).put("Me", 0);
			happinessMap.get("Me").put(person, 0);
		}
		people.add("Me");

		permutations = generatePermutations(new ArrayList<>(people));
		maxHappiness = Integer.MIN_VALUE;
		for (List<String> p : permutations) {
			int happiness = calcHappiness(p, happinessMap);
			if (happiness > maxHappiness) {
				maxHappiness = happiness;
			}
			//System.out.println(p + " = " + happiness);
		}
		System.out.println("maxHappiness = " + maxHappiness); // 640
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

	public static int calcHappiness(final List<String> people, final Map<String, Map<String, Integer>> happinessMap) {
		int happiness = 0;
		for (int i = 0; i < people.size() - 1; i++) {
			happiness += happinessMap.get(people.get(i)).get(people.get(i + 1));
			happiness += happinessMap.get(people.get(i + 1)).get(people.get(i));
		}
		happiness += happinessMap.get(people.get(people.size() - 1)).get(people.get(0));
		happiness += happinessMap.get(people.get(0)).get(people.get(people.size() - 1));
		return happiness;
	}
}

