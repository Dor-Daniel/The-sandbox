package GameLevels;

import GameObjects.*;
import Geometry.Ball;
import Geometry.Point;
import Interfaces.Animation;
import Interfaces.LevelInformation;

import Methods.Const;
import Methods.Methods;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.ArrayList;


public class Level1 extends Levels implements LevelInformation, Animation {

    public Level1(SpriteCollection sprites, GUI gui) {
        super(sprites, gui);
    }

    @Override
    public int ballsNum() {
        return this.getBallCounter().getValue();
    }

    @Override
    public int paddleV() {
        return Const.LEVEL_1_PADDLE_SPEED;
    }

    @Override
    public int numBlocks() {
        return this.getBlockCounter().getValue();
    }

    @Override
    public int paddleW() {
        return Const.LEVEL_1_PADDLE_WIDTH;
    }

    @Override
    public String levelN() {
        return "\"Another brick in the wall\"";
    }

    @Override
    public void createBalls() {
        for (int i = 0; i < Const.LEVEL_1_BALLS_NUM; i++) {
            Velocity v = Methods.RANDOM_VELOCITY(Const.DEFAULT_SPEED);
            this.initialBallVelocities().add(v);
            Ball ball = new Ball(new Point(400, 450), 7, Color.WHITE);
            ball.addToGame(this);
            ball.setVelocity(v);
        }
    }

    @Override
    public void createPaddle() {
        Paddle paddle = new Paddle(Const.LEVEL_1_PADDLE_RECTANGLE, Color.BLACK,
                super.getGui());
        paddle.setSpeed(Const.LEVEL_1_PADDLE_SPEED);
        paddle.addToGame(this);
    }

    @Override
    public void createInnerBlocks() {
        for (Block b : drawWall()) {
            b.addHitListener(super.getBlockListener());
            b.setInner(true);
            b.addToGame(this);
            b.addHitListener(super.getScoreListener());
            this.blocks().add(b);
        }
    }


    @Override
    public void drawBackground(DrawSurface d) {
        this.getBackground().drawOn(d);
    }

    @Override
    public void initializeCounters() {
        super.getBallCounter().increase(this.initialBallVelocities().size());
        super.getBlockCounter().increase(this.drawWall().size() - 1);
    }

    @Override
    public int levelNum() {
        return 1;
    }

    private java.util.List<Block> drawWall() {
        Color wallColor = new Color(59, 59, 59);
        java.util.List<Block> wallBlocks = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Block crown = new Block(new Geometry.Point(25 + 90 * 2 * i, 150 + 30)
                    , 30, 30,
                    wallColor);
            wallBlocks.add(crown);
        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 10; i++) {
                if (j % 2 == 0) {
                    Block wall = new Block(new Geometry.Point(10 + 78 * i,
                            180 + 30 * j + 30),
                            78,
                            30, wallColor);
                    wallBlocks.add(wall);
                } else {
                    if (i == 0) {
                        Block halfWall = new Block(new Geometry.Point(10,
                                180 + 30 * j + 30), 39, 30, wallColor);
                        wallBlocks.add(halfWall);
                        Block wall = new Block(new Geometry.Point(49,
                                180 + 30 * j + 30),
                                78,
                                30, wallColor);
                        wallBlocks.add(wall);

                    } else if (i == 9) {
                        Block halfWall = new Block(new Geometry.Point(751,
                                180 + 30 * j + 30), 39, 30, wallColor);
                        wallBlocks.add(halfWall);
                    } else {
                        Block wall = new Block(new Point(49 + 78 * i,
                                180 + 30 * j + 30),
                                78,
                                30, wallColor);
                        wallBlocks.add(wall);
                    }

                }
            }
        }
        return wallBlocks;
    }


}


