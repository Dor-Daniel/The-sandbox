package Game;

import GameObjects.Background;
import GameObjects.SpriteCollection;
import Interfaces.Animation;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.*;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private final int constTime;
    private double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private Background background = new Background();
    private int levelNum = 0;

    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.constTime = (int)numOfSeconds;


    }

    public void doOneFrame(DrawSurface d) {
        try{
            draw(d);
            Color color = new Color(89, 124, 4);
            d.setColor(color);
            d.drawText(200, 300, "Get ready game start in " + countFrom, 30);
            this.countFrom--;
            this.numOfSeconds -= this.numOfSeconds / (this.countFrom+1);
        }catch (NullPointerException e){
            this.numOfSeconds -= this.numOfSeconds / (this.countFrom+1);
        }
    }
    public void setLevelNum(int levelNum){
        this.levelNum = levelNum;
    }
    private void draw(DrawSurface d){
        if (levelNum ==1){
            this.background.drawLevel1(d);
        } else if (levelNum ==2) {
            this.background.drawLevel2(d);
        } else if (levelNum == 3) {

        }
        this.gameScreen.drawAllOn(d);

    }

    public boolean shouldStop() {
        return (this.numOfSeconds < 1);
    }

    @Override
    public int waitFor() {
        return this.constTime;
    }

    public void setCountFrom(int countFrom) {
        this.countFrom = countFrom;
    }

    public void setNumOfSeconds(double numOfSeconds) {
        this.numOfSeconds = numOfSeconds;
    }
}
