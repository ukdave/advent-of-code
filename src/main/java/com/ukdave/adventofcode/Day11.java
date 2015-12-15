package com.ukdave.adventofcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advent of Code - Day 11
 *
 * @author uk_dave
 */
public class Day11 {

    private final Pattern p1 = Pattern.compile("(abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz)");
    private final Pattern p2 = Pattern.compile("[iol]");
    private final Pattern p3 = Pattern.compile("(.)\\1.*((?!\\1).)\\2");

    public static void main(final String[] args) {
        Day11 day11 = new Day11();
        System.out.println(day11.nextPassword("cqjxjnds")); // cqjxxyzz
        System.out.println(day11.nextPassword("cqjxxyzz")); // cqkaabcc
    }

    public String nextPassword(final String currentPassword) {
        String newPassword = currentPassword;

        while (true) {
            newPassword = increment(newPassword);

            Matcher m = p1.matcher(newPassword);
            if (!m.find()) {
                continue;
            }

            m = p2.matcher(newPassword);
            if (m.find()) {
                continue;
            }

            m = p3.matcher(newPassword);
            if (!m.find()) {
                continue;
            }

            break;
        }

        return newPassword;
    }

    public String increment(final String in) {
        long i = Long.parseLong(in, 36) + 1;
        return Long.toString(i, 36).replace('0', 'a');
    }
}
