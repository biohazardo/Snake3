package snake;

import engine.Activity;
import engine.Engine;

import java.awt.*;

public class Game {
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    private Engine engine;
    public static void main(String[] args) {
        Game game = new Game();
    }

    public Game() {
        Activity activity = new snake.activity.Menu();
        // for testing
//        Activity activity = new snake.activity.Game();
        this.engine = new Engine(getCanvasDimension(activity));
        this.engine.setActivity(activity);
        this.engine.startLoop();
    }

    public Dimension getCanvasDimension(Activity activity)
    {
        return activity.getDimension();
    }
}
