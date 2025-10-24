package GameObjects;

import Geometry.Point;
import Methods.Const;

/**
 * The type Velocity.
 */
public class Velocity {

    // local variables
    private final double dx, dy;

    // Constructors

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    // Getters


    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    // public methods

    /**
     * Apply to point point.
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        //change the center point of the ball simulating ball movement
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //calculate the dx part of the speed
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        //calculate the dy part of the speed
        double dy = Math.cos(Math.toRadians(angle)) * speed * Const.NEGATIVE;
        //return Velocity by dx and dy
        return new Velocity(dx, dy);
    }

    public String toString() {
        return "dx = " + dx + ", dy = " + dy;
    }
}
// This class represents the velocity of a game object.
//        Velocity is defined by a change in x and y coordinates over time.
//        The class has two private instance variables, dx and dy,
//        which represent the change in x and y coordinates, respectively.
//        The class has a constructor that takes in two doubles,
//        representing the dx and dy values, and initializes the instance variables.
//        The class has two getter methods, getDx() and getDy(),
//        which return the values of the instance variables.
//        The class has a public method called applyToPoint(),
//        which takes in a Point object and returns a new Point object
//        with the x and y values adjusted by the dx and dy values of the Velocity object.
//        The class has a public static method called fromAngleAndSpeed(),
//        which takes in an angle and speed and returns a new Velocity object
//        with dx and dy values calculated from the angle and speed.
//        The class overrides the toString() method to
//        return a string representation of the Velocity object.
