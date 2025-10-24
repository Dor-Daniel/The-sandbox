package GameLevels;

import Calc.Counter;
import Game.AnimationRunner;
import Game.CountdownAnimation;
import Game.GameEnvironment;
import Game.PauseScreen;
import GameObjects.*;
import Interfaces.*;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import Methods.Const;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import Game.KeyPressStoppableAnimation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Levels implements LevelInformation, Animation, Game {

    // local variables
    private ScoreDisplay scoreDisplay =
            new ScoreDisplay(Const.DOWN_BLOCK.getCollisionRectangle(),
                    Color.GRAY);
    private final GUI gui;
    private final SpriteCollection sprites;
    private final GameEnvironment environment = new GameEnvironment();
    //counters
    private final Counter blockCounter = new Counter();
    private final Counter ballCounter = new Counter();
    private final Counter scoreCounter = new Counter();
    //listeners
    private final HitListener scoreListener = new ScoreTrackingListener(scoreCounter);
    private final HitListener ballListener = new BallRemover(this, this.ballCounter);
    private final HitListener blockListener = new BlockRemover(this,
            this.blockCounter);
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private boolean running;

    private final List<Block> blocks = new ArrayList<>();
    private final Background background = new Background();
    private final java.util.List<Velocity> initialBallVelocities =
            new ArrayList<>();



    // constructor
    public Levels(SpriteCollection sprites, GUI gui) {
        this.sprites = sprites;
        this.gui = gui;
        this.runner = new AnimationRunner(this.gui, 60);
        this.keyboard = gui.getKeyboardSensor();
        this.background.setLevelNum(this.levelNum());
    }

    // child methods
    public abstract int ballsNum();

    public abstract int paddleV();

    public abstract int numBlocks();

    public abstract int paddleW();

    public abstract String levelN();

    public abstract void createBalls();

    public abstract void createPaddle();

    public abstract void createInnerBlocks();

    public abstract void drawBackground(DrawSurface d);

    public abstract void initializeCounters();

    public abstract int levelNum();

    // override levelInformation
    @Override
    public int numberOfBalls() {
        return ballsNum();
    }

    @Override
    public int paddleSpeed() {
        return paddleV();
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numBlocks();
    }

    @Override
    public int paddleWidth() {
        return paddleW();
    }

    @Override
    public String levelName() {
        return levelN();
    }
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }
    @Override
    public Sprite getBackground() {
        return this.background;
    }
    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    // Adders
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Setters
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    // Getters

    public GUI getGui() {
        return gui;
    }

    public Counter getBallCounter() {
        return ballCounter;
    }

    public Counter getBlockCounter() {
        return blockCounter;
    }

    public HitListener getBallListener() {
        return ballListener;
    }

    public HitListener getBlockListener() {
        return blockListener;
    }

    public HitListener getScoreListener() {
        return scoreListener;
    }

    public GameEnvironment getEnvironment() {
        return environment;
    }

    public AnimationRunner getRunner() {
        return runner;
    }

    public SpriteCollection getSprites() {
        return this.sprites;
    }

    // public methods
    @Override
    public int waitFor() {
        return 0;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        drawBackground(d);
        d.setColor(Color.WHITE);
        d.drawText(600,50,this.levelN(),15);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (blockCounter.getValue() == 0 || ballCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    public void initialize() {

        this.scoreDisplay.setCounter(this.blockCounter);
        scoreDisplay.addToGame(this);
        createDeathReagen();
        createEdgesBlocks();
        createInnerBlocks();
        createPaddle();
        createBalls();
        initializeCounters();
    }

    public void run() {
        initialize();
        CountdownAnimation countdownAnimation = new CountdownAnimation(10,
                3, this.sprites);
        countdownAnimation.setLevelNum(this.levelNum());
        this.runner.run(countdownAnimation);
        this.running = true;
        this.runner.run(this);
    }

    // private methods
    private void createEdgesBlocks() {
        Block left = Const.LEFT_BLOCK;
        Block right = Const.RIGHT_BLOCK;
        Block down = Const.DOWN_BLOCK;
        left.addToGame(this);
        right.addToGame(this);
        down.addToGame(this);

    }

    private void createDeathReagen() {
        Block deathReagen = Const.DEATH_REAGEN_BLOCK;
        deathReagen.addToGame(this);
        deathReagen.addHitListener(this.ballListener);
    }
    public void setScore(int score){
        this.scoreDisplay.setScore(score);
    }
    public int getScore(){
        return this.scoreCounter.getValue();
    }


}
