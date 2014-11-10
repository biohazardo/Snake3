package snake.object.field;

import snake.Config;
import snake.activity.Game;
import snake.object.Field;

import java.awt.*;

/**
 * Created by Никита on 11/11/2014.
 */
public class InfoPanel {
    private Field field;
    private Game game;

    public InfoPanel(Field field) {
        this.field = field;
        this.game = Game.gate();
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, (int) game.getDimension().getWidth(), Config.INFO_PANEL_HEIGHT);
        renderLabels(graphics);
    }

    private void renderLabels(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Consolas", Font.BOLD, 16));
        graphics.drawString("Speed: " + Config.SNAKE_SPEED, 5, 16);

        int point = game.getScore();
        String text = "Score: " + point;
        FontMetrics fm = graphics.getFontMetrics();
        int x = (int) game.getDimension().getWidth() - fm.stringWidth(text);
        graphics.drawString(text, x - 5, 16);

    }

}
