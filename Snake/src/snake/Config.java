package snake;

/**
 * Created by User on 31.08.2014.
 */
public class Config {
    // 20 = Small, 35 = medium, 50 = big
    public static int FIELD_WIDTH = 20;
    public static int FIELD_HEIGHT = 20;

    public static int INFO_PANEL_HEIGHT = 22;
    public static int CELL_SIZE = 10;
    public static int SNAKE_SPEED = 2;
    public static int SPEED_MODIFIER = 5;
    public static int BIG_FRUIT_STEPS = 10;
    public static int BIG_FRUIT_LIFETIME = 3500;
    public static int CHANGE_SPEED_AFTER_FRUITS = 20;

    public static int MENU_HEIGHT = 480;
    public static int MENU_WIDTH = 390;

    public static int getLevelSize() {
        if (Config.FIELD_HEIGHT == 20) {
            return 1;
        } else if (Config.FIELD_HEIGHT == 35) {
            return 2;
        } else if (Config.FIELD_HEIGHT == 50) {
            return 3;
        }
        return 0;
    }
}
