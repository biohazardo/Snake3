package snake.activity.menu;

import engine.Activity;
import engine.Engine;
import engine.Menu;
import snake.Config;

import java.awt.*;

/**
 * Created by Никита on 11/11/2014.
 */
public class Speed extends Menu {
    protected void init() {
        this.title = "Current speed: " + Config.SNAKE_SPEED;
        this.items = new MenuItem[6];
        this.items[0] = new MenuItem("Speed 1", "setSpeed", 1);
        this.items[1] = new MenuItem("Speed 2", "setSpeed", 2);
        this.items[2] = new MenuItem("Speed 3", "setSpeed", 3);
        this.items[3] = new MenuItem("Speed 4", "setSpeed", 4);
        this.items[4] = new MenuItem("Speed 5", "setSpeed", 5);
        this.items[5] = new MenuItem("Back", "back");
        this.items[0].setSelected(true);
        this.keyReleased = false;
    }

    public void setSpeed(Integer speed) {
        Config.SNAKE_SPEED = speed;
        this.title = "Current speed: " + Config.SNAKE_SPEED;
    }

    public void back() {
        Activity menu = new snake.activity.Menu();
        Engine.gate().setActivity(menu);
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(Config.MENU_WIDTH, Config.MENU_HEIGHT + 100);
    }
}
