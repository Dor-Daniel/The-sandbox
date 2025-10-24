package Listeners;

import Calc.Counter;

import GameLevels.Levels;
import GameObjects.Block;
import Geometry.Ball;
import Interfaces.HitListener;

public class BallRemover implements HitListener {
    private final Levels game;
    private final Counter remainingBalls;
    public BallRemover (Levels g, Counter c){
        this.game = g;
        this.remainingBalls = c;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(game);
    }
}
