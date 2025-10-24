package Methods;
import GameObjects.Block;
import GameObjects.Paddle;
import GameObjects.Velocity;
import Geometry.Ball;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Collidable;
import Interfaces.Sprite;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The type Methods.
 */
public class Methods {
    /**
     * Equals boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public static boolean EQUALS(double a, double b){
        return Math.abs(a - b) <= Const.DELTA;
    }

    /**
     * Smaller or equals boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public static boolean SMALLER_OR_EQUALS(double a, double b){
        return EQUALS(a, b) || a < b;
    }

    /**
     * Greater or equals boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public static boolean GREATER_OR_EQUALS(double a, double b){
        return EQUALS(a, b) || a > b;
    }

    /**
     * Is in range boolean.
     *
     * @param a     the a
     * @param start the start
     * @param end   the end
     * @return the boolean
     */
    public static boolean IS_IN_RANGE(double a, double start, double end){
        return SMALLER_OR_EQUALS(a,end) && GREATER_OR_EQUALS(a,start);
    }

    /**
     * Average double.
     *
     * @param a the a
     * @param b the b
     * @return the double
     */
    public static double AVERAGE(double a, double b){
        return (a + b) / Const.TWO;
    }

    /**
     * Point list to set java . util . list.
     *
     * @param points the points
     * @return the java . util . list
     */
    public static java.util.List<Point> POINT_LIST_TO_SET(List<Point> points){
        if (points.isEmpty()) return points;
        Set<Point> set = new HashSet<>(points);
        return new ArrayList<>(set);
    }

    /**
     * Is point on lines boolean.
     *
     * @param lines the lines
     * @param point the point
     * @return the boolean
     */
    public static boolean IS_POINT_ON_LINES(List<Line> lines, Point point){
        if (ExceptionCheck.IS_NULL(point)) return false;
        if (lines.isEmpty()) return false;
        for (Line l : lines) {
            if(l.isPointOnLine(point)) return true;
        }
        return false;
    }

    /**
     * Closest point point.
     *
     * @param pointsList the points list
     * @param point      the point
     * @return the point
     */
    public static Point CLOSEST_POINT(List<Point> pointsList, Point point){
        if (pointsList.isEmpty()) return null;
        Point closest = pointsList.get(Const.START_INDEX);
        for (Point p : pointsList) {
            if (SMALLER_OR_EQUALS(p.distance(point),closest.distance(point))) closest = p;
        }
        return closest;
    }

    /**
     * Random velocity velocity.
     *
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity RANDOM_VELOCITY(int speed){
        Random random = new Random();
        return Velocity.fromAngleAndSpeed(random.nextDouble() * Const.FLAT_ANGLE , speed);
    }

    /**
     * Random color color.
     *
     * @return the color
     */
    public static Color RANDOM_COLOR(){
        //creating a random object
        Random random = new Random();
        //generating random rgb
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        //return random color
        return new Color(r, g, b);
    }

