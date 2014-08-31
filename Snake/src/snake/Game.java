package snake;

import engine.Activity;
import engine.Engine;
import snake.activity.Blank;

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
        this.engine = new Engine();
        Activity blank = new Blank();
        this.engine.setActivity(blank);
        this.engine.startLoop();
    }
}
