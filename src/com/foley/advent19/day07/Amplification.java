package com.foley.advent19.day07;

import com.foley.advent19.AdventMaster;
import com.foley.util.Formatting;
import com.foley.util.IntCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Rebuilds the ship's computer
 *
 * @author Evan Foley
 * @version 02 Dec 2019
 */
public class Amplification extends AdventMaster {
    /**
     * Creates a new intcode computer
     *
     * @param fileName The name of the input file
     */
    public Amplification(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the task for this day
     */
    protected void task() {
        int[] baseMemory = Formatting.convertToIntArray(input[0].split(","));
        boolean feedback = true;
        char[] sequence;
        if(feedback) {
            char[] temp = {'5', '6', '7', '8', '9'};
            sequence = temp;
        } else {
            char[] temp = {'0', '1', '2', '3', '4'};
            sequence = temp;
        }
        // Find all permutations
        List<char[]> perms = new ArrayList<>();
        heapPermutations(perms, sequence, sequence.length);

        int maxSignal = Integer.MIN_VALUE;
        for(char[] perm : perms) {
            IntCode[] amps = {new IntCode(baseMemory), new IntCode(baseMemory), new IntCode(baseMemory), new IntCode(baseMemory), new IntCode(baseMemory)};
            int result = tryPermutation(perm, amps);
            maxSignal = Math.max(maxSignal, result);
        }

        System.out.printf("Max signal sent to thrusters is %d\n", maxSignal);
    }

    /**
     * Finds the thruster output from a permutation
     *
     * @param perm The permutation
     * @param amps The amps
     * @return The output sent to the thruster
     */
    private int tryPermutation(char[] perm, IntCode[] amps) {
        // Provide initial input to each amp
        for(int i = 0; i < amps.length; i++) {
            amps[i].setInput(perm[i] - 48);
            amps[i].cycle();
        }

        // Run the amps until the ending opcode is reached
        int inSignal = 0;
        while(true) {
            for(int i = 0; i < amps.length; i++) {
                IntCode amp = amps[i];
                // Feed in the input signal
                amp.setInput(inSignal);
                // Cycle until the output instruction is reached
                while(amp.getCurrentOpcode() != 4) {
                    if(amp.getCurrentOpcode() == 99) {
                        return amps[4].getOutput();
                    }
                    amp.cycle();
                }
                // Process output signal and set to inSignal
                amp.cycle();
                inSignal = amp.getOutput();
            }
        }
    }

    /**
     * Finds all permutations of a given set of characters
     *
     * @param l The permutations list
     * @param arr The input characters
     * @param size The size of the input
     */
    private void heapPermutations(List<char[]> l, char[] arr, int size) {
        if(size == 1) {
            char[] perm = new char[arr.length];
            for(int i = 0; i < arr.length; i++) {
                perm[i] = arr[i];
            }
            l.add(perm);
        }

        for(int i = 0; i < size; i++) {
            heapPermutations(l, arr, size - 1);

            if(size % 2 == 1) {
                char temp = arr[0];
                arr[0] = arr[size - 1];
                arr[size - 1] = temp;
            } else {
                char temp = arr[i];
                arr[i] = arr[size - 1];
                arr[size - 1] = temp;
            }
        }
    }
}
