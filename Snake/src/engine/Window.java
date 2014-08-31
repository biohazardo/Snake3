package engine;

import javax.swing.*;

/**
 * Created by User on 31.08.2014.
 */
public class Window extends JFrame{
    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        getContentPane().add(canvas);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
