//package Game;
//
//import Calc.Counter;
//import GameObjects.Block;
//import GameObjects.Paddle;
//import GameObjects.ScoreDisplay;
//import GameObjects.SpriteCollection;
//import Geometry.Ball;
//import Geometry.Line;
//import Geometry.Point;
//import Geometry.Rectangle;
//import Interfaces.Animation;
//import Interfaces.Collidable;
//import Interfaces.HitListener;
//import Interfaces.Sprite;
//import Listeners.BallRemover;
//import Listeners.BlockRemover;
//import Listeners.ScoreTrackingListener;
//import Methods.Const;
//import Methods.Methods;
//import biuoop.GUI;
//import biuoop.DrawSurface;
//import biuoop.KeyboardSensor;
//import biuoop.Sleeper;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * The type Game.
// */
//public class Game implements Animation {
//
//    // local variables
//
//    private final GameEnvironment environment = new GameEnvironment();
//    private final GUI gui = new GUI("Game", Const.SCREEN_WIDTH,
//            Const.SCREEN_HEIGHT);
//    //    private final Sleeper sleeper = new Sleeper();
//    private final Counter blockCounter = new Counter();
//    private final Counter ballCounter = new Counter();
//    private final Counter scoreCounter = new Counter();
//    private final HitListener scoreListener = new ScoreTrackingListener(scoreCounter);
//    private final KeyboardSensor keyboard = gui.getKeyboardSensor();
//    private boolean running;
//    private final AnimationRunner runner = new AnimationRunner(this.gui,60);
//
//
//    // Getters
//
//    /**
//     * Gets environment.
//     *
//     * @return the environment
//     */
//    public GameEnvironment getEnvironment() {
//        return environment;
//    }
//
//
//    // Adders
//
//    /**
//     * Add collidable.
//     *
//     * @param c the c
//     */
//    public void addCollidable(Collidable c) {
//        this.environment.addCollidable(c);
//    }
//
//    /**
//     * Add sprite.
//     *
//     * @param s the s
//     */
//    public void addSprite(Sprite s) {
//        this.sprites.addSprite(s);
//    }
//
//    // Setters
//    public void removeCollidable(Collidable c) {
////        this.environment.removeCollidable(c);
////    }
//
//    public void removeSprite(Sprite s) {
//        this.sprites.removeSprite(s);
//    }
//
//    // game methods
//
//    /**
//     * Initialize.
//     */
//    public void initialize() {
//        ScoreDisplay scoreDisplay =
//                new ScoreDisplay(Const.DOWN_BLOCK.getCollisionRectangle(),
//                        Color.GRAY);
//        scoreDisplay.setCounter(this.blockCounter);
//
//        HitListener ballListener = new BallRemover(this, this.ballCounter);
//        HitListener listener = new BlockRemover(this, this.blockCounter);
//        ///
//        Block deathReagen = Const.DEATH_REAGEN_BLOCK;
//        deathReagen.addToGame(this);
//        deathReagen.addHitListener(ballListener);
//        createEdgesBlocks();
//        scoreDisplay.addToGame(this);
//        createInnerBlocks(listener);
//        createPaddle();
//        createBalls();
//        initializeCounters();
//    }
//
//    /**
//     * Run.
//     */
//    public void run() {
//        initialize();
//        CountdownAnimation countdownAnimation = new CountdownAnimation(200,
//                100,this.sprites);
//        this.runner.run(countdownAnimation);
//        this.running = true;
//        this.runner.run(this);
//
//    }
//    // private methods
//    private void createEdgesBlocks() {
//        Block left = Const.LEFT_BLOCK;
//        Block right = Const.RIGHT_BLOCK;
//        Block down = Const.DOWN_BLOCK;
//        left.addToGame(this);
//        right.addToGame(this);
//        down.addToGame(this);
//
//    }
//
//    private void createBalls() {
//        Ball A = new Ball(Const.DEFAULT_STARTING_POINT, Const.DEFAULT_SIZE,
//                Methods.RANDOM_COLOR());
//        Ball B = new Ball(Const.DEFAULT_STARTING_POINT, Const.DEFAULT_SIZE,
//                Methods.RANDOM_COLOR());
//        A.setVelocity(Methods.RANDOM_VELOCITY(Const.DEFAULT_SPEED));
//        B.setVelocity(Methods.RANDOM_VELOCITY(Const.DEFAULT_SPEED));
//        A.addToGame(this);
//        B.addToGame(this);
//
//    }
//
//    private void createPaddle() {
//        Paddle paddle = new Paddle(Const.DEFAULT_PADDLE_RECTANGLE(),
//                Const.DEFAULT_PADDLE_COLOR, gui);
//        paddle.setSpeed(Const.DEFAULT_PADDLE_SPEED);
//        paddle.addToGame(this);
//
//    }
//
//    private void createInnerBlocks(HitListener listener) {
//        double x =
//                Const.SCREEN_WIDTH / 2.0 - 0.6 * Const.WIDTH_OF_BLOCKS;
//        double y = Const.FIRST_ROW_HEIGHT;
//        List<Block> blocks = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6 - i; j++) {
//                blocks.add(new Block(new Point(x - j * Const.WIDTH_OF_BLOCKS,
//                        y + i * Const.HEIGHT_OF_BLOCKS),
//                        Const.WIDTH_OF_BLOCKS, Const.HEIGHT_OF_BLOCKS,
//                        Methods.RANDOM_COLOR()));
//                blocks.add(new Block(new Point(x + j * Const.WIDTH_OF_BLOCKS,
//                        y + i * Const.HEIGHT_OF_BLOCKS),
//                        Const.WIDTH_OF_BLOCKS, Const.HEIGHT_OF_BLOCKS,
//                        Methods.RANDOM_COLOR()));
//            }
//
//        }
//        for (Block b : blocks) {
//            b.addToGame(this);
//            b.addHitListener(listener);
//            b.setInner(true);
//            b.addHitListener(this.scoreListener);
//        }
//
//    }
//
//    private boolean isInnerBlock(Block block) {
//        return !(block.equals(Const.UP_BLOCK) || block.equals(Const.DOWN_BLOCK)
//                || block.equals(Const.LEFT_BLOCK) || block.equals(Const.RIGHT_BLOCK)
//                || block.getCollisionRectangle().equals(Const.DEFAULT_PADDLE_RECTANGLE()));
//
//    }
//
//    private void initializeCounters() {
//        this.blockCounter.increase(this.environment.getCollidingObjects().size() - 5);
//        this.ballCounter.increase(2);
//    }
//
//    public void drawBackground(DrawSurface d) {
//        Color color = Color.GRAY;
//        d.setColor(color);
//        d.fillRectangle(0, 0, 800, 600);
//    }
//
//    @Override
//    public void doOneFrame(DrawSurface d) {
//        drawBackground(d);
//        this.sprites.drawAllOn(d);
//        this.sprites.notifyAllTimePassed();
//        if (blockCounter.getValue() == 0 || ballCounter.getValue() == 0) {
//            this.gui.close();
//        }
//        if (this.keyboard.isPressed("p")) {
//            this.runner.run(new PauseScreen(this.keyboard));
//        }
//    }
//
//    @Override
//    public boolean shouldStop() {
//        return !this.running;
//    }
//}
//
