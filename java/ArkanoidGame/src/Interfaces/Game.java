package Interfaces;
import Game.GameEnvironment;
public interface Game {
    public void addCollidable(Collidable c);
    public void addSprite(Sprite s);
    public void removeSprite(Sprite s);
    public void removeCollidable(Collidable c);
    public void initialize();
    public GameEnvironment getEnvironment();
}
