package Game;

import Calc.Counter;
import GameLevels.EndLevelAnimation;
import GameLevels.Levels;
import GameLevels.WinGameAnimation;
import GameObjects.SpriteCollection;
import Interfaces.Sprite;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private final List<Levels> levels;
    private final GUI gui;

    public GameFlow(List<Levels> levels,GUI gui) {
        this.levels = levels;
        this.gui = gui;
    }

    public void runLevels() {
        int score = 0;
        for (Levels levelInfo : this.levels) {
            levelInfo.setScore(score);
            AnimationRunner ar = levelInfo.getRunner();
            levelInfo.run();
            Counter ballCounter = levelInfo.getBallCounter();
            score += levelInfo.getScore() + 100;
            if (ballCounter.getValue() == 0) {
                EndLevelAnimation endLevelAnimation =
                        new EndLevelAnimation();
                ar.run(endLevelAnimation);
                levelInfo.getGui().close();
                return;
            } else {
                CountdownAnimation countdownAnimation = new CountdownAnimation(10,
                        3, levelInfo.getSprites());
                countdownAnimation.setLevelNum(2);
            }
        }
        WinGameAnimation winGameAnimation = new WinGameAnimation(gui,score);
        winGameAnimation.winner();
    }
}
