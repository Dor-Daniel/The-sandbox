package Listeners;

import Calc.Counter;
import GameObjects.Block;
import Geometry.Ball;
import Interfaces.HitListener;

public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(1);
    }

}