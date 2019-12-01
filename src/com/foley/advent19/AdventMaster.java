package com.foley.advent19;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides a template for some of the Advent of code classes. Assumes file exists and is formatted according to AOC convention
 *
 * @author Evan Foley
 * @version 02 Dec 2018
 */
public abstract class AdventMaster {
    protected String[] input;

    /**
     * Creates a new master object
     *
     * @param fileName The name of the input file
     */
    public AdventMaster(String fileName) {
        setup(fileName);
    }

    /**
     * Prepares the input for use
     *
     * @param scan The parsing object
     * @return The input from the parser
     */
    protected String[] processInputFile(Scanner scan) {
        ArrayList<String> arr = new ArrayList<>();
        while(scan.hasNextLine()) {
            arr.add(scan.nextLine());
        }
        return arr.toArray(new String[arr.size()]);
    }

    /**
     * Sets up the data
     *
     * @param fileName The name of the input file
     */
    protected void setup(String fileName) {
        //File file = new File(fileName);
        InputStream is = AdventMaster.class.getResourceAsStream(fileName);
        Scanner scan = new Scanner(is);
        input = processInputFile(scan);
    }

    /**
     * Runs the program
     *
     * @param timer The timer
     */
    public void run(SimpleTimer timer) {
        timer.setToCurrentTime();
        task();
        timer.tick();
        timer.printTick();
    }

    /**
     * Accomplishes the task for this day
     */
    protected abstract void task();
}