package com.foley.advent19;

import com.foley.advent19.day01.RocketFuel;

/**
 * Main entry-point for program
 *
 * @author Evan Foley
 * @version 01 Dec 2018
 */
public class Driver {
    public static void main(String[] args) {
        // Timing mechanism
        SimpleTimer timer = new SimpleTimer();

        // Day 01
        System.out.println("Day 01");
        new RocketFuel("/com/foley/advent19/day01/Day01.txt").run(timer);

        System.out.println();
        timer.printTotalTime(SimpleTimer.Units.SECONDS);
    }
}