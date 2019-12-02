package com.foley.advent19.day01;

import com.foley.advent19.AdventMaster;

/**
 * Calculates required fuel
 *
 * @author Evan Foley
 * @version 01 Dec 2019
 */
public class RocketFuel extends AdventMaster {
    /**
     * Creates a new rocketfuel
     *
     * @param fileName The name of the input file
     */
    public RocketFuel(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the task for this day
     */
    protected void task() {
        int totalOne = 0;
        int totalTwo = 0;
        for(String s : input) {
            int current = Integer.parseInt(s);
            totalOne += (current / 3) - 2;
            while((current = (current / 3) - 2) > 0) {
                totalTwo += current;
            }
        }
        System.out.printf("The amount of fuel required for part 1 is %d, and part 2 requires %d\n", totalOne, totalTwo);
    }
}
