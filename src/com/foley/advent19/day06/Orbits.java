package com.foley.advent19.day06;

import com.foley.advent19.AdventMaster;
import com.foley.util.graph.Graph;
import com.foley.util.graph.Searchable;
import com.foley.util.graph.DijkstraPathfinder;

/**
 * Class description goes here
 *
 * @author Evan Foley
 * @version 06 Dec 2019
 */
public class Orbits extends AdventMaster {
    private Graph<String> graph;

    /**
     * Creates a new orbit object
     *
     * @param fileName The name of the input file
     */
    public Orbits(String fileName) {
        super(fileName);
        graph = new Graph<>();
    }

    @Override
    /**
     * Accomplishes the task for today
     */
    protected void task() {
        // Create the graph
        for(String s : input) {
            String body = s.substring(0, 3);
            String body2 = s.substring(4);
            graph.addVertex(body);
            graph.addVertex(body2);
            graph.addEdge(body, body2);
        }

        // Find all path costs from COM
        Searchable<String> allPaths = new DijkstraPathfinder<>(graph);
        allPaths.searchGraphFrom("COM");

        int totalOrbits = 0;
        for(String key : graph.getVertices()) {
            totalOrbits += allPaths.getPathCostTo(key);
        }
        System.out.printf("The total number of orbits from COM is %d\n", totalOrbits);

        // Find path cost from me to santa
        Searchable<String> toSanta = new DijkstraPathfinder<>(graph);
        toSanta.searchGraphTo("YOU", "SAN");

        int movesRequired = toSanta.getPathCostTo("SAN") - 2; // Substract 2 to account for starting and ending
        System.out.printf("The total number of orbit transfers to Santa is %d\n", movesRequired);
    }
}
