import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Brute force solution. To run: java brute.java < input.txt
 *
 * @author Magnus Nielsen
 *         Largely based on existing C++-laborations by Tommy Olsson and Filip
 *         Strömbäck.
 */
public class Fast {
    /**
     * Clear the window and paint all the Points in the plane.
     *
     * @param frame  - The window / frame.
     * @param points - The points to render.
     */
    private static void render(JFrame frame, ArrayList<Point> points) {
        frame.removeAll();
        frame.setVisible(true);

        for (Point p : points) {
            p.paintComponent(frame.getGraphics(), frame.getWidth(), frame.getHeight());
        }
    }

    /**
     * Draw a line between two points in the window / frame.
     *
     * @param frame - The frame / window in which you wish to draw the line.
     * @param p1    - The first Point.
     * @param p2    - The second Point.
     */
    private static void renderLine(JFrame frame, Point p1, Point p2) {
        p1.lineTo(p2, frame.getGraphics(), frame.getWidth(), frame.getHeight());
    }

    /**
     * Read all the points from the buffer in the input scanner.
     *
     * @param input - Scanner containing a buffer from which to read the points.
     * @return ArrayList<Point> containing all points defined in the file / buffer.
     */
    private static ArrayList<Point> getPoints(Scanner input) {
        int count = input.nextInt();
        ArrayList<Point> res = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            res.add(new Point(input.nextInt(), input.nextInt()));
        }

        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame;
        Scanner input = null;
        File f;
        ArrayList<Point> points;

        if (args.length != 1) {
            System.out.println("Usage: java Brute <input.txt>\n" +
                    "Replace <input.txt> with your input file of preference, and possibly the path.\n" +
                    "Ex: java Brute data/input1000.txt");
            System.exit(0);
        }

        // Opening the file containing the points.
        f = new File(args[0]);
        try {
            input = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open file. Try giving a correct file / file path.");
        }

        // Creating frame for painting.
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(512, 512));
        frame.setPreferredSize(new Dimension(512, 512));

        // Getting the points and painting them in the window.
        points = getPoints(input); // n
        render(frame, points); // n

        // Sorting points by natural order (lexicographic order). Makes finding end
        // points of line segments easy.
        Collections.sort(points, new NaturalOrderComparator()); // n

        long start = System.currentTimeMillis();

        //////////////////////////////////////////////////////////////////////
        // Your code goes here. Draw any lines you find using the function //
        // renderLine. Look at Brute.java if you are unsure if how to use //
        // any functions. Good luck! //
        //////////////////////////////////////////////////////////////////////

        //n^2log(n)???

        ArrayList<Point> arrayLutningar = new ArrayList<>(); // 1
        for(Point r : points){ // n

             arrayLutningar.add(r); // 1

        }

        for (Point o : points) { // n

            Point origo = o; // 1
            
            arrayLutningar.remove(o); // 1
            

            
            Collections.sort(arrayLutningar, new SlopeOrderComparator(origo)); // nlog(n)

            for (int i = 0; i < arrayLutningar.size(); i++) { // n-1
                try {
                    if (origo.slopeTo(arrayLutningar.get(i)) == origo.slopeTo(arrayLutningar.get(i + 1))
                            && origo.slopeTo(arrayLutningar.get(i)) == origo.slopeTo(arrayLutningar.get(i + 2))) { // 1 
                                
                        renderLine(frame, origo, arrayLutningar.get(i + 2)); // 1

                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
            arrayLutningar.add(o); // 1
        }

        long end = System.currentTimeMillis(); // 1
        System.out.println("Computing all the line segments took: " + (end - start) + " milliseconds."); // 1
    }

    private static class SlopeOrderComparator implements Comparator<Point> {

        private Point referencePoint;

        public SlopeOrderComparator(Point referencePoint) {
            this.referencePoint = referencePoint;
        }

        @Override
        public int compare(Point a, Point b) {
            double slopeA = referencePoint.slopeTo(a);
            double slopeB = referencePoint.slopeTo(b);

            if (slopeA < slopeB) {
                return -1;
            } else if (slopeA > slopeB) {
                return 1;
            } else {
                return 0;
            }
        }

    }

    /**
     * Comparator class. Used to tell Collections.sort how to compare
     * objects of a non standard class. When you make your own
     * Comparator, keep in mind that this is a class like any other,
     * and can contain data and other methods if you deem it useful.
     */
    private static class NaturalOrderComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (a.greaterThan(b)) {
                // a is "greater" than b.
                return 1;
            } else if (a.lessThan(b)) {
                // a is "less" than b.
                return -1;
            }
            // our two points are equal.
            return 0;
        }
    }
}
