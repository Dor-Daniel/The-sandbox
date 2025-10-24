package Geometry;

import Methods.Methods;
import Methods.Const;
import Methods.ExceptionCheck;

/**
 * The type Line.
 */
public class Line {

    // local variables
    private final Point start, end;
    private Object slope = null;
    private double constB;

    //Constructors

    /**
     * Instantiates a new Line.
     *
     * @param start the start point
     * @param end   the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        initiateSlope();
        initiateConstB();
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x coordinate of the start point
     * @param y1 the y coordinate of the start point
     * @param x2 the x coordinate of the end point
     * @param y2 the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        initiateSlope();
        initiateConstB();
    }

    // Getters

    /**
     * Gets end.
     *
     * @return the end point
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Gets start.
     *
     * @return the start point
     */
    public Point getStart() {
        return start;
    }

    /**
     * Gets const b.
     * the constB is the y coordinate that this line intersect the y-axis
     * in case which this line is perpendicular the constB will be the x
     * coordinate of the intersection with the x-axis.
     *
     * @return the const b
     */
    public double getConstB() {
        return constB;
    }

    /**
     * Gets slope.
     * the slope is equals to the (y2-y1)/(x2-x1), in case the line is
     * perpendicular the slope will be null;
     *
     * @return the slope
     */
    public Object getSlope() {
        return slope;
    }

    // Overrides toString and equals methods

    @Override
    public String toString() {
        return " Start = " + this.start.toString() + " ,"
                + " End = " + this.end.toString();
    }

    /**
     * Equals boolean.
     *
     * @param other the other line
     * @return the boolean
     */
    public boolean equals(Line other) {
        return this.start.equals(other.getStart()) &&
                this.end.equals(other.getEnd());

    }

    // Line information methods

    /**
     * Length double.
     * calculate the length of this line
     *
     * @return the double
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Middle point.
     * calculate the middle point of this line
     *
     * @return the point middle
     */
    public Point middle() {
        return new Point(Methods.AVERAGE(this.start.getX(), this.end.getX()),
                Methods.AVERAGE(this.start.getY(), this.end.getY()));
    }

    /**
     * Intersection with point.
     *
     * @param other the other line
     * @return the point of intersection
     */
    public Point intersectionWith(Line other) {

        // if the lines intersect
        if (isIntersecting(other)) {

            // if both lines perpendicular
            if (ExceptionCheck.IS_NULL(this.slope) &&
                    ExceptionCheck.IS_NULL(other.getSlope())) {
                return intersectionPointBothPerpendicular(other);
            }

            // if only this line is perpendicular
            else if (ExceptionCheck.IS_NULL(this.slope)) {
                return intersectionPointOnlyOnePerpendicular(this, other);
            }

            // if only other is perpendicular
            else if (ExceptionCheck.IS_NULL(other.getSlope())) {
                return intersectionPointOnlyOnePerpendicular(other, this);
            }

            // if both have the same slope and none perpendicular
            else if (Methods.EQUALS((double) this.slope,
                    (double) other.getSlope())) {
                return intersectionPointHaveTheSameSlope(other);
            }

            // line intersect and have nothing in common and none perpendicular.
            else {
                return intersectionPoint(other);
            }
        }
        return null;
    }

