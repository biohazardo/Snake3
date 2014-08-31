package engine;

import snake.Game;

import java.awt.*;

/**
 * Created by User on 31.08.2014.
 */
public class Canvas extends java.awt.Canvas{
    public Canvas() {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }
}
