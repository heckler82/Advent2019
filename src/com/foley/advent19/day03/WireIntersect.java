package com.foley.advent19.day03;

import com.foley.advent19.AdventMaster;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class description goes here
 *
 * @author Evan Foley
 * @version 03 Dec 2019
 */
public class WireIntersect extends AdventMaster {
    /**
     * Creates a new wire intersect object
     *
     * @param fileName The name of the input file
     */
    public WireIntersect(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the task for this day
     */
    protected void task() {
        Map<Point, Integer> line1 = getLinePoints(input[0]);
        Map<Point, Integer> line2 = getLinePoints(input[1]);

        line2.keySet().retainAll(line1.keySet());
        int closestDist = Integer.MAX_VALUE;
        int fastestIntersect = Integer.MAX_VALUE;
        for(Point p : line2.keySet()) {
            int manDist = Math.abs(p.x) + Math.abs(p.y);
            int intersect = line2.get(p) + line1.get(p);
            fastestIntersect = intersect < fastestIntersect ? intersect : fastestIntersect;
            closestDist = manDist < closestDist ? manDist : closestDist;
        }

        System.out.printf("The closest intersection occurs %d manhattan distance away\n", closestDist);
        System.out.printf("The fastest intersection occurs %d steps away\n", fastestIntersect);
    }

    /**
     * Gets all integer points along this polyline given a set of directions and distance
     *
     * @param input The set of distances and directions
     * @return The integer points along the polyline
     */
    private Map<Point, Integer> getLinePoints(String input) {
        Map<Point, Integer> map = new HashMap<>();
        int x = 0;
        int y = 0;
        String[] values = input.split(",");
        int pathDist = 0;
        for(String element : values) {
            int xDir = 0;
            int yDir = 0;
            switch(element.charAt(0)) {
                case 'R':
                    xDir = 1;
                    break;
                case 'L':
                    xDir = -1;
                    break;
                case 'U':
                    yDir = 1;
                    break;
                case 'D':
                    yDir = -1;
            }
            int dist = Integer.parseInt(element.substring(1));
            for(int i = 0; i < dist; i++) {
                x += xDir;
                y += yDir;
                map.put(new Point(x, y), ++pathDist);
            }
        }
        return map;
    }
}
