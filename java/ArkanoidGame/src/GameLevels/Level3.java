package GameLevels;

import Game.GameEnvironment;
import GameObjects.Block;
import GameObjects.Paddle;
import GameObjects.SpriteCollection;
import GameObjects.Velocity;
import Geometry.Ball;
import Geometry.Point;
import Interfaces.Animation;
import Interfaces.HitListener;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Methods.Const;
import Methods.Methods;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level3 extends Levels implements LevelInformation, Animation {
    public Level3(SpriteCollection sprites, GUI gui) {
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
        return "Relax";
    }

    @Override
    public void createBalls() {
        Ball ball = new Ball(new Point(400, 300), 7, Color.WHITE);
        Ball ball2 = new Ball(new Point(400, 300),7,Color.WHITE);
        ball2.addToGame(this);
        ball2.setVelocity(Methods.RANDOM_VELOCITY(15));
        ball.setVelocity(Methods.RANDOM_VELOCITY(15));
        ball.addToGame(this);
        this.initialBallVelocities().add(ball.getVelocity());
        this.initialBallVelocities().add(ball2.getVelocity());
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
        for (Block b : boomingBlocks()) {
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
        this.getBallCounter().increase(this.initialBallVelocities().size());
        this.getBlockCounter().increase(boomingBlocks().size());
    }

    private List<Block> boomingBlocks() {
        List<Block> blocks = new ArrayList<>();
//        blocks.add(new Block(new Point(0,0),800,600,Color.BLACK));
        for (int i = 0; i < 6; i++) {
            for (int j = i; j < 10 - i; j++) {
                Block block = new Block(new Point(50 + j * 70, 100 + i * 30), 70, 30,
                        Color.BLACK);
                blocks.add(block);

            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = i; j < 10 - i; j++) {
                Block block = new Block(new Point(5 + 50 + j * 70, 100 + i * 30 + 5), 60, 20,
                        Color.CYAN);
                blocks.add(block);

            }
        }
        return blocks;
    }

    @Override
    public int levelNum() {
        return 3;
    }


}
