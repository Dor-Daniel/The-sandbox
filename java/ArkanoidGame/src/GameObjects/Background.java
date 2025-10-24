package GameObjects;

import GameLevels.Levels;
import GameObjects.Block;
import Geometry.Ball;
import Geometry.Line;
import Geometry.Point;
import Interfaces.Game;
import Interfaces.Sprite;
import Methods.Methods;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Background implements Sprite {

    private int levelNum = 0;

    // level 1
    public void drawLevel1(DrawSurface d) {
        for (Block b : background()) {
            Point p = b.getCollisionRectangle().getUpperLeft();
            int w = b.getCollisionRectangle().getWidth(), h =
                    b.getCollisionRectangle().getHeight();
            d.setColor(Color.BLACK);
            d.drawRectangle((int) p.getX(), (int) p.getY(), w, h);

            d.setColor(b.getBlockColor());
            d.fillRectangle((int) p.getX(), (int) p.getY(), w, h);
        }
        for (Ball b : moon()) {
            d.setColor(b.getBallColor());
            d.fillCircle(b.getX(), b.getY(), b.getSize());
        }
        for (Block b : buildings()) {
            Point p = b.getCollisionRectangle().getUpperLeft();
            int w = b.getCollisionRectangle().getWidth(), h =
                    b.getCollisionRectangle().getHeight();
            d.setColor(Color.BLACK);
            d.drawRectangle((int) p.getX(), (int) p.getY(), w, h);

            d.setColor(b.getBlockColor());
            d.fillRectangle((int) p.getX(), (int) p.getY(), w, h);
        }

    }

    private static List<Block> background() {
        List<Block> blocks = new ArrayList<>();
        Color brown = new Color(90, 50, 0);
        Color blue = new Color(38, 31, 84);
        Block sky = new Block(new Point(0, 0), 800, 300, blue);
        Block ground = new Block(new Point(0, 300), 800, 300, brown);
        blocks.add(sky);
        blocks.add(ground);
        return blocks;
    }

    private static List<Ball> moon() {
        Ball moon = new Ball(new Point(125, 75), 50, Color.WHITE);
        Color blue = new Color(38, 31, 84);
        Ball shadow = new Ball(new Point(150, 75), 50, blue);
        List<Ball> moons = new ArrayList<>();
        moons.add(moon);
        moons.add(shadow);
        return moons;
    }

    private static List<Block> buildings() {
        List<Block> buildings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                Block tallBuilding = new Block(new Point(10 + 156 * i, 180), 39, 120,
                        Color.BLACK);
                buildings.add(tallBuilding);
                Color yellow = new Color(255, 255, 0);
                Block window_1 = new Block(new Point(17 + 156 * i, 190), 5, 20, yellow);
                Block window_2 = new Block(new Point(17 + 156 * i, 240), 5, 20, yellow);
                Block window_3 = new Block(new Point(37 + 156 * i, 190), 5, 20, yellow);
                Block window_4 = new Block(new Point(37 + 156 * i, 240), 5, 20, yellow);
                buildings.add(window_1);
                buildings.add(window_2);
                buildings.add(window_3);
                buildings.add(window_4);
                Block littleBuilding = new Block(new Point(49 + 156 * i, 240),
                        39, 60, Color.BLACK);
                buildings.add(littleBuilding);
                Block guyBuilding = new Block(new Point(88 + 156 * i, 210),
                        39, 90, Color.BLACK);
                buildings.add(guyBuilding);
                Block window_9 = new Block(new Point(98 + 156 * i, 220), 19, 3,
                        yellow);
                Block window_10 = new Block(new Point(98 + 156 * i, 232), 19, 3,
                        yellow);
                Block window_11 = new Block(new Point(98 + 156 * i, 244), 19, 3,
                        yellow);
                Block window_12 = new Block(new Point(98 + 156 * i, 256), 19, 3,
                        yellow);
                Block shadow = new Block(new Point(105 + 156 * i, 210), 5, 90,
                        Color.BLACK);
                buildings.add(window_11);
                buildings.add(window_12);
                buildings.add(window_9);
                buildings.add(window_10);
                buildings.add(shadow);
                Block middleBuilding = new Block(new Point(127 + 156 * i, 255),
                        39, 45, Color.BLACK);
                buildings.add(middleBuilding);


            }
        }
        return buildings;
    }

    // level 2
    public void drawLevel2(DrawSurface d) {
        drawBackground2(d);
        for (Ball b : board()) {
            int x = b.getX(), y = b.getY(), r = b.getSize();
            d.setColor(b.getBallColor());
            d.drawCircle(x, y, r);
        }
        for (Line l : boardLines()) {
            int x1 = (int) l.getStart().getX(), y1 = (int) l.getStart().getY(), x2 = (int)
                    l.getEnd().getX(), y2 = (int) l.getEnd().getY();
            d.drawLine(x1, y1, x2, y2);
        }
    }

    private List<Ball> board() {
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Color pathColor = new Color(128, 9, 9);
            Ball b = new Ball(new Point(400, 250), 40 + 40 * i, pathColor);
            balls.add(b);
        }
        return balls;
    }

    private List<Line> boardLines() {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Line line = new Line(400, 250, 200 + 200 * i, 50);
            lines.add(line);
        }
        for (int i = 0; i < 3; i++) {
            Line line = new Line(400, 250, 200 + 200 * i, 450);
            lines.add(line);
        }
        for (int i = 0; i < 2; i++) {
            Line line = new Line(400, 250, 200 + 400 * i, 250);
            lines.add(line);
        }
        return lines;
    }
    // level 3

    private void drawLevel3(DrawSurface d) {
        drawBackground(d);
    }

    private static void drawBackground2(DrawSurface surface) {
        // Draw a background with a gradient color
        int width = surface.getWidth();
        int height = surface.getHeight();
        Color startColor = new Color(10, 10, 10);
        Color endColor = new Color(255, 0, 0);

        for (int y = 0; y < height; y++) {
            float ratio = (float) y / height;
            int red = (int) (startColor.getRed() * (1 - ratio) + endColor.getRed() * ratio);
            int green = (int) (startColor.getGreen() * (1 - ratio) + endColor.getGreen() * ratio);
            int blue = (int) (startColor.getBlue() * (1 - ratio) + endColor.getBlue() * ratio);
            Color color = new Color(red, green, blue);
            surface.setColor(color);
            surface.drawLine(0, y, width, y);
        }
    }

    private static void drawBackground(DrawSurface surface) {
        // Draw a background with a gradient color
        int width = surface.getWidth();
        int height = surface.getHeight();
        Color startColor = new Color(10, 10, 10);
        Color endColor = new Color(221, 222, 222);

        for (int y = 0; y < height; y++) {
            float ratio = (float) y / height;
            int red = (int) (startColor.getRed() * (1 - ratio) + endColor.getRed() * ratio);
            int green = (int) (startColor.getGreen() * (1 - ratio) + endColor.getGreen() * ratio);
            int blue = (int) (startColor.getBlue() * (1 - ratio) + endColor.getBlue() * ratio);
            Color color = new Color(red, green, blue);
            surface.setColor(color);
            surface.drawLine(0, y, width, y);
        }
    }
