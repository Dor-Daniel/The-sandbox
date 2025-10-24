package Methods;

import GameObjects.Block;
import GameObjects.Paddle;
import GameObjects.Velocity;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;

import java.awt.*;

/**
 * The type Const.
 */
public class Const {
    /**
     * The constant DEFAULT_PADDLE_COLOR.
     */
    public static Color DEFAULT_PADDLE_COLOR = Color.CYAN,
    /**
     * The Side blocks color.
     */
    SIDE_BLOCKS_COLOR = Color.white,
    /**
     * The Blocks parameter color.
     */
    BLOCKS_PARAMETER_COLOR = Color.BLACK;
    /**
     * The constant SQUARED.
     */
    public static double SQUARED = 2,
    /**
     * The Delta.
     */
    DELTA = 0.0001,
    /**
     * The Fifth.
     */
    FIFTH = 0.2,
    /**
     * The Two fifth.
     */
    TWO_FIFTH = 0.4,
    /**
     * The Three fifth.
     */
    THREE_FIFTH = 0.6,
    /**
     * The Four fifth.
     */
    FOUR_FIFTH = 0.8;
    /**
     * The constant START_INDEX.
     */
    public static int START_INDEX = 0,
    /**
     * The Negative.
     */
    NEGATIVE = -1,
    /**
     * The Screen width.
     */
    SCREEN_WIDTH = 800,
    /**
     * The Screen height.
     */
    SCREEN_HEIGHT = 600,
    /**
     * The Flat angle.
     */
    FLAT_ANGLE = 180,
    /**
     * The Default speed.
     */
    DEFAULT_SPEED = 5,
    /**
     * The Default size.
     */
    DEFAULT_SIZE = 5,
    /**
     * The Distance from down.
     */
    DISTANCE_FROM_DOWN = 20,
    /**
     * The Default paddle width.
     */
    DEFAULT_PADDLE_WIDTH = 100,
    /**
     * The Default paddle height.
     */
    DEFAULT_PADDLE_HEIGHT = 5,
    /**
     * The Default paddle speed.
     */
    DEFAULT_PADDLE_SPEED = 8,
    /**
     * The Two.
     */
    TWO = 2,
    /**
     * The Zero.
     */
    ZERO = 0,
    /**
     * The X start.
     */
    X_START = 0,
    /**
     * The Side blocks size.
     */
    SIDE_BLOCKS_SIZE = 10,
    /**
     * The First row height.
     */
    FIRST_ROW_HEIGHT = 100,
    /**
     * The Height of blocks.
     */
    HEIGHT_OF_BLOCKS = 25,
    /**
     * The Width of blocks.
     */
    WIDTH_OF_BLOCKS = 55;
    /**
     * The constant DEFAULT_STARTING_POINT.
     */
    public static Point DEFAULT_STARTING_POINT =
            new Point((double) (SCREEN_WIDTH - 2 * SIDE_BLOCKS_SIZE) / 2,
                    (double) SCREEN_HEIGHT / 2);

    /**
     * Default paddle rectangle rectangle.
     *
     * @return the rectangle
     */
    public static Rectangle DEFAULT_PADDLE_RECTANGLE() {
        return new Rectangle(new Point((double) SCREEN_WIDTH / 2,
                SCREEN_HEIGHT - DISTANCE_FROM_DOWN), DEFAULT_PADDLE_WIDTH
                , DEFAULT_PADDLE_HEIGHT);
    }

    //////// edge blocks /////////
    /**
     * The constant RIGHT_BLOCK.
     */
    public static Block RIGHT_BLOCK = new Block(new Point(SCREEN_WIDTH -
            SIDE_BLOCKS_SIZE, SCREEN_HEIGHT),
            SIDE_BLOCKS_SIZE, SCREEN_HEIGHT,
            SIDE_BLOCKS_COLOR),
    /**
     * The Left block.
     */
    LEFT_BLOCK = new Block(new Point(X_START, SCREEN_HEIGHT),
            SIDE_BLOCKS_SIZE, SCREEN_HEIGHT,
            SIDE_BLOCKS_COLOR),
    /**
     * The Up block.
     */
    UP_BLOCK = new Block(new Point(SIDE_BLOCKS_SIZE,
            SCREEN_HEIGHT),
            SCREEN_WIDTH - TWO * SIDE_BLOCKS_SIZE,
            SIDE_BLOCKS_SIZE, SIDE_BLOCKS_COLOR),
    /**
     * The Down block.
     */
    DOWN_BLOCK = new Block(new Point(SIDE_BLOCKS_SIZE,
            SIDE_BLOCKS_SIZE),
            SCREEN_WIDTH - TWO * SIDE_BLOCKS_SIZE,
            SIDE_BLOCKS_SIZE, SIDE_BLOCKS_COLOR),
            DEATH_REAGEN_BLOCK = UP_BLOCK;

    ///////// level 1 const //////

    public static Ball LEVEL_1_BALL = new Ball(new Point(100, 100), 5,
            Color.WHITE);
    public static Velocity LEVEL_1_BALL_VELOCITY = new Velocity(7, 7);
    public static int LEVEL_1_PADDLE_WIDTH = 200, LEVEL_1_PADDLE_HEIGHT = 10,
            LEVEL_1_PADDLE_SPEED = 15;
    public static Rectangle LEVEL_1_PADDLE_RECTANGLE =
            new Rectangle(new Point((double) SCREEN_WIDTH / 2,
                    SCREEN_HEIGHT - DISTANCE_FROM_DOWN), LEVEL_1_PADDLE_WIDTH,
                    LEVEL_1_PADDLE_HEIGHT), LEVEL_1_BLOCK_RECTANGLE =
            new Rectangle(new Point(385, 265), 30, 30), LEVEL_2_PADDLE_REC = new Rectangle(new Point((double) SCREEN_WIDTH / 2,
            SCREEN_HEIGHT - DISTANCE_FROM_DOWN), 100, 10);
    public static int LEVEL_1_BALLS_NUM = 5;
    /////// level 2//////

}
