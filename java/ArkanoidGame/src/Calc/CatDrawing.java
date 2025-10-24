

package Calc;

import GameObjects.Block;
import biuoop.DrawSurface;
import biuoop.GUI;
import Geometry.Point;

import java.awt.*;

public class CatDrawing {
    public static void main(String[] args) {
        // Create the blocks for the cat face
        Block[] catFaceBlocks = createCatFaceBlocks();

        // Create the GUI and show the drawing
        GUI gui = new GUI("Cat Face", 400, 400);
        DrawSurface surface = gui.getDrawSurface();
        drawBackground(surface);
        drawBlocks(surface, catFaceBlocks);
        gui.show(surface);
    }

    private static Block[] createCatFaceBlocks() {
        // Define the blocks for the cat face
        Block[] catFaceBlocks = new Block[5];

        Point headPoint = new Point(150, 100);
        int headWidth = 100;
        int headHeight = 100;
        Color headColor = new Color(244, 223, 175);
        catFaceBlocks[0] = new Block(headPoint, headWidth, headHeight, headColor);

        Point leftEarPoint = new Point(130, 80);
        int earWidth = 20;
        int earHeight = 60;
        Color earColor = new Color(255, 127, 0);
        catFaceBlocks[1] = new Block(leftEarPoint, earWidth, earHeight, earColor);

        Point rightEarPoint = new Point(250, 80);
        catFaceBlocks[2] = new Block(rightEarPoint, earWidth, earHeight, earColor);

        Point leftEyePoint = new Point(170, 130);
        int eyeWidth = 20;
        int eyeHeight = 20;
        Color eyeColor = Color.GREEN;
        catFaceBlocks[3] = new Block(leftEyePoint, eyeWidth, eyeHeight, eyeColor);

        Point rightEyePoint = new Point(210, 130);
        catFaceBlocks[4] = new Block(rightEyePoint, eyeWidth, eyeHeight, eyeColor);

        return catFaceBlocks;
    }

    private static void drawBackground(DrawSurface surface) {
        // Draw a background with a gradient color
        int width = surface.getWidth();
        int height = surface.getHeight();
        Color startColor = new Color(135, 206, 250);
        Color endColor = new Color(70, 130, 180);

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
    private static void drawBlocks(DrawSurface surface, Block[] blocks) {
        // Draw multiple blocks
        for (Block block : blocks) {
            drawBlock(surface, block);
        }
    }

    private static void drawBlock(DrawSurface surface, Block block) {
        // Draw a block
        block.drawOn(surface);
    }
}