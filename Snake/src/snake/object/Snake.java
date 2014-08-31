package snake.object;

import snake.Config;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by User on 31.08.2014.
 */
public class Snake {
    private ArrayList<Limb> limbs;
    public Snake(Point startPoint)
    {
        limbs = new ArrayList<Limb>();
        Limb firstLimb = new Limb(startPoint);
        limbs.add(firstLimb);
    }

    public void render(Graphics graphics) {
        for (Limb limb: limbs) {
            limb.render(graphics);
        }
    }

    protected class Limb {
        private Point position;
        public Limb(Point point) {
            position = point;
        }
        public void render(Graphics graphics) {
            graphics.setColor(Color.GREEN);
            graphics.fillRect((int)position.getX() * Config.CELL_SIZE, (int)position.getY() * Config.CELL_SIZE, Config.CELL_SIZE, Config.CELL_SIZE);
        }

    }
}
