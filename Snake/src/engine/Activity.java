package engine;

import snake.Game;

import java.awt.*;

/**
 * Created by User on 31.08.2014.
 */
abstract public class Activity {
    abstract public void render(Graphics graphics);

    abstract public void update(long delta);

    public void clearScreen(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

    }
}
