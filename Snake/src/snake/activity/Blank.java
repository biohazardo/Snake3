package snake.activity;

import engine.Activity;
import snake.Config;
import snake.Game;

import java.awt.*;

/**
 * Created by User on 31.08.2014.
 */
public class Blank  extends Activity {
    @Override
    public void update(long delta) {

    }



    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(Config.MENU_WIDTH, Config.MENU_HEIGHT);
    }
}
