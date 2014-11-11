package snake.activity.menu;

import engine.Activity;
import engine.Engine;
import engine.Menu;
import snake.Config;

import java.awt.*;

/**
 * Created by Никита on 11/11/2014.
 */
public class Level extends Menu {
    protected void init() {
        this.title = "Current level: " + Config.getLevelSize();
        this.items = new MenuItem[4];
        this.items[0] = new MenuItem("Small", "setSize", 1);
        this.items[1] = new MenuItem("Medium", "setSize", 2);
        this.items[2] = new MenuItem("Big", "setSize", 3);
        this.items[3] = new MenuItem("Back", "back");
        this.items[0].setSelected(true);
        this.keyReleased = false;
    }

    public void setSize(Integer levelSize) {
        switch(levelSize) {
            case 1: Config.FIELD_HEIGHT = 20; Config.FIELD_WIDTH = 20; break;
            case 2: Config.FIELD_HEIGHT = 35; Config.FIELD_WIDTH = 35; break;
            case 3: Config.FIELD_HEIGHT = 50; Config.FIELD_WIDTH = 50; break;
        }
        this.title = "Current levelSize: " + levelSize;
    }

    public void back() {
        Activity menu = new snake.activity.Menu();
        Engine.gate().setActivity(menu);
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(Config.MENU_WIDTH, Config.MENU_HEIGHT);
    }
}