//    public static void main(String[] args) {
//        GUI gui = new GUI("", 800, 600);
//
//        while (true) {
//            DrawSurface d = gui.getDrawSurface();
////            d.setColor(Color.BLACK);
////            d.fillRectangle(0,0,800,600);
////            for (Ball b:board()){
////                int x = b.getX(),y=b.getY(),r=b.getSize();
////                d.setColor(b.getBallColor());
////                d.drawCircle(x,y,r);
////            }
////            for (Line l:boardLines()){
////                int x1 = (int)l.getStart().getX(),y1 =(int)l.getStart().getY(),x2 =(int)
////                        l.getEnd().getX(),y2=(int)l.getEnd().getY();
////                d.drawLine(x1,y1,x2,y2);
////            }
////
////            for (Block b:roadBackground()){
////                int x = (int)b.getCollisionRectangle().getUpperLeft().getX();
////                int y = (int)b.getCollisionRectangle().getUpperLeft().getY();
////                int w = b.getCollisionRectangle().getWidth(), h =
////                        b.getCollisionRectangle().getHeight();
////                d.setColor(b.getBlockColor());
////                d.fillRectangle(x,y,w,h);
////            }
////            for (Ball b:sun()){
////                int x = b.getX(),y=b.getY(),r=b.getSize();
////                d.setColor(b.getBallColor());
////                d.fillCircle(x,y,r);
////            }
////            for (Sprite s:cars()){
////                s.drawOn(d);
////            }
////            d.setColor(Color.BLACK);
////            d.fillRectangle(0,0,800,600);
//            drawBackground(d);
//
//
//            for (Block b:blocks()){
//                int x = (int)b.getCollisionRectangle().getUpperLeft().getX();
//                int y = (int)b.getCollisionRectangle().getUpperLeft().getY();
//                int w = b.getCollisionRectangle().getWidth(), h =
//                        b.getCollisionRectangle().getHeight();
//                d.setColor(b.getBlockColor());
//                d.fillRectangle(x,y,w,h);
//                d.setColor(Color.WHITE);
//                d.drawRectangle(x,y,w,h);
//            }
//            gui.show(d);
//
//        }
//    }

    @Override
    public void drawOn(DrawSurface d) {
        if (levelNum == 1) {
            this.drawLevel1(d);
        } else if (levelNum == 2) {
            this.drawLevel2(d);
        } else if (levelNum == 3) {
            this.drawLevel3(d);
        }
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game g) {
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }
}
