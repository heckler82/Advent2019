package com.foley.advent19;

import com.foley.advent19.day01.RocketFuel;
import com.foley.advent19.day02.IntCodeComputer;
import com.foley.advent19.day03.WireIntersect;
import com.foley.advent19.day04.Password;
import com.foley.advent19.day06.Orbits;

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
        //System.out.println("Day 01");
        //new RocketFuel("/com/foley/advent19/day01/Day01.txt").run(timer);

        // Day 02
        //System.out.println("Day 02");
        //new IntCodeComputer("/com/foley/advent19/day02/Day02.txt").run(timer);

        // Day 03
        //System.out.println("Day 02");
        //new WireIntersect("/com/foley/advent19/day03/Day03.txt").run(timer);

        // Day 04
        //System.out.println("Day 04");
        //new Password("/com/foley/advent19/day04/Day04.txt").run(timer);

        // Day 05
        //System.out.println("Day 04");
        //new Password("/com/foley/advent19/day04/Day04.txt").run(timer);

        // Day 06
        //System.out.println("Day 06");
        new Orbits("/com/foley/advent19/day06/Day06.txt").run(timer);

        System.out.println();
        timer.printTotalTime(SimpleTimer.Units.SECONDS);
    }
}