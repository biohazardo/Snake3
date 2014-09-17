package engine;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 31.08.2014.
 */
public class Engine {
    protected Window window;
    protected Canvas canvas;
    protected Activity activity;
    protected Dimension dimension;
    private static Engine instance;
    protected Map<Integer,Boolean> keys = new HashMap<Integer,Boolean>();

    public static Engine gate() {
        return Engine.instance;
    }

    public Engine(Dimension dimension) {
        Engine.instance = this;
        this.window = new Window(dimension);
        this.canvas = this.window.getCanvas();
    }

    public void startLoop() {
        Thread gameThread = new Thread(new Loop(this));
        gameThread.setPriority(Thread.MIN_PRIORITY);
        gameThread.start();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void update(long delta) {
        if (activity == null) {
            return;
        }
        activity.update(delta);
    }

    public void draw() {
        if (activity == null) {
            return;
        }
        Graphics graphics = this.canvas.startRender();
        if (graphics != null) {
            this.activity.render(graphics);
        }
        this.canvas.endRender();

    }

    public class Loop implements Runnable {
        boolean isRunning = true;
        Engine engine;

        public Loop(Engine engine) {
            this.engine = engine;
        }

        @Override
        public void run() {
            long lastTime = System.currentTimeMillis();
            long delta;

            while (isRunning) {
                delta = System.currentTimeMillis() - lastTime;
                lastTime = System.currentTimeMillis();

                engine.update(delta);
                engine.draw();
            }
        }
    }

    public void registerKey(Integer key) {
        this.keys.put(key, true);
    }

    public void unregisterKey(Integer key) {
        this.keys.remove(key);
    }

    public Boolean isKeyPressed(Integer key) {
        return this.keys.containsKey(key) && this.keys.get(key);
    }
}
