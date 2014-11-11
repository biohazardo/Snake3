package snake.activity.game;

import snake.activity.Game;

import java.awt.*;

/**
 * Created by Никита on 11/11/2014.
 */
public class GameOverTitle {
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0,0,0,200));
        graphics.fillRect(0, 0, (int)Game.gate().getDimension().getWidth(), (int)Game.gate().getDimension().getHeight());

        double width = Game.gate().getDimension().getWidth();
        double height = Game.gate().getDimension().getHeight();
        int stringWidth;
        int totalStringsHeight = 20+16+10;
        double top = (height - totalStringsHeight) / 2;



        graphics.setColor(Color.RED);
        String s = "GAME OVER";
        graphics.setFont(new Font("Consolas", Font.BOLD, 20));
        stringWidth = graphics.getFontMetrics().stringWidth(s);
        graphics.drawString(s, (int)((width - stringWidth)/2), (int)top+20);


        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Consolas", Font.BOLD, 16));
        s = "Score: " + Game.gate().getScore();
        stringWidth = graphics.getFontMetrics().stringWidth(s);
        graphics.drawString(s, (int)((width - stringWidth)/2), (int)top+20+16);


        graphics.setFont(new Font("Consolas", Font.BOLD, 10));
        s = "Press ESC to return";
        stringWidth = graphics.getFontMetrics().stringWidth(s);
        graphics.drawString(s, (int) ((width - stringWidth) / 2), (int) top + 20 + 16 + 10);

    }
}
