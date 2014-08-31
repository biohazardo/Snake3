package snake;

import engine.Activity;
import engine.Engine;

import java.awt.*;

/**
 * Created by User on 31.08.2014.
 */
public class Game {
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    private Engine engine;
    public static void main(String[] args) {
        Game game = new Game();
    }

    public Game() {
        this.engine = new Engine(getCanvasDimension());
        Activity blank = new snake.activity.Game();
        this.engine.setActivity(blank);
        this.engine.startLoop();
    }

    public Dimension getCanvasDimension()
    {
        return new Dimension(Config.FIELD_WIDTH * Config.CELL_SIZE, Config.FIELD_HEIGHT * Config.CELL_SIZE);
    }
}
