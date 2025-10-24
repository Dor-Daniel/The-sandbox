
import Game.GameFlow;
import GameLevels.Level1;
import GameLevels.Level2;

import GameLevels.Level3;
import GameLevels.Levels;
import GameObjects.SpriteCollection;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] levelsNumbers = new int[args.length];
        if(args.length == 0){
            levelsNumbers = new int[]{1,2,3};
        }
        int i = 0;
        for (String arg : args) {
            try {
                levelsNumbers[i] = Integer.parseInt(arg);
                i++;
            } catch (NumberFormatException numberFormatException) {
                levelsNumbers = new int[]{1,2,3};
                break;
            }
        }

        List<Levels> levels = new ArrayList<>();
        GUI gui = new GUI("Game", 800, 600);
        for (int levelsNumber : levelsNumbers) {
            SpriteCollection spriteCollection = new SpriteCollection();
            if (levelsNumber == 1) {
                Levels level1 = new Level1(spriteCollection, gui);
                levels.add(level1);
            } else if (levelsNumber == 2) {
                Levels level2 = new Level2(spriteCollection, gui);
                levels.add(level2);
            } else if (levelsNumber == 3) {
                Levels level3 = new Level3(spriteCollection, gui);
                levels.add(level3);
            }
        }
        GameFlow gameFlow = new GameFlow(levels,gui);
        gameFlow.runLevels();
    }
}
