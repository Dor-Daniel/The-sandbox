package GameObjects;


import Game.GameEnvironment;
import GameLevels.Levels;
import Geometry.Ball;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Collidable;
import Interfaces.Game;
import Interfaces.Sprite;
import Methods.Methods;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.GUI;
import Methods.Methods;
import Methods.Const;

import java.awt.*;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {

    // local variables
    private Rectangle paddleRectangle;
    private int speed;
    private final KeyboardSensor key;
    private final Color paddleColor;
    private GameEnvironment environment;

    // Constructors

    /**
     * Instantiates a new Paddle.
     *
     * @param paddleRectangle the paddle rectangle
     * @param paddleColor     the paddle color
     * @param gui             the gui
     */
    public Paddle(Rectangle paddleRectangle, Color paddleColor, GUI gui) {
        this.paddleRectangle = paddleRectangle;
        this.paddleColor = paddleColor;
        this.key = gui.getKeyboardSensor();
    }

    // Getters

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    @Override
    public Velocity hit(Ball hitter ,Point collisionPoint,
                        Velocity currentVelocity) {
        return switch (getRegion(collisionPoint.getX(),
                    this.paddleRectangle.getUpperLeft().getX(),
                    this.paddleRectangle.getWidth())) {
                case 1 -> Velocity.fromAngleAndSpeed(-60, Const.DEFAULT_SPEED);
                case 2 -> Velocity.fromAngleAndSpeed(-30, Const.DEFAULT_SPEED);
                case 3 -> Velocity.fromAngleAndSpeed(0, Const.DEFAULT_SPEED);
                case 4 -> Velocity.fromAngleAndSpeed(30, Const.DEFAULT_SPEED);
                case 5 -> Velocity.fromAngleAndSpeed(60, Const.DEFAULT_SPEED);
                default -> currentVelocity;
            };
    }

    @Override
    public boolean hitFromTheSide(Point collisionPoint) {
        return Methods.IS_POINT_ON_LINES(this.paddleRectangle.getRectangleXEdges()
                , collisionPoint);
    }

    @Override
    public boolean hitFromTheTopOrBelow(Point collisionPoint) {
        return Methods.IS_POINT_ON_LINES(this.paddleRectangle.getRectangleYEdges(),
                collisionPoint);
    }

    //  public game related methods

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
        this.setEnvironment(g.getEnvironment());
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddleColor);
        d.fillRectangle((int) this.paddleRectangle.getLowerLeft().getX(),
                (int) this.paddleRectangle.getLowerLeft().getY(),
                this.paddleRectangle.getWidth(),
                this.paddleRectangle.getHeight());
    }

    @Override
    public void timePassed() {
        if (isKeyLeft()) {
            Line t = Methods.CREATE_TRAJECTORY_LINE(this.paddleRectangle.getUpperLeft(),
                    -speed, Const.ZERO);
            if (!isHit(t)) {
                moveLeft();
            }
        } else if (isKeyRight()) {
            Line t = Methods.CREATE_TRAJECTORY_LINE(this.paddleRectangle.getUpperRight(),
                    speed, Const.ZERO);
            if (!isHit(t)) {
                moveRight();
            }
        }
    }

    // Setters

    /**
     * Set speed.
     *
     * @param speed the speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Set paddle rectangle.
     *
     * @param paddleRectangle the paddle rectangle
     */
    public void setPaddleRectangle(Rectangle paddleRectangle) {
        this.paddleRectangle = paddleRectangle;
    }

    /**
     * Set environment.
     *
     * @param environment the environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }


    // private methods

    private void moveLeft() {
        this.setPaddleRectangle(new Rectangle(new Point(this.paddleRectangle.getUpperLeft().getX()
                - speed, this.paddleRectangle.getUpperLeft().getY()),
                paddleRectangle.getWidth(), paddleRectangle.getHeight()));
    }

    private void moveRight() {
        this.setPaddleRectangle(new Rectangle(new Point(this.paddleRectangle.getUpperLeft().getX()
                + speed, this.paddleRectangle.getUpperLeft().getY()),
                paddleRectangle.getWidth(), paddleRectangle.getHeight()));
    }

    private boolean isKeyRight() {
        return this.key.isPressed(KeyboardSensor.RIGHT_KEY);
    }

    private boolean isKeyLeft() {
        return this.key.isPressed(KeyboardSensor.LEFT_KEY);
    }

    private boolean isHit(Line t) {
        for (Collidable c : environment.getCollidingObjects()) {
            if (!c.equals(this) && !c.getCollisionRectangle().intersectionPoints(t).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private int getRegion(double collisionX, double leftX, int width) {
        if (Methods.IS_IN_RANGE(collisionX, leftX, leftX + width * Const.FIFTH))
            return 1;
        if (Methods.IS_IN_RANGE(collisionX, leftX + width * Const.FIFTH,
                leftX + width * Const.TWO_FIFTH)) return 2;
        if (Methods.IS_IN_RANGE(collisionX, leftX + width * Const.TWO_FIFTH,
                leftX + width * Const.THREE_FIFTH)) return 3;
        if (Methods.IS_IN_RANGE(collisionX, leftX + width * Const.THREE_FIFTH,
                leftX + width * Const.FOUR_FIFTH)) return 4;
        if (Methods.IS_IN_RANGE(collisionX, leftX + width * Const.FOUR_FIFTH,
                leftX + width)) return 5;
        return 0;
    }
}