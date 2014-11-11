package snake.activity;

import engine.Activity;
import engine.Engine;
import snake.Config;
import snake.activity.game.GameOverTitle;
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

    public boolean paused = false;
    private int score = 0;
    private boolean keyReleased;
    private static Game instance;
    private GameOverTitle gameOverTitle;
    public static Game gate() {
        return Game.instance;
    }

    public Game() {
        Game.instance = this;
        field = new Field(new Dimension(Config.FIELD_WIDTH, Config.FIELD_HEIGHT));
        snake = new Snake(field.getCenter());
    }

    public int getScore() {return score;}
    public void addScore(int delta) {this.score += delta;}

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
        } else {
            this.keyReleased = true;
        }

        if (!this.paused) {
            field.update(delta);
            snake.update(delta);
        }
    }

    @Override
    public void render(Graphics graphics) {
        this.clearScreen(graphics);
        this.field.render(graphics);
        this.snake.render(graphics);
        if (this.gameOverTitle != null) {
            this.gameOverTitle.render(graphics);
        }
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(Config.FIELD_WIDTH * Config.CELL_SIZE, Config.FIELD_HEIGHT * Config.CELL_SIZE + Config.INFO_PANEL_HEIGHT);
    }

    public void closeGame() {
        Engine.gate().setActivity(new Menu());
    }

    public void gameOver() {
        this.paused = true;
        this.gameOverTitle = new GameOverTitle();

    }
}
