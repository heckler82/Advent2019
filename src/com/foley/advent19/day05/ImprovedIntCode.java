package com.foley.advent19.day05;

import com.foley.advent19.AdventMaster;
import com.foley.util.Formatting;
import com.foley.util.IntCode;

import java.util.Scanner;

/**
 * Rebuilds the ship's computer
 *
 * @author Evan Foley
 * @version 02 Dec 2019
 */
public class ImprovedIntCode extends AdventMaster {

    /**
     * Creates a new intcode computer
     *
     * @param fileName The name of the input file
     */
    public ImprovedIntCode(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the task for this day
     */
    protected void task() {
        IntCode computer = new IntCode(Formatting.convertToIntArray(input[0].split(",")));
        computer.setInput(5);
        computer.run();
        System.out.printf("The output value is %d\n", computer.getOutput());
    }
}
