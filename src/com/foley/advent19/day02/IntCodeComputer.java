package com.foley.advent19.day02;

import com.foley.advent19.AdventMaster;
import com.foley.util.Formatting;
import com.foley.util.IntCode;

/**
 * Rebuilds the ship's computer
 *
 * @author Evan Foley
 * @version 02 Dec 2019
 */
public class IntCodeComputer extends AdventMaster {
    /**
     * Creates a new intcode computer
     *
     * @param fileName The name of the input file
     */
    public IntCodeComputer(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the task for this day
     */
    protected void task() {
        IntCode computer = new IntCode(Formatting.convertToIntArray(input[0].split(",")));
        computer.setMemoryValue(1, 33);
        computer.setMemoryValue(2, 76);

        computer.run();
        System.out.printf("The value at position 0 is %d\n", computer.getFromMemory(0));
        System.out.printf("noun is %d and verb is %d; final answer is %d\n", 33, 76, 100 * 33 + 76);
    }
}