    /**
     * Create trajectory line line.
     *
     * @param current the current
     * @param dx      the dx
     * @param dy      the dy
     * @return the line
     */
    public static Line CREATE_TRAJECTORY_LINE(Point current, double dx,
                                              double dy){
        return new Line(current,new Point(current.getX() + dx,
                current.getY() + dy));
    }
    public static Line[] CREATE_EXTRA_TRAJECTORY_LINES(Point current, double dx,
                                                       double dy){
        Line[] lines = new Line[2];
        Point end1 = new Point (current.getX() + dx + 0.5, current.getY() + dy);
        Point end2 = new Point(current.getX() + dx - 0.5, current.getY() + dy);
        lines[0] = new Line(current, end1);
        lines[1] = new Line(current, end2);
        return lines;
    }
    public static Rectangle[] TRAJECTORY_RECTANGLES(Paddle paddle){
        double xLeft = paddle.getCollisionRectangle().getUpperLeft().getX()
                + Const.DEFAULT_PADDLE_SPEED;
        double y = paddle.getCollisionRectangle().getUpperLeft().getY();
        Point upperLeftLeft = new Point(xLeft,y);
        Point upperLeftRight = paddle.getCollisionRectangle().getUpperRight();
        int width = Const.DEFAULT_PADDLE_SPEED;
        int height = Const.DEFAULT_PADDLE_HEIGHT;
        return new Rectangle[]{
                new Rectangle(upperLeftLeft,width,height),
                new Rectangle(upperLeftRight,width,height)};
    }
    public static boolean IS_BALL_INTERSECT_LINE(Ball ball, Line l){
        if (l.getSlope() == null) {
            return IS_IN_RANGE(l.getConstB(), ball.getX() - ball.getSize(),
                    ball.getX() + ball.getSize()) && (IS_IN_RANGE(ball.getY()
                    ,l.getStart().getY() - ball.getSize(),
                    l.getEnd().getY() + ball.getSize())
                    || IS_IN_RANGE(ball.getY(),l.getEnd().getY() - ball.getSize(),
                    l.getStart().getY() + ball.getSize()));
        }
        double m = (double)l.getSlope();
        double n = l.getConstB();
        double a = ball.getX();
        double b = ball.getY();
        double r = ball.getSize();
        try{
            double c =
                    Math.sqrt(Math.pow(2 * (n-b) - 2 * a, 2) - 4 * (1-m*m)*(a*a + (n-b*(n-b) - r*r)));
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean IS_BALL_INTERSECT_RECTANGLE(Ball b, Rectangle r){
        return false;
    }
    public static List<Sprite> BLOCK_TO_FACE(Block block){
        double x = block.getCollisionRectangle().getUpperLeft().getX();
        double y = block.getCollisionRectangle().getUpperLeft().getY();
        int width = block.getCollisionRectangle().getWidth();
        int height = block.getCollisionRectangle().getHeight();
//        int widthOneNinth = (int) Math.floor( (1.0/9) * width);
//        int widthTwoNinth = (int) Math.floor( (2.0/9) * width);
//        int widthTwoThirds = (int) Math.floor( (2.0/3) * width);
//        int widthFiveNinth = (int) Math.floor( (5.0/9) * width);
//        int heightOneSixth = (int) Math.floor( (1.0/6) * height);
//        int heightTwoSixth = (int) Math.floor( (2.0/6) * height);
//        int heightTwoThirds = (int) Math.floor( (2.0/3) * height);
//        Ball eyeBallLeft =
//                new Ball(new Point(x + widthOneNinth + widthTwoNinth / 2.0,
//                y - heightTwoThirds - heightOneSixth),heightOneSixth-1,
//                        Color.BLUE);
//        Ball eyeBallRight =
//                new Ball(new Point(x + widthTwoThirds + widthTwoNinth / 2.0,
//                        y - heightTwoThirds - heightOneSixth),heightOneSixth-1,
//                        Color.BLUE);
//        Block eyeLeft = new Block(new Point(x + widthOneNinth,
//                y - heightTwoThirds),widthTwoNinth,
//                heightTwoSixth,Color.WHITE);
//        Block eyeRight = new Block(new Point(x + widthTwoThirds
//                ,y - heightTwoThirds),widthTwoNinth,
//                heightTwoSixth,Color.WHITE);
//        Block mouth = new Block(new Point(x + widthTwoNinth,
//                y - heightOneSixth),widthFiveNinth,
//                heightOneSixth,Color.RED);
//        List <Sprite> blocks = new ArrayList<>();
//        blocks.add(eyeLeft);
//        blocks.add(eyeRight);
//        blocks.add(eyeBallLeft);
//        blocks.add(eyeBallRight);
//        blocks.add(mouth);
//        return blocks;
        List<Sprite>sprites = new ArrayList<>();
        Color color = Color.CYAN;
        for (int i = 0; i < Math.min(width,height) / 2 ; i++) {

            Sprite s = new Block(new Point(x + i, y - i),width - 2 * i,
                    height-2*i ,color);
            sprites.add(s);
            color = color.darker();
        }
        return sprites;
    }
}
