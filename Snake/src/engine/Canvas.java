package engine;

import snake.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/**
 * Created by User on 31.08.2014.
 */
public class Canvas extends java.awt.Canvas {
    protected Graphics graphics;
    public Canvas(Dimension dimension) {
        addKeyListener(new KeyListener() {

            Engine engine = Engine.gate();

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Typed " + e.getKeyCode());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                engine.registerKey(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                engine.unregisterKey(e.getKeyCode());
            }
        });
        setPreferredSize(dimension);
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
