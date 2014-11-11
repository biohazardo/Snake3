package snake.object.field;

import snake.Config;
import snake.activity.Game;
import snake.object.Snake;

import java.awt.*;

/**
 * Created by Никита on 11/11/2014.
 */
public class BigFruit {
    public int x;
    public int y;
    public double lifetime = 0;

    public BigFruit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(double delta) {
        lifetime += delta;
        if (lifetime > Config.BIG_FRUIT_LIFETIME) {
            Game.gate().getField().destroyBigFruit(this);
        }
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

    public boolean intersectsWithSnake(Snake snake) {
        if (snake.isFillCell(x,y) || snake.isFillCell(x+1,y) || snake.isFillCell(x,y+1) || snake.isFillCell(x+1,y+1)) {
            return true;
        }
        return false;
    }
}
