package snake.activity;

import engine.Activity;
import engine.Engine;
import snake.Config;
import snake.object.Field;
import snake.object.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;

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

    public Field getField() {
        return this.field;
    }

    public Snake getSnake() {
        return this.snake;
    }

    @Override
    public void update(long delta) {
        if (Engine.gate().isKeyPressed(KeyEvent.VK_ESCAPE)) {
            this.closeGame();
        }
        field.update(delta);
        snake.update(delta);
    }

    @Override
    public void render(Graphics graphics) {
        this.clearScreen(graphics);
        this.field.render(graphics);
        this.snake.render(graphics);
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(Config.FIELD_WIDTH * Config.CELL_SIZE, Config.FIELD_HEIGHT * Config.CELL_SIZE + Config.INFO_PANEL_HEIGHT);
    }

    public void closeGame() {
        Engine.gate().setActivity(new Menu());
    }
}
