package GameObjects;


import GameLevels.Levels;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.*;
import Methods.Methods;
import biuoop.DrawSurface;
import Methods.Const;
import Methods.ExceptionCheck;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;



/**
 * The type Block.
 */
public class Block implements Collidable, Sprite , HitNotifier {

    // local variables
    private final Rectangle blockRectangle;
    private final Color blockColor;
    private final List<HitListener> hitListeners = new ArrayList<>();
    private boolean isInner = false;


    // Constructors

    /**
     * Instantiates a new Block.
     *
     * @param blockRectangle the block rectangle
     * @param blockColor     the block color
     */
    public Block(Rectangle blockRectangle, Color blockColor) {
        this.blockRectangle = blockRectangle;
        this.blockColor = blockColor;
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft  the upper left
     * @param width      the width
     * @param height     the height
     * @param blockColor the block color
     */
    public Block(Point upperLeft, int width, int height, Color blockColor) {
        this.blockRectangle = new Rectangle(upperLeft, width, height);
        this.blockColor = blockColor;
    }

    // Getters

    /**
     * Gets block color.
     *
     * @return the block color
     */
    public Color getBlockColor() {
        return blockColor;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return blockRectangle;
    }

    // public methods
    @Override
    public Velocity hit(Ball hitter ,Point collisionPoint,
                        Velocity currentVelocity) {
        if (ExceptionCheck.IS_NULL(currentVelocity) ||
                ExceptionCheck.IS_NULL(collisionPoint)) {
            return currentVelocity;
        }
        if (Methods.IS_POINT_ON_LINES(this.blockRectangle.getRectangleXEdges(),
                collisionPoint)) {
            notifyHit(hitter);
            return new Velocity(Const.NEGATIVE * currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        if (Methods.IS_POINT_ON_LINES(this.blockRectangle.getRectangleYEdges(),
                collisionPoint)) {
            notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(),
                    Const.NEGATIVE * currentVelocity.getDy());
        }
        return currentVelocity;

    }

    @Override
    public boolean hitFromTheSide(Point collisionPoint) {
        return Methods.IS_POINT_ON_LINES(this.blockRectangle.getRectangleXEdges()
                , collisionPoint);
    }

    @Override
    public boolean hitFromTheTopOrBelow(Point collisionPoint) {
        return Methods.IS_POINT_ON_LINES(this.blockRectangle.getRectangleYEdges(),
                collisionPoint);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Const.BLOCKS_PARAMETER_COLOR);
        d.drawRectangle((int) this.blockRectangle.getLowerLeft().getX(),
                (int) this.blockRectangle.getLowerLeft().getY(),
                this.blockRectangle.getWidth(), this.blockRectangle.getHeight());
        d.setColor(this.blockColor);
        d.fillRectangle((int) this.blockRectangle.getLowerLeft().getX(),
                (int) this.blockRectangle.getLowerLeft().getY(),
                this.blockRectangle.getWidth(), this.blockRectangle.getHeight());
//        if (isInner){
//            List<Sprite> blocks = Methods.BLOCK_TO_FACE(this);
//            for (Sprite b : blocks){
//                b.drawOn(d);
//            }
//        }
    }

    @Override
    public void timePassed() {

    }
    public void setInner(boolean isInner){
        this.isInner = isInner;
    }

    public void removeFromGame(Game game){
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    // Override toString and equals methods
    public String toString() {
        return this.blockRectangle.toString() + " color = " + this.blockColor.toString();
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Block other) {
        return this.blockRectangle.equals(other.getCollisionRectangle()) &&
                this.blockColor.equals(other.getBlockColor());
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
