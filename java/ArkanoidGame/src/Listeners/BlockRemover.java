package Listeners;

import Calc.Counter;
import GameLevels.Levels;
import GameObjects.Block;
import Geometry.Ball;
import Interfaces.Game;
import Interfaces.HitListener;

// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {

    // local variables
    private final Game game;
    private final Counter remainingBlocks;

    // constructors
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
        beingHit.removeFromGame(this.game);
    }
}