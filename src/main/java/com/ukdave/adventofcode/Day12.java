package com.ukdave.adventofcode;

import jdk.nashorn.internal.runtime.JSONFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advent of Code - Day 12
 *
 * @author uk_dave
 */
public class Day12 {

    public static final Pattern objPattern = Pattern.compile("\\{[^{}]*\\}");
    public static final Pattern numPattern = Pattern.compile("(-?[0-9]+)");

    public static void main(final String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Day9.class.getResourceAsStream("/day12.txt")))) {
            String in = br.readLine();
            System.out.println(sumNumbers(in)); // 111754
            System.out.println(sumNumbersIgnoreRed(in)); // 65402
        }
    }

    public static int sumNumbers(final String in) {
        int sum = 0;
        Matcher m = numPattern.matcher(in);
        while (m.find()) {
            sum += Integer.parseInt(m.group());
        }
        return sum;
    }

    public static int sumNumbersIgnoreRed(final String in) {
        Matcher m = objPattern.matcher(in);
        if (m.find()) {
            String obj = m.group();
            int sum = 0;
            if (!obj.contains(":\"red\"")) {
                sum = sumNumbers(obj);
            }
            String newIn = in.substring(0, m.start()) + sum + in.substring(m.end());
            return sumNumbersIgnoreRed(newIn);
        } else {
            return sumNumbers(in);
        }
    }
}
