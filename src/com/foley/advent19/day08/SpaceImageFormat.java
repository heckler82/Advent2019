package com.foley.advent19.day08;

import com.foley.advent19.AdventMaster;
import com.foley.util.Formatting;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Decodes the password
 *
 * @author Evan Foley
 * @version 08 Dec 2019
 */
public class SpaceImageFormat extends AdventMaster {
    /**
     * Creates a new space image format object
     *
     * @param fileName The name of the input file
     */
    public SpaceImageFormat(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the task for this day
     */
    protected void task() {
        // Data parameters
        int[] data = convertToIntArray(input[0]);
        int width = 25;
        int height = 6;
        int depth = data.length / (width * height);

        // Tracking variables
        int fewestZero = Integer.MAX_VALUE;
        int numOnes;
        int numTwos;
        int currZero;
        int ans = 0;

        // Image array
        int[][] image = new int[height][width];
        for(int[] row : image) {
            Arrays.fill(row, 2);
        }

        // Parse the input stream
        for(int z = 0; z < depth; z++) {
            // Reset trackers
            currZero = 0;
            numOnes = 0;
            numTwos = 0;
            // Prep a new layer
            int[][] layer = new int[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Get current value, update trackers, and add to layer
                    int val = data[(z * height * width) + (y * width) + x];
                    if(val == 0) {
                        currZero++;
                    } else if(val == 1) {
                        numOnes++;
                    } else if(val == 2) {
                        numTwos++;
                    }
                    layer[y][x] = val;
                }
            }

            // Determine visible pixels
            for(int y = 0; y < image.length; y++) {
                for(int x = 0; x < image[y].length; x++) {
                    if(image[y][x] == 2) {
                        image[y][x] = layer[y][x];
                    }
                }
            }

            // Determine if current zero count is the minimum, and update answer if so
            if(currZero < fewestZero) {
                fewestZero = currZero;
                ans = numOnes * numTwos;
            }
        }
        // Output
        System.out.printf("The answer is %d\n", ans);
        int scale = 30;
        BufferedImage img = new BufferedImage(scale * (width + 2), scale * (height + 2), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        for(int y = 0; y < image.length; y++) {
            for(int x = 0; x < image[y].length; x++) {
                int val = image[y][x];
                g.setColor(val == 0 ? Color.BLACK : Color.WHITE);
                g.fillRect((x + 1) * scale, (y + 1) * scale, scale, scale);
                System.out.print((val == 0 ? ' ' : '#') + " ");
            }
            System.out.println();
        }
        g.dispose();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SimpleImageDisplay sid = new SimpleImageDisplay(img, "Advent of Code 2019 - Day 08");
            }
        });
    }

    /**
     * Converts a stream of characters into an integer array
     *
     * @param stream The character stream
     * @return An array of integers
     */
    private int[] convertToIntArray(String stream) {
        int[] result = new int[stream.length()];
        for(int i = 0; i < stream.length(); i++) {
            result[i] = stream.charAt(i) - 48;
        }
        return result;
    }

    /**
     * Displays an image in a frame
     */
    class SimpleImageDisplay {
        JFrame frame;

        /**
         * Creates a new image display
         *
         * @param img The image to display
         * @param titleString The title to display
         */
        public SimpleImageDisplay(Image img, String titleString) {
            // Create the display window
            frame = new JFrame(titleString);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBackground(Color.BLACK);
            // Set image to label icon and add to frame
            ImageIcon icon = new ImageIcon(img);
            JLabel jLabel = new JLabel();
            jLabel.setIcon(icon);
            frame.getContentPane().add(jLabel, BorderLayout.CENTER);
            // Pack and display frame
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}
