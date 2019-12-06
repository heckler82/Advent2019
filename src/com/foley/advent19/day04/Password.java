package com.foley.advent19.day04;

import com.foley.advent19.AdventMaster;

import java.util.stream.IntStream;

/**
 * Deciphers the password at the fuel station
 *
 * @author Evan Foley
 * @version 04 Dec 2019
 */
public class Password extends AdventMaster {
    /**
     * Creates a new password object
     *
     * @param fileName The name of the input file
     */
    public Password(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the task for this day
     */
    protected void task() {
        String[] vals = input[0].split("-");
        int startRange = Integer.parseInt(vals[0]); // 145852 -> 145888 is minimum starting value
        int endRange = Integer.parseInt(vals[1]); // 616942 -> 599999 is maximum possible value
        System.out.printf("There are %d valid passwords\n", IntStream.range(startRange, endRange).filter(Password::validate).count());
    }

    /**
     * Validates an integer
     *
     * @param val The integer
     * @return True if it is valid
     */
    private static boolean validate(int val) {
        char[] chars = Integer.toString(val).toCharArray();
        boolean allIncreasing = true;
        for(int i = 0; i < chars.length - 1; i++) {
            allIncreasing &= chars[i] <= chars[i + 1];
        }
        boolean hasTwoAdj = chars[0] == chars[1] && chars[1] != chars[2];
        for(int i = 1; i < chars.length - 1; i++) {
            hasTwoAdj |= chars[i] == chars[i + 1] && chars[i + 1] != chars[i + 2] && chars[i] != chars[i - 1];
        }
        hasTwoAdj |= chars[4] == chars[5] && chars[4] != chars[3];
        return hasTwoAdj && allIncreasing;
    }
}
