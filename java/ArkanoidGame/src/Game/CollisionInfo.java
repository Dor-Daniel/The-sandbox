package Game;

import Geometry.Line;
import Geometry.Point;
import Interfaces.Collidable;
import Methods.ExceptionCheck;
import Methods.Methods;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Collision info.
 */
public class CollisionInfo {

    // local variables

    private Point collisionPoint;
    private Collidable collisionObject;
    private final Line trajectory;
    private final List<Collidable> collidableList;

    // Constructors

    /**
     * Instantiates a new Collision info.
     *
     * @param trajectory     the trajectory line of the ball movement
     * @param collidableList the collidable list of the game environment
     */
    public CollisionInfo(Line trajectory, List<Collidable> collidableList) {
        this.trajectory = trajectory;
        this.collidableList = collidableList;
        initiateCollisionPoint();
        initiateCollisionObject();
    }

    // Getters

    /**
     * Gets collision object.
     *
     * @return the collision object.
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }

    /**
     * Gets collision point.
     *
     * @return the collision point
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    // private methods

    // calculating the collision point
    private void initiateCollisionPoint() {

        // initiate Point list
        List<Point> pointsOfIntersection = new ArrayList<>();

        // for each collidable object in the game
        for (Collidable c : this.collidableList) {

            // if there exist an intersection point between the current
            // collidable object and this trajectory line
            if (!c.getCollisionRectangle().intersectionPoints(this.trajectory).isEmpty())
                // adding the closest intersection point to the list
                pointsOfIntersection.add(this.trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()));

        }

        // this collision point = to the closest point to the start of
        // trajectory from the intersection points in the list
        this.collisionPoint = Methods.CLOSEST_POINT(pointsOfIntersection,
                this.trajectory.getStart());
    }

    // calculate the collision object
    private void initiateCollisionObject() {

        // for each collidable in the game
        for (Collidable c : this.collidableList) {

            // if the collision point is on the current collidable c
            if (Methods.IS_POINT_ON_LINES(c.getCollisionRectangle().getRectangleXEdges(), this.collisionPoint) ||
                    Methods.IS_POINT_ON_LINES(c.getCollisionRectangle().getRectangleYEdges(), this.collisionPoint)) {
                // set the collidable object to current
                this.collisionObject = c;
                return;
            }
        }
    }

    // public methods

    /**
     * Is hit boolean.
     * false if the collision point is null
     *
     * @return the boolean
     */
    public boolean isHit() {
        return !ExceptionCheck.IS_NULL(this.collisionPoint);
    }

}
//    The CollisionInfo class represents information about a collision that occurred
//    between a Ball and a Collidable object in the game. It contains the collision
//    point and the Collidable object that was hit by the ball.
//
//        The class has the following variables:
//
//        collisionPoint - a Point object representing the collision point.
//        collisionObject - a Collidable object representing the object that was hit by the ball.
//        trajectory - a Line object representing the trajectory line of the ball movement.
//        collidableList - a list of Collidable objects representing
//        the collidable objects in the game environment.
//        The class has the following methods:
//
//        initiateCollisionPoint() - a private method that calculates the collision
//        point by checking for intersection points between the trajectory line and
//        each collidable object in the game, and selecting the closest point to the
//        start of the trajectory from the intersection points.
//        initiateCollisionObject() - a private method that calculates the collision
//        object by checking if the collision point is on each collidable object in the game.
//        getCollisionObject() - a public method that returns the collision object.
//        getCollisionPoint() - a public method that returns the collision point.
//        isHit() - a public method that returns true if a collision occurred
//        (i.e., the collision point is not null), and false otherwise.
