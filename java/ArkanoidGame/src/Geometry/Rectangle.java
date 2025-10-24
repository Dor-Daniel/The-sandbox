package Geometry;

import Methods.Methods;
import Methods.ExceptionCheck;

import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {

    // local variables
    private final Point upperLeft;
    private final int width, height;

    // Constructors

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left point
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Getters

    /**
     * Gets height.
     *
     * @return the height of this rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets width.
     *
     * @return the width of this rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Get upper right point.
     *
     * @return the point upper right.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY());
    }

    /**
     * Get lower left point.
     *
     * @return the point lower left
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(),
                this.upperLeft.getY() - this.height);
    }

    /**
     * Get lower right point.
     *
     * @return the point lower right
     */
    public Point getLowerRight() {
        return new Point(this.getUpperRight().getX(),
                this.getLowerLeft().getY());
    }

    /**
     * Get rectangle x edges list.
     *
     * @return the list of the edges of this rectangle
     */
    public java.util.List<Line> getRectangleXEdges() {

        // initiate new Line list
        java.util.List<Line> lines = new ArrayList<>();

        // inserting the side lines
        lines.add(new Line(this.getUpperRight(), this.getLowerRight()));
        lines.add(new Line(this.getUpperLeft(), this.getLowerLeft()));

        return lines;
    }

    /**
     * Get rectangle y edges list.
     *
     * @return the list
     */
    public java.util.List<Line> getRectangleYEdges() {

        // initiate Line list
        java.util.List<Line> lines = new ArrayList<>();

        // insert the top and bottom edges
        lines.add(new Line(this.getUpperLeft(), this.getUpperRight()));
        lines.add(new Line(this.getLowerLeft(), this.getLowerRight()));

        return lines;
    }

    // Override toString and equals methods
    public String toString() {
        return "Upper left point = " + this.upperLeft.toString() +
                ", width = " + this.width + ", height = " + this.height;
    }

    /**
     * Equals boolean.
     *
     * @param other the other rectangle
     * @return the boolean
     */
    public boolean equals(Rectangle other) {
        return this.upperLeft.equals(other.getUpperLeft()) &&
                this.width == other.getWidth() &&
                this.height == other.getHeight();
    }

    // information methods

    /**
     * Intersection points list.
     * return the list of the intersection points between this rectangle and
     * other line
     *
     * @param line the line
     * @return the list
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        // initiate Point list
        java.util.List<Point> points = new ArrayList<>();

        // for each edge in this rectangle side edges
        for (Line edge : this.getRectangleXEdges()) {

            // if there exist a single point of intersection between the line
            // and this current edge
            if (!ExceptionCheck.IS_NULL(edge.intersectionWith(line)))
                points.add(edge.intersectionWith(line));
        }

        // for each edge in this rectangle top and bottom edges
        for (Line edge : this.getRectangleYEdges()) {

            // if there exist a single point of intersection between the line
            // and this current edge
            if (!ExceptionCheck.IS_NULL(edge.intersectionWith(line)))
                points.add(edge.intersectionWith(line));

        }

        // return the Point list
        return Methods.POINT_LIST_TO_SET(points);
    }


}
//    This class defines a Rectangle class that represents a rectangle in a 2D plane.
//        The class has private variables for the upper left point of the rectangle,
//        its width, and its height.
//        The class has a constructor that takes these variables as arguments.
//        The class has getter methods for the width, height,
//        and upper left point of the rectangle.
//        The class has methods for getting the upper right, lower left,
//        and lower right points of the rectangle.
//        The class has methods for getting the list of edges of the rectangle,
//        both in the x and y directions.
//        The class overrides the toString and equals methods.
//        The class has a method for getting the list of intersection points
//        between the rectangle and a given line.
