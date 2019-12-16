package com.foley.advent19.day10;

import com.foley.advent19.AdventMaster;

import java.awt.Point;
import java.util.*;

/**
 * Asteroid monitoring
 *
 * @author Evan Foley
 * @version 15 Dec 2019
 */
public class AsteroidWatch extends AdventMaster {
    /**
     * Creates a new asteroid watch
     *
     * @param fileName The name of the input file
     */
    public AsteroidWatch(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the task for this day
     */
    protected void task() {
        Map<Integer, Point> asteroids = new HashMap<>();
        Map<Integer, Map<Double, Stack<Integer>>> astList = new HashMap<>();

        int id = 0;
        int y = 0;
        for(String str : input) {
            // Find all asteroids on this row
            for(int x = 0; x < str.length(); x++) {
                if(str.charAt(x) == '#') {
                   Point p = new Point(x, y);
                   asteroids.put(id, p);
                   astList.put(id++, new HashMap<>());
                }
            }
            // Increase the y value
            y++;
        }

        // Find out how many other asteroids an asteroid can see, and store the most
        int mostSeen = Integer.MIN_VALUE;
        int lastVaporized = -1;
        int bestLocation = -1;
        for(int i : asteroids.keySet()) {
            Point p = asteroids.get(i);
            // Check all other asteroids
            for(int j : asteroids.keySet()) {
                // Do not check against self
                if(i != j) {
                    Point p2 = asteroids.get(j);
                    // Get the angle between the two asteroids, and clamp to 0 - 360
                    double angle = Math.toDegrees(Math.atan2(p2.y - p.y, p2.x - p.x));
                    angle = angle + Math.ceil(-angle / 360) * 360;
                    // Place the asteroid into the list of those that are seen by the current location
                    if(astList.get(i).get(angle) == null) {
                        astList.get(i).put(angle, new Stack<>());
                    }
                    astList.get(i).get(angle).push(j);
                }
            }
            // Determine if this asteroid can see more than the current max, and update if so
            if(astList.get(i).keySet().size() > mostSeen) {
                mostSeen = astList.get(i).keySet().size();
                bestLocation = i;
            }
        }
        // Get the angles and asteroids the best location can see
        Map<Double, Stack<Integer>> bestCanSee = astList.get(bestLocation);
        // Get the angles and sort them from smallest to largest
        List<Double> angles = new ArrayList<>(bestCanSee.keySet());
        Collections.sort(angles);

        lastVaporized = bestCanSee.get(angles.get(200)).pop();

        /**

        // Find out the 200th asteroid vaporized
        int vaporized = 0;
        int listIndex = 0;
        // Continue to loop until 200 asteroids have been vaporized
        while(vaporized < 200) {
            // If there are asteroids along this angle kill it
            if(!bestCanSee.get(angles.get(listIndex)).isEmpty()) {
                vaporized++;
                lastVaporized = bestCanSee.get(angles.get(listIndex)).pop();
            }
            // Advance the list index
            listIndex = (listIndex + 1) % angles.size();
        }
        // Get the last asteroid that was vaporized*/
        Point p = asteroids.get(lastVaporized);

        System.out.printf("The best location can see %d other asteroids\n", mostSeen);
        System.out.printf("The answer to part 2 is %d\n", p.x * 100 + p.y);
    }
}
