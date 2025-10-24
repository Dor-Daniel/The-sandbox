package Tests;
import Geometry.Ball;
import Geometry.Point;
import Methods.Methods;
import Geometry.Line;

import java.awt.*;

public class LineTest {
    static Line line = new Line(2,0,2,8);
    static Ball ball = new Ball(new Point(2,-5) ,5, Color.BLACK);

    public static void main(String[] args) {
        System.out.print(Methods.IS_BALL_INTERSECT_LINE(ball,line));
    }
}
