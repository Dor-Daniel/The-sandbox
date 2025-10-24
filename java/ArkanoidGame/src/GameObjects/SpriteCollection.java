package GameObjects;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Interfaces.Sprite;
import com.sun.jdi.PrimitiveValue;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {

    // local variables
    private final List<Sprite> sprites = new LinkedList<>();


    // Adders

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    // Getters

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    // Setters
    public void removeSprite(Sprite s){
        this.sprites.remove(s);
    }


    // public game related methods

    /**
     * Notify all time passed.
     */
    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (int i = 0;i<this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

}