package GameLevels;

import Game.CountdownAnimation;
import GameObjects.Block;
import GameObjects.SpriteCollection;
import Geometry.Point;
import Interfaces.Animation;
import Interfaces.Collidable;
import biuoop.DrawSurface;

import java.awt.*;

public class EndLevelAnimation implements Animation {
    private boolean shouldStop = false;
    private CountdownAnimation animation = new CountdownAnimation(1, 1, null);
    private boolean t = true;
    private SpriteCollection spriteCollection = new SpriteCollection();

    public EndLevelAnimation() {

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        loser(d);
    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }

    @Override
    public int waitFor() {
        return 0;
    }

    private void winGame(DrawSurface d) {
        this.animation.setCountFrom(10);
        if (t) {
            this.animation.setNumOfSeconds(5000);
            t = false;
        }
        this.animation.doOneFrame(d);
        Color color = new Color(41, 150, 134);
        d.setColor(color);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        d.drawText(350, 300, "YOU WON!!!!", 50);
        if (animation.shouldStop()) {
            this.shouldStop = true;
        }
    }

    public void setSpriteCollection(SpriteCollection spriteCollection) {
        this.spriteCollection = spriteCollection;
    }

    private void loser(DrawSurface d) {
        this.animation.setCountFrom(10);
        if (t) {
            this.animation.setNumOfSeconds(5000);
            t = false;
        }
        this.animation.doOneFrame(d);
        drawBackground(d);
        d.setColor(Color.WHITE);
        d.drawText(350, 300, "LOSER!!!!", 50);
        if (animation.shouldStop()) {
            this.shouldStop = true;
        }
    }

    private static void drawBackground(DrawSurface surface) {
        // Draw a background with a gradient color
        int width = surface.getWidth();
        int height = surface.getHeight();
        Color startColor = new Color(135, 206, 250);
        Color endColor = new Color(70, 130, 180);

        for (int y = 0; y < height; y++) {
            float ratio = (float) y / height;
            int red = (int) (startColor.getRed() * (1 - ratio) + endColor.getRed() * ratio);
            int green = (int) (startColor.getGreen() * (1 - ratio) + endColor.getGreen() * ratio);
            int blue = (int) (startColor.getBlue() * (1 - ratio) + endColor.getBlue() * ratio);
            Color color = new Color(red, green, blue);
            surface.setColor(color);
            surface.drawLine(0, y, width, y);
        }
    }

}
