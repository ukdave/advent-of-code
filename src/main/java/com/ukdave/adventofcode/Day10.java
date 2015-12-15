package com.ukdave.adventofcode;

/**
 * Advent of Code - Day 10
 *
 * @author uk_dave
 */
public class Day10 {

    public static void main(final String[] args) {
        String str = "1113122113";

        for (int i = 0; i < 40; i++) {
            str = lookAndSay(str);
        }

        System.out.println(str.length());
    }

    private static String lookAndSay(final String in) {
        StringBuffer out = new StringBuffer();

        int start = 0;
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) != in.charAt(start)) {
                out.append(i - start);
                out.append(in.charAt(start));
                start = i;
            }
        }
        out.append(in.length() - start);
        out.append(in.charAt(start));

        return out.toString();
    }
}
