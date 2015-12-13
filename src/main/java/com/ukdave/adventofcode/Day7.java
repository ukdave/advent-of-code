package com.ukdave.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Advent of Code - Day 7
 * 
 * @author uk_dave
 */
public class Day7 {

	private final Map<String, Operation> wires = new HashMap<>();

	public void addInstruction(final String instruction) {
		String split[] = instruction.split(" -\\> ");
		wires.put(split[1], new Operation(split[0]));
	}

	public void clearInstructions() {
		wires.clear();
	}

	public void resetOutputs() {
		wires.values().forEach(Operation::resetOutput);
	}

	public void printWires() {
		for (Map.Entry<String, Operation> entry : wires.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue().getOutput());
		}
	}

	public void printWire(final String wire) {
		System.out.println(wires.get(wire).getOutput());
	}

	private class Operation {
		private final String op;
		private Integer output;

		public Operation(final String op) {
			this.op = op;
		}

		private Integer calculate() {
			String[] split = op.split(" ");
			if (split.length == 1) {
				// 123 -> x
				return getInput(split[0]);
			} else if (split.length == 2) {
				// NOT x -> h
				return 0xffff & (~getInput(split[1]));
			} else if (split.length == 3) {
				switch (split[1]) {
					case "AND":
						// x AND y -> d
						return 0xffff & (getInput(split[0]) & getInput(split[2]));
					case "OR":
						// x OR y -> e
						return 0xffff & (getInput(split[0]) | getInput(split[2]));
					case "LSHIFT":
						// x LSHIFT 2 -> f
						return 0xffff & (getInput(split[0]) << getInput(split[2]));
					case "RSHIFT":
						// y RSHIFT 2 -> g
						return 0xffff & (getInput(split[0]) >> getInput(split[2]));
				}
			}
			return null;
		}

		private Integer getInput(final String input) {
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				return wires.get(input).getOutput();
			}
		}

		public void resetOutput() {
			output = null;
		}

		public Integer getOutput() {
			if (output == null) {
				output = calculate();
				if (output == null) {
					System.err.println("Could not calculate value for: " + op);
				}
			}
			return output;
		}
	}

	public static void main(final String[] args) throws IOException {
		Day7 day7 = new Day7();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(Day7.class.getResourceAsStream("/day7.txt")))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				day7.addInstruction(line);
			}
		}
		//day7.printAllWires();
		day7.printWire("a"); // 956

		day7.resetOutputs();
		day7.addInstruction("956 -> b");
		day7.printWire("a"); // 40149
	}
}
