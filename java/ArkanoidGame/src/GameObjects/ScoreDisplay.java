package GameObjects;

import Calc.Counter;

import Geometry.Rectangle;

import Interfaces.Game;

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.*;

public class ScoreDisplay extends Block implements Sprite {
    private Counter counter = new Counter();
    private int score = 0;
    private int numOfBlocks;
    private boolean b = true;

    public ScoreDisplay(Rectangle blockRectangle, Color blockColor) {
        super(blockRectangle, blockColor);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);

        if (counter.getValue() != 0) {
            if (b) {
                this.numOfBlocks = counter.getValue();
                b = false;
            }
            d.drawText(375, 30,
                    "your score: " + score + "",
                    15);
            if (this.counter.getValue() < numOfBlocks) {
                score += 100 / numOfBlocks;
                numOfBlocks--;
            }
        } else {
            d.drawText(375, 30, "your score: " + (100) + "", 15);
        }
    }

    public void setCounter(Counter counter) {
        this.counter = counter;

    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    public void setScore(int score) {
        this.score = score;
    }
}
