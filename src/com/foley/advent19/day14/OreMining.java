package com.foley.advent19.day14;

import com.foley.advent19.AdventMaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Stoichiometry
 *
 * @author Evan Foley
 * @version 18 Dec 2019
 */
public class OreMining extends AdventMaster {
    Map<String, Pair<Long, Long>> bases = new HashMap<>();
    Map<String, Pair<Long, List<Pair<String, Long>>>> reactions = new HashMap<>();
    Map<String, Long> excess = new HashMap<>();

    /**
     * Creates a new ore mining
     *
     * @param fileName The name of the input file
     */
    public OreMining(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the task for this day
     */
    protected void task() {
        for(String line : input) {
            String[] eq = line.split(" => ");
            String[] form = eq[0].split(", ");
            // If form is length 1, this is a base
            if(form.length == 1 && form[0].contains("ORE")) {
                // Parse out base here
                long ore = Integer.parseInt(getPattern(form[0], "\\d+"));
                long baseNeed = Integer.parseInt(getPattern(eq[1], "\\d+"));
                String base = getPattern(eq[1], "[a-zA-Z]+");
                // Create pair here
                bases.put(base, Pair.createPair(baseNeed, ore));
            } else {
                // The result
                long compoundCreated = Integer.parseInt(getPattern(eq[1], "\\d+"));
                String compound = getPattern(eq[1], "[a-zA-Z]+");
                // The reaction list
                List<Pair<String, Long>> list = new ArrayList();
                for(String str : form) {
                    long reactCount = Integer.parseInt(getPattern(str, "\\d+"));
                    String reactLabel = getPattern(str, "[a-zA-Z]+");
                    list.add(Pair.createPair(reactLabel, reactCount));
                }
                // Add the reaction to the map
                reactions.put(compound, Pair.createPair(compoundCreated, list));
            }
        }

        // Stoichiometry
        long fuel = 1L;
        long topOre = processReactant(Pair.createPair("FUEL", fuel));
        System.out.printf("It takes %d units of ore to produce 1 fuel\n", topOre);
    }

    /**
     * Processes part of the chemical reaction
     *
     * @param pair The value to process
     * @return The total amount of ore needed for this part of the chemical reaction
     */
    private long processReactant(Pair<String, Long> pair) {
        // Start at 0 needed or
        long totalOre = 0;
        // The quantity required as per the equation
        long reqQty = pair.second();
        // Determine if any excess exists, and update the required quantity if needed
        if(excess.getOrDefault(pair.first(), 0L) != 0) {
            long ex = excess.get(pair.first());
            if(reqQty < ex) {
                excess.put(pair.first(), ex - reqQty);
                reqQty = 0;
            } else {
                reqQty -= ex;
                excess.put(pair.first(), 0L);
            }
        }
        // If not a base reaction, break down until a base reaction is reached
        if(!bases.containsKey(pair.first())) {
            long reactQty = reactions.get(pair.first()).first();
            List<Pair<String, Long>> formula = reactions.get(pair.first()).second();
            // Continue to mine ore until required quantity is created
            while (reqQty > 0) {
                // Process each sub part of the chemical reaction
                for (Pair<String, Long> subPair : formula) {
                    totalOre += processReactant(subPair);
                }
                // Update the required quantity by the amount that was created by the reaction
                reqQty -= reactQty;
            }
            // Store any additional quantities as excess
            if (reqQty != 0) {
                excess.put(pair.first(), Math.abs(reqQty));
            }
        } else {
            // This is a base reaction
            long canMake = bases.get(pair.first()).first();
            long canMine = bases.get(pair.first()).second();
            // Get as much ore as needed to cover the requirement
            while (reqQty > 0) {
                reqQty -= canMake;
                totalOre += canMine;
            }
            // Store any remaining excess
            if(reqQty != 0) {
                excess.put(pair.first(), Math.abs(reqQty));
            }
        }
        return totalOre;
    }

    private String getPattern(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if(m.find()) {
            return m.group();
        }
        return null;
    }
}

class Pair<K, V> {
    K first;
    V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K first() {
        return first;
    }

    public V second() {
        return second;
    }

    public static <K, V> Pair<K, V> createPair(K first, V second) {
        return new Pair(first, second);
    }
}
