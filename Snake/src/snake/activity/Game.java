package snake.activity;

import engine.Activity;
import snake.Config;
import snake.object.Field;
import snake.object.Snake;

import java.awt.*;

/**
 * Created by User on 31.08.2014.
 */
public class Game extends Activity {
    Field field;
    Snake snake;
    public Game() {
        field = new Field(new Dimension(Config.FIELD_WIDTH, Config.FIELD_HEIGHT));
        snake = new Snake(field.getCenter());
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void render(Graphics graphics) {
        this.clearScreen(graphics);
        this.field.render(graphics);
        this.snake.render(graphics);
    }
}
