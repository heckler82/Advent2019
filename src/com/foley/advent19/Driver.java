package com.foley.advent19;

import com.foley.advent19.day01.RocketFuel;
import com.foley.advent19.day02.IntCodeComputer;
import com.foley.advent19.day03.WireIntersect;
import com.foley.advent19.day04.Password;
import com.foley.advent19.day05.ImprovedIntCode;
import com.foley.advent19.day06.Orbits;
import com.foley.advent19.day07.Amplification;
import com.foley.advent19.day08.SpaceImageFormat;
import com.foley.advent19.day10.AsteroidWatch;

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
        //System.out.println("Day 05");
        //new ImprovedIntCode("/com/foley/advent19/day05/Day05.txt").run(timer);

        // Day 06
        //System.out.println("Day 06");
        //new Orbits("/com/foley/advent19/day06/Day06.txt").run(timer);

        // Day 07
        //System.out.println("Day 07");
        //new Amplification("/com/foley/advent19/day07/Day07.txt").run(timer);

        // Day 08
        //System.out.println("Day 08");
        //new SpaceImageFormat("/com/foley/advent19/day08/Day08.txt").run(timer);

        // Day 09
        System.out.println("Day 09");
        //new SpaceImageFormat("/com/foley/advent19/day09/Day09.txt").run(timer);

        // Day 10
        System.out.println("Day 10");
        new AsteroidWatch("/com/foley/advent19/day10/Day10.txt").run(timer);

        // Day 11
        System.out.println("Day 11");
        //new SpaceImageFormat("/com/foley/advent19/day11/Day11.txt").run(timer);

        // Day 12
        System.out.println("Day 12");
        //new SpaceImageFormat("/com/foley/advent19/day12/Day12.txt").run(timer);

        // Day 13
        System.out.println("Day 13");
        //new SpaceImageFormat("/com/foley/advent19/day13/Day13.txt").run(timer);

        // Day 14
        System.out.println("Day 14");
        //new SpaceImageFormat("/com/foley/advent19/day14/Day14.txt").run(timer);

        // Day 15
        System.out.println("Day 15");
        //new SpaceImageFormat("/com/foley/advent19/day15/Day15.txt").run(timer);

        // Day 16
        System.out.println("Day 16");
        //new SpaceImageFormat("/com/foley/advent19/day16/Day16.txt").run(timer);

        // Day 17
        System.out.println("Day 17");
        //new SpaceImageFormat("/com/foley/advent19/day17/Day17.txt").run(timer);

        // Day 18
        System.out.println("Day 18");
        //new SpaceImageFormat("/com/foley/advent19/day18/Day18.txt").run(timer);

        // Day 19
        System.out.println("Day 19");
        //new SpaceImageFormat("/com/foley/advent19/day19/Day19.txt").run(timer);

        // Day 20
        System.out.println("Day 20");
        //new SpaceImageFormat("/com/foley/advent19/day20/Day20.txt").run(timer);

        // Day 21
        System.out.println("Day 21");
        //new SpaceImageFormat("/com/foley/advent21/day14/Day21.txt").run(timer);

        // Day 22
        System.out.println("Day 22");
        //new SpaceImageFormat("/com/foley/advent22/day14/Day22.txt").run(timer);

        // Day 23
        System.out.println("Day 23");
        //new SpaceImageFormat("/com/foley/advent19/day23/Day23.txt").run(timer);

        // Day 24
        System.out.println("Day 24");
        //new SpaceImageFormat("/com/foley/advent19/day24/Day24.txt").run(timer);

        // Day 25
        System.out.println("Day 25");
        //new SpaceImageFormat("/com/foley/advent19/day25/Day25.txt").run(timer);

        System.out.println();
        timer.printTotalTime(SimpleTimer.Units.SECONDS);
    }
}