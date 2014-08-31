package snake.object;

import java.awt.*;

/**
 * Created by User on 31.08.2014.
 */
public class Field {
    Dimension size;
    public Field(Dimension size) {
        this.size = size;
    }

    public void render(Graphics graphics) {

    }

    public Point getCenter()
    {
        return new Point((int)(size.getWidth() / 2), (int)Math.floor(size.getHeight() / 2));
    }
}
