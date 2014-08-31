package snake.object;

import snake.Config;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by User on 31.08.2014.
 */
public class Snake {
    private ArrayList<Limb> limbs;
    private Character direction;
    private int speed;
    private long lastStep;
    private long milliBetweenSteps;

    public Snake(Point startPoint) {
        limbs = new ArrayList<Limb>();
        initFirstLimbs(startPoint);
        direction = 'U';
        setSpeed(Config.SNAKE_SPEED);

        lastStep = System.currentTimeMillis();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        milliBetweenSteps = 1000 / speed;
    }

    private void initFirstLimbs(Point startPoint) {
        Limb firstLimb = new Limb(true, startPoint);
        limbs.add(firstLimb);
        Limb secondLimb = new Limb(false, new Point((int) startPoint.getX(), (int) startPoint.getY() - 1));
        limbs.add(secondLimb);
        Limb thirdLimb = new Limb(false, new Point((int) startPoint.getX(), (int) startPoint.getY() - 2));
        limbs.add(thirdLimb);
    }


    public void render(Graphics graphics) {
        for (Limb limb : limbs) {
            limb.render(graphics);
        }
    }

    public void update(long delta) {

        this.makeStep(delta);
    }

    public void makeStep(long delta) {
        // We need to found interval between steps.
        if (lastStep > System.currentTimeMillis() - milliBetweenSteps) {
            return;
        }

        Limb lastLimb = limbs.remove(limbs.size() - 1);
        Limb firstLimb = limbs.get(0);
        Point firstLimbPosition = firstLimb.getPosition();
        lastLimb.setPosition(new Point((int)firstLimbPosition.getX(), (int)firstLimbPosition.getY() + 1));
        limbs.add(0, lastLimb);

        lastLimb.setHead(true);
        firstLimb.setHead(false);

        lastStep = System.currentTimeMillis();
    }

    protected class Limb {
        private Point position;
        private boolean isHead;

        public Limb(boolean isHead, Point point) {
            this.isHead = isHead;
            position = point;
        }

        public void render(Graphics graphics) {
            graphics.setColor(isHead ? Color.RED : Color.GREEN);
            graphics.fillRect((int) position.getX() * Config.CELL_SIZE, (int) position.getY() * Config.CELL_SIZE, Config.CELL_SIZE, Config.CELL_SIZE);
        }

        public Point getPosition() {
            return position;
        }

        public void setPosition(Point position) {
            this.position = position;
        }

        public void setHead(boolean value) {
            this.isHead = value;
        }

    }
}
