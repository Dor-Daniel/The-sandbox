package GameLevels;

import Game.AnimationRunner;
import Game.GameEnvironment;
import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class WinGameAnimation{
    private final GUI gui;
    private boolean run = true;
    private Sleeper sleeper = new Sleeper();
    private int score;

    public WinGameAnimation(GUI gui,int score){
        this.gui = gui;
        this.score = score;
    }
    public void winner(){
        while (run){
            DrawSurface d = gui.getDrawSurface();
            drawBackground2(d);
            d.setColor(Color.WHITE);
            d.drawText(300,300,"You win!!!",50);
            d.drawText(310,350,"your final score is " + score,30);
            gui.show(d);
            this.sleeper.sleepFor(2000);
            this.run = false;
        }
        this.gui.close();
    }
    private static void drawBackground2(DrawSurface surface) {
        // Draw a background with a gradient color
        int width = surface.getWidth();
        int height = surface.getHeight();
        Color startColor = new Color(45, 187, 13);
        Color endColor = new Color(1, 143, 111);

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