    /**
     * Closest intersection to start of line point.
     * calculate all the intersection point between this line and the other
     * rectangle and return the closest point.
     *
     * @param rect the other rectangle
     * @return the point closest to start of this line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        // initiate list of the intersection points
        java.util.List<Point> pointList = rect.intersectionPoints(this);

        // if this line does not intersect the rectangle
        if (pointList.isEmpty()) return null;

        // initiate the closest point to the start of the list
        Point closest = pointList.get(Const.START_INDEX);

        // for every point in the list
        for (Point intersection : pointList) {

            // if this point is closest to start of this line so far
            if (Methods.SMALLER_OR_EQUALS(intersection.distance(this.start),
                    closest.distance(this.start))) closest = intersection;
        }
        return closest;
    }

    // Boolean information methods

    /**
     * Is intersecting boolean.
     * return true if this line and other intersect.
     *
     * @param other the other line
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {

        // both lines are perpendicular
        if (ExceptionCheck.IS_NULL(other.getSlope()) &&
                ExceptionCheck.IS_NULL(this.slope)) {

            // true if both on the same equation and intersect.
            // calculate if intersect by comparing the distance between the
            // lines middles and the size of the lines.
            return Methods.EQUALS(this.start.getX(), other.start.getX()) &&
                    Methods.SMALLER_OR_EQUALS(this.middle().distance(other.middle()),
                            Methods.AVERAGE(this.length(), other.length()));

        }

        // this line only is perpendicular
        else if (ExceptionCheck.IS_NULL(this.slope)) {

            // point p intersection of the lines equations
            Point p = new Point(this.start.getX(),
                    this.start.getX() * (double) other.getSlope() +
                            other.getConstB());

            // true if point p on both lines
            return other.isPointOnLine(p) && this.isPointOnLine(p);
        }

        // other line only is perpendicular
        else if (ExceptionCheck.IS_NULL(other.getSlope())) {

            // point p intersection of the lines equations
            Point p = new Point(other.getStart().getX(),
                    other.getStart().getX() * (double) this.slope + this.constB);

            // true if point p on both lines
            return other.isPointOnLine(p) && this.isPointOnLine(p);
        }

        // both lines have the same slope
        else if (Methods.EQUALS((double) this.slope, (double) other.getSlope())) {

            // true if lines intersect.
            // calculate if the line intersect by comparing the distance
            // between the lines middles and the average size of the lines.
            return Methods.SMALLER_OR_EQUALS(this.middle().distance(other.middle()),
                    Methods.AVERAGE(this.length(), other.length()));
        }

        // lines have nothing in common and none perpendicular
        else {

            // x,y coordinates of the intersection between the lines equations.
            double x = (this.constB - other.getConstB()) /
                    ((double) other.getSlope() - (double) this.slope);
            double y = (double) this.slope * x + this.constB;

            // point p of intersection
            Point p = new Point(x, y);

            //true if p is on both lines
            return other.isPointOnLine(p) && this.isPointOnLine(p);

        }

    }

    /**
     * Is point on this line boolean.
     * return true if other point is on this line.
     *
     * @param point the point other
     * @return the boolean
     */
    public boolean isPointOnLine(Point point) {

        // if this line is perpendicular
        if (this.slope == null) {

            // true if this point is on this line equation
            return Methods.EQUALS(this.start.getX(), point.getX()) &&
                    // true if the distance between the point and this middle
                    // is less than half of this line's length.
                    Methods.SMALLER_OR_EQUALS(point.distance(middle()),
                            length() / Const.TWO);
        }

        // true if this point is on this line equation
        boolean isOnLineEquation =
                Methods.EQUALS(point.getX() * (double) this.slope +
                        this.constB, point.getY());

        // true if the distance between the point and this middle is less
        // than or equals to half of this line's length.
        return isOnLineEquation && Methods.SMALLER_OR_EQUALS(point.distance(middle()),
                length() / Const.TWO);
    }

    // Private methods

    // calculate the slope of this line
    private void initiateSlope() {

        // if this line is perpendicular
        if (Methods.EQUALS(this.start.getX(), this.end.getX())) return;

        // else: calculate slope by the formula m = (y2-y1) / (x2-x1)
        this.slope = (this.start.getY() - this.end.getY()) /
                (this.start.getX() - this.end.getX());


    }

    // calculate the constB
    private void initiateConstB() {

        // if this line is perpendicular: constB = intersection with x-axis.
        if (this.slope == null) this.constB = this.start.getX();

            // else: constB = intersection with the y-axis.
        else if (this.start != null) this.constB = this.start.getY() -
                this.start.getX() * (double) this.slope;

    }

