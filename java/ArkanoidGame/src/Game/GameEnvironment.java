package Game;

import Geometry.Line;
import Interfaces.Collidable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private final List<Collidable> collidingObjectsCollection =
            new LinkedList<>();

    // public information methods

    /**
     * Add collidable.
     *
     * @param c the c
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidingObjectsCollection.add(c);
    }

    /**
     * Get the closest collision info.
     *
     * @param trajectory the trajectory
     * @return the collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        return new CollisionInfo(trajectory, this.collidingObjectsCollection);
    }

    // Getters

    /**
     * Gets colliding objects.
     *
     * @return the colliding objects
     */
    public List<Collidable> getCollidingObjects() {
        return collidingObjectsCollection;
    }

    // Setters
    public void removeCollidable(Collidable c){
        this.collidingObjectsCollection.remove(c);
    }
}

