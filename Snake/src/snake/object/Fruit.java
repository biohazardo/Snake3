package snake.object;

import snake.Config;

import java.awt.*;

/**
 * Created by Никита on 11/11/2014.
 */
public class Fruit {
    public int x;
    public int y;

    public Fruit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x * Config.CELL_SIZE, y * Config.CELL_SIZE, Config.CELL_SIZE, Config.CELL_SIZE);
    }

}
