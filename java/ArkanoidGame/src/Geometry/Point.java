// A package for geometric classes
package Geometry;

// Importing necessary classes from another package
import Methods.Const;
import Methods.Methods;

/**
 * The type Point.
 */
public class Point {

    // Two private instance variables of type double to store x and y coordinates
    private final double x,y;

    /**
     * Instantiates a new Point.
     * Constructor method to initialize x and y coordinates
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     * Method to calculate the distance between two points.
     *
     * @param other the other point
     * @return the double distance
     */
    public double distance(Point other){
        // Calculating distance using the distance formula
        return Math.sqrt( Math.pow(this.x - other.getX(), Const.SQUARED) +
                Math.pow(this.y - other.getY(), Const.SQUARED) );
    }

    // Getter methods for x and y coordinates

    /**
     * Gets y.
     *
     * @return the y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Gets x.
     *
     * @return the x coordinate
     */
    public double getX() {
        return x;
    }

    // Overriding toString and equals methods

    /**
     * Method to convert a point object to a string.
     *
     * @return the string representation of the point object
     */
    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * Method to check if two point objects are equal.
     *
     * @param other the other point
     * @return a boolean indicating if the two point objects are equal
     */
    public boolean equals(Point other){
        return Methods.EQUALS(this.x, other.getX()) &&
                Methods.EQUALS(this.y, other.getY());
    }
}
