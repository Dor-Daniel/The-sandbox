package Geometry;

import Game.CollisionInfo;
import Game.GameEnvironment;
import GameLevels.Levels;
import GameObjects.Velocity;
import Interfaces.Collidable;
import Interfaces.Game;
import Interfaces.Sprite;
import Methods.ExceptionCheck;
import Methods.Methods;
import biuoop.DrawSurface;

import java.awt.*;


public class Ball implements Sprite {

    // local variables

    private Point center;
    private final int radius;
    private final Color ballColor;
    private Velocity speed;
    private GameEnvironment environment;

    // Constructors

    public Ball(Point center, int r, Color color ){
        this.center = center;
        this.radius = r;
        this.ballColor = color;
    }

    // Getters

    public Color getBallColor() {
        return ballColor;
    }

    public int getSize() {
        return radius;
    }

    public Point getCenter() {
        return center;
    }

    public Velocity getVelocity() {
        return speed;
    }

    public int getX(){
        return (int) this.center.getX();
    }

    public int getY(){
        return (int) this.center.getY();
    }

    // Setters

    public void setVelocity(Velocity speed) {
        this.speed = speed;
    }

    public void setVelocity(double dx, double dy){
        this.speed = new Velocity(dx,dy);
    }

    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }
    public void setCenter(Point center){
        this.center = center;
    }

    // public methods

    public String toString() {
        return "center = " + this.center.toString() + ", radius = " + this.radius;
    }

    public Boolean equals(Ball other) {
        return this.center.equals(other.getCenter()) &&
                this.radius == other.getSize();
    }

    // Game related methods

    public void moveOneStep(){
        Line trajectory = Methods.CREATE_TRAJECTORY_LINE(this.center,
                this.speed.getDx(), this.speed.getDy());
        CollisionInfo info = environment.getClosestCollision(trajectory);
        if (info.isHit()){
            moveCenter(info);
            setSpeed(info);
        }else this.center = this.speed.applyToPoint(this.center);
    }

    public void addToGame(Game g){
        this.setEnvironment(g.getEnvironment());
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawCircle(this.getX(), this.getY(), this.getSize());
        d.setColor(this.ballColor);
        d.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        if (! ExceptionCheck.IS_NULL(this.speed)) moveOneStep();

    }
    public void removeFromGame(Levels g){
        g.removeSprite(this);
    }
    private void moveCenter(CollisionInfo info){
        Collidable c = info.getCollisionObject();
        Point i = info.getCollisionPoint();
        if (c.hitFromTheSide(i)){
            if ((this.getX() < i.getX())){
                this.setCenter(new Point(c.getCollisionRectangle().getUpperLeft().getX() - this.radius - 1,
                        this.getY()));
            }else {
                this.setCenter(new Point(c.getCollisionRectangle().getUpperRight().getX() + this.radius + 1,
                        this.getY()));
            }
        }
        if (c.hitFromTheTopOrBelow(i)) {
            if ((this.getY() < i.getY())){
                this.setCenter(new Point(this.getX(),
                        c.getCollisionRectangle().getLowerLeft().getY() - this.radius - 1));
            }else {
                this.setCenter(new Point(this.getX(),
                        c.getCollisionRectangle().getUpperLeft().getY() + this.radius + 1));
            }
        }
    }
    private void setSpeed(CollisionInfo info){
        Collidable c = info.getCollisionObject();
        Point i = info.getCollisionPoint();
        setVelocity(c.hit(this,i,this.speed));
    }
}
