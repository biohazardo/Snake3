package snake.activity;

import engine.Activity;
import engine.Engine;
import snake.*;
import snake.activity.menu.Speed;

import java.awt.*;
import java.lang.reflect.Method;

/**
 * Created by User on 17.09.2014.
 */
public class Menu extends engine.Menu {
    protected void init() {
        this.keyReleased = false;
        this.title = "Ultra Snake Game!";
        this.items = new MenuItem[3];
        this.items[0] = new MenuItem("Start","startGame");
        this.items[1] = new MenuItem("Speed","changeSpeed");
        this.items[2] = new MenuItem("Exit","exit");
        this.items[0].setSelected(true);
    }


    public void startGame() {
        if (this.startingGame == true) {
            return;
        }
        this.startingGame = true;
        Activity activity = new snake.activity.Game();
        Engine.gate().setActivity(activity);
    }


    public void exit() {
        System.exit(0);
    }

    public void changeSpeed() {
        Activity speed = new Speed();
        Engine.gate().setActivity(speed);
    }


    @Override
    public Dimension getDimension() {
        return new Dimension(Config.MENU_WIDTH, Config.MENU_HEIGHT);
    }
}
