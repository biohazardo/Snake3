package engine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 31.08.2014.
 */
public class Window extends JFrame{
    protected Canvas canvas;
    public Window(Dimension dimension) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = new Canvas(dimension);
        getContentPane().add(canvas);
        pack();
        setResizable(false);
        setVisible(true);
        setTitle("Game Engine");
        setLocationRelativeTo(null);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

}
