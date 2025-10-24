
package GameLevels;

import GameObjects.*;
import Geometry.Ball;
import Geometry.Point;
import Interfaces.Animation;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Methods.Const;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level2 extends Levels implements LevelInformation, Animation {


    // constructor
    public Level2(SpriteCollection sprites, GUI gui) {
        super(sprites, gui);

    }

    // override super
    @Override
    public int ballsNum() {
        return 1;
    }

    @Override
    public int paddleV() {
        return Const.LEVEL_1_PADDLE_SPEED;
    }

    @Override
    public int numBlocks() {
        return 1;
    }

    @Override
    public int paddleW() {
        return Const.LEVEL_1_PADDLE_WIDTH;
    }

    @Override
    public String levelN() {
        return "Direct hit";
    }

    @Override
    public void createBalls() {
        Ball ball = Const.LEVEL_1_BALL;
        ball.setVelocity(Const.LEVEL_1_BALL_VELOCITY);
        ball.addToGame(this);
        this.initialBallVelocities().add(ball.getVelocity());
    }

    @Override
    public void createPaddle() {
        Paddle paddle = new Paddle(Const.LEVEL_2_PADDLE_REC, Color.WHITE,
                super.getGui());
        paddle.setSpeed(Const.LEVEL_1_PADDLE_SPEED);
        paddle.addToGame(this);

    }

    @Override
    public void createInnerBlocks() {
        Color color = new Color(255, 255, 255);
        Block block = new Block(Const.LEVEL_1_BLOCK_RECTANGLE, color);
        block.addToGame(this);
        block.addHitListener(super.getBlockListener());
        block.setInner(true);
        block.addHitListener(super.getScoreListener());
        Color pathColor = new Color(128, 9, 9);
        Block interact = new Block(new Point(325, 300), 150, 15, pathColor);
        interact.addToGame(this);
        interact.setInner(true);
        Block interact2 = new Block(new Point(325, 300), 15, 100, pathColor);
        interact2.addToGame(this);
        interact2.setInner(true);
        Block interact3 = new Block(new Point(460, 300), 15, 100, pathColor);
        interact3.addToGame(this);
        interact3.setInner(true);

    }

    @Override
    public void drawBackground(DrawSurface d) {
        this.getBackground().drawOn(d);
    }

    @Override
    public void initializeCounters() {
        super.getBallCounter().increase(1);
        super.getBlockCounter().increase(1);
    }

    @Override
    public int levelNum() {
        return 2;
    }
}
