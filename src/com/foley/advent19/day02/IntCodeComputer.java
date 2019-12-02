package com.foley.advent19.day02;

import com.foley.advent19.AdventMaster;

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
        // Format input to integer array
        int[] opcodes = convertToIntArray(input[0].split(","));
        int noun = 33;
        int verb = 76;

        // Prep values 1 and 2
        opcodes[1] = noun;
        opcodes[2] = verb;

        int currentInstruction = 0;
        int pointer = 0;

        // Loop until ending opcode is reached
        while((currentInstruction = opcodes[pointer]) != 99) {
            // Get values to operate on
            int param1 = opcodes[pointer + 1];
            int param2 = opcodes[pointer + 2];
            int resultAddr = opcodes[pointer + 3];

            // Perform addition or multiplication instruction
            switch(currentInstruction) {
                case 1:
                    opcodes[resultAddr] = opcodes[param1] + opcodes[param2];
                    break;
                case 2:
                    opcodes[resultAddr] = opcodes[param1] * opcodes[param2];
                    break;
            }
            // Advance the pointer to the next instruction
            pointer += 4;
        }
        System.out.printf("The value at position 0 is %d\n", opcodes[0]);
        System.out.printf("noun is %d and verb is %d; final answer is %d\n", noun, verb, 100 * noun + verb);
    }

    /**
     * Converts the given string array to an integer array
     *
     * @param split The input array
     * @return The new int array
     */
    private int[] convertToIntArray(String[] split) {
        int[] newArr = new int[split.length];
        for(int i = 0; i < split.length; i++) {
            newArr[i] = Integer.parseInt(split[i]);
        }
        return newArr;
    }
}
