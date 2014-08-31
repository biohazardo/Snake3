package engine;

import snake.Game;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by User on 31.08.2014.
 */
public class Canvas extends java.awt.Canvas {
    protected Graphics graphics;
    public Canvas() {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    public Graphics startRender() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return null;
        }

        this.graphics = bs.getDrawGraphics();
        return this.graphics;
    }

    public void endRender() {
        if (graphics != null) {
            graphics.dispose();
            getBufferStrategy().show();
        }
    }
}