    // calculate the intersection point if both lines are perpendicular
    private Point intersectionPointBothPerpendicular(Line other) {

        // if the lines continuing one the other
        if (Methods.EQUALS(this.middle().distance(other.middle()),
                Methods.AVERAGE(this.length(), other.length()))) {

            // if start = start or start = end
            if (this.start.equals(other.start) || this.start.equals(other.end))
                return this.start;

            // if end = start or end = end
            if (this.end.equals(other.start) || this.end.equals(other.end))
                return this.end;
        }

        // if this line length is 0
        if (this.start.equals(this.end)) return this.start;

        // if other line length is 0
        if (other.getStart().equals(other.getEnd())) return other.getStart();
        return null;
    }

    // calculate the intersection point if only one is perpendicular
    private Point intersectionPointOnlyOnePerpendicular(Line perpendicularLine, Line line) {

        // return the point of intersection.
        return new Point(perpendicularLine.getStart().getX(),
                (double) line.slope * perpendicularLine.getStart().getX() + line.constB);
    }

    // calculate the intersection point if both line have the same slope
    private Point intersectionPointHaveTheSameSlope(Line other) {

        // if the distance between lines middles is equal to the average
        // of the lengths of those lines.
        if (Methods.EQUALS(this.middle().distance(other.middle()),
                Methods.AVERAGE(this.length(), other.length()))) {

            // if start = start or start = end
            if (this.start.equals(other.start) || this.start.equals(other.end))
                return this.start;

            // if end = start or end = end
            if (this.end.equals(other.start) || this.end.equals(other.end))
                return this.end;

        }

        // if this line length is 0
        if (this.start.equals(this.end)) return this.start;

        // if other line length is 0
        if (other.getStart().equals(other.getEnd())) return other.getStart();
        return null;

    }

    // calculate the intersection point if lines have nothing in common and
    // none is perpendicular
    private Point intersectionPoint(Line other) {

        // x,y coordinates of the intersection between the lines equations.
        double x = (this.constB - other.getConstB()) /
                ((double) other.getSlope() - (double) this.slope);
        double y = (double) this.slope * x + this.constB;

        // return point of intersection
        return new Point(x, y);
    }

}
//    This is a Java class named Line, which belongs to the Geometry package.
//    It represents a line in two-dimensional space defined by two points, start and end.
//
//        The class has the following attributes:
//
//        start and end: private Point variables that represent the two endpoints of the line.
//        slope: private Object variable that represents the slope of the line.
//        If the line is perpendicular to the x-axis, then the slope is null.
//        constB: private double variable that represents the value of y where the line
//        intersects with the y-axis. If the line is perpendicular to the y-axis, then the value of
//        constB represents the value of x where the line intersects with the x-axis.
//        The class has the following constructors:
//
//        Line(Point start, Point end): Creates a new Line object with the given start and end points.
//        Line(double x1, double y1, double x2, double y2): Creates a new
//        Line object with the given (x1,y1) and (x2,y2) points.
//        The class has the following methods:
//
//        getStart(): Returns the start point of the line.
//        getEnd(): Returns the end point of the line.
//        getSlope(): Returns the slope of the line.
//        getConstB(): Returns the constB value of the line.
//        equals(Line other): Returns true if this line is equal to the given other line,
//        meaning that they have the same start and end points.
//        length(): Calculates and returns the length of the line.
//        middle(): Calculates and returns the middle point of the line.
//        intersectionWith(Line other): Calculates and returns the intersection
//        point between this line and the given other line.
//        closestIntersectionToStartOfLine(Rectangle rect): Calculates all intersection points
//        between this line and the given rect rectangle,
//        and returns the closest one to the start point of this line.
//        isIntersecting(Line other): Returns true if this line intersects with the given other line.
//        toString(): Returns a string representation of this line.
//





