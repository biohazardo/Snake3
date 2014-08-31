package engine;

import java.awt.*;

/**
 * Created by User on 31.08.2014.
 */
abstract public class Activity {
    abstract public void render(Graphics graphics);
    abstract public void update(long delta);
}
