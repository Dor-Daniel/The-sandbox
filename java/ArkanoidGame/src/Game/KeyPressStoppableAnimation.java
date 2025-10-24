package Game;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final Animation animation;
    private boolean stop = false;

    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor,
                                      Animation animation) {
        this.animation = animation;
        this.keyboardSensor = keyboardSensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop =
                    true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public int waitFor() {
        return 0;
    }
}
