package snake.object.field;

import snake.Config;

import java.awt.*;

/**
 * Created by Никита on 11/11/2014.
 */
public class BigFruit {
    public int x;
    public int y;

    public BigFruit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(
                x * Config.CELL_SIZE,
                y * Config.CELL_SIZE + Config.INFO_PANEL_HEIGHT,
                2 * Config.CELL_SIZE,
                2 * Config.CELL_SIZE);
    }

    public boolean containsCell(int x, int y) {
        return (this.x <= x && this.x + 1 >= x && this.y <= y && this.y + 1 >= y);

    }
}
