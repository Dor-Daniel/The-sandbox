package Game;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class PauseScreen implements Animation {

    private static void drawBackground(DrawSurface surface) {
        // Draw a background with a gradient color
        int width = surface.getWidth();
        int height = surface.getHeight();
        Color startColor = new Color(17, 28, 143);
        Color endColor = new Color(10, 238, 169);

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

    public void doOneFrame(DrawSurface d) {
        drawBackground(d);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);

    }

    public boolean shouldStop() {
        return true;
    }

    @Override
    public int waitFor() {
        return 0;
    }
}
