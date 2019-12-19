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
        Map<Integer, Map<Double, Integer>> astList = new HashMap<>();

        int id = 0;
        int y = 0;
        for(String str : input) {
            // Find all asteroids on this row
            for(int x = 0; x < str.length(); x++) {
                if(str.charAt(x) == '#') {
                   Point p = new Point(x, y);
                   asteroids.put(id, p);
                   astList.put(id, new HashMap<>());
                   id++;
                }
            }
            // Increase the y value
            y++;
        }

        // Find out how many other asteroids an asteroid can see, and store the most
        int mostSeen = Integer.MIN_VALUE;
        int bestLocation = -1;
        for(int i : asteroids.keySet()) {
            Point p = asteroids.get(i);
            // Check all other asteroids
            for(int j : asteroids.keySet()) {
                // Do not check against self
                if(j != i) {
                    Point p2 = asteroids.get(j);
                    // Get the angle between the two asteroids, and clamp to 0 - 360
                    double rads = Math.atan2(p2.x - p.x, p.y - p2.y);
                    double angle = Math.toDegrees(rads);
                    if(angle < 0) {
                        angle += 360;
                    }
                    // Place the asteroid by angle if the angle doesn't already have one, otherwise take the closer asteroid
                    if(!astList.get(i).containsKey(angle)) {
                        astList.get(i).put(angle, j);
                    } else {
                        int ast = astList.get(i).get(angle);
                        Point a = asteroids.get(ast);
                        if(p.x == 11 && p.y == 13 && p2.x == 11 && p2.y == 12) {
                            int test = 0;
                        }
                        int distSq = ((a.x - p.x) * (a.x - p.x)) + ((a.y - p.y) * (a.y - p.y));
                        int myDistSq = ((p2.x - p.x) * (p2.x - p.x)) + ((p2.y - p.y) * (p2.y - p.y));
                        if(myDistSq < distSq) {
                            astList.get(i).replace(angle, j);
                        }
                    }
                }
            }
            // Determine if this asteroid can see more than the current max, and update if so
            if(astList.get(i).keySet().size() > mostSeen) {
                mostSeen = astList.get(i).keySet().size();
                bestLocation = i;
            }
        }
        // Get the angles and asteroids the best location can see
        Map<Double, Integer> bestCanSee = astList.get(bestLocation);
        // Get the angles and sort them from smallest to largest
        List<Double> angles = new ArrayList<>(bestCanSee.keySet());
        Collections.sort(angles);

        int lastVaporized = bestCanSee.get(angles.get(199));

        // Get the last asteroid that was vaporized*/
        Point p = asteroids.get(lastVaporized);

        System.out.printf("The best location can see %d other asteroids\n", mostSeen);
        System.out.printf("The answer to part 2 is %d\n", p.x * 100 + p.y);
    }
}
