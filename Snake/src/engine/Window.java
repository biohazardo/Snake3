package engine;

import javax.swing.*;

/**
 * Created by User on 31.08.2014.
 */
public class Window extends JFrame{
    protected Canvas canvas;
    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = new Canvas();
        getContentPane().add(canvas);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

}
