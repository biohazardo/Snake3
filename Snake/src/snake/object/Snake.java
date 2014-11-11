package snake.object;

import engine.Engine;
import snake.Config;
import snake.activity.Game;
import snake.object.field.BigFruit;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by User on 31.08.2014.
 */
public class Snake {
    private ArrayList<Limb> limbs;
    private Character direction;
    private int speed;
    private int growAmount = 0;
    private long lastStep;
    private long milliBetweenSteps;
    private boolean notMovedYet = false;

    public Snake(Point startPoint) {
        limbs = new ArrayList<Limb>();
        initFirstLimbs(startPoint);
        direction = 'D';
        setSpeed(Config.SNAKE_SPEED);

        lastStep = System.currentTimeMillis();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        milliBetweenSteps = (1000 / speed) / Config.SPEED_MODIFIER;
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
        this.changeDirection();
    }

    public void makeStep(long delta) {
        // We need to found interval between steps.
        if (lastStep > System.currentTimeMillis() - milliBetweenSteps) {
            return;
        }

        Limb lastLimb = limbs.remove(limbs.size() - 1);
        Limb firstLimb = limbs.get(0);

        Point lastLimbOldPosition = lastLimb.getPosition();

        Point firstLimbPosition = firstLimb.getPosition();
        if (direction == 'D') {
            lastLimb.setPosition(new Point((int) firstLimbPosition.getX(), (int) firstLimbPosition.getY() + 1));
        }
        switch (direction) {
            case 'D':
                lastLimb.setPosition(new Point((int) firstLimbPosition.getX(), (int) firstLimbPosition.getY() + 1));
                break;
            case 'U':
                lastLimb.setPosition(new Point((int) firstLimbPosition.getX(), (int) firstLimbPosition.getY() - 1));
                break;
            case 'L':
                lastLimb.setPosition(new Point((int) firstLimbPosition.getX() - 1, (int) firstLimbPosition.getY()));
                break;
            case 'R':
                lastLimb.setPosition(new Point((int) firstLimbPosition.getX() + 1, (int) firstLimbPosition.getY()));
                break;
        }
        limbs.add(0, lastLimb);

        lastLimb.setHead(true);
        firstLimb.setHead(false);

        if (lastLimb.outOfBounds()) {
            lastLimb.teleport(direction);
        }

        if (this.snakeEatItself(lastLimb)) {
            Game.gate().gameOver();
        }

        Game game = (Game) Engine.gate().getActivity();
        Point point = lastLimb.getPosition();
        Fruit fruit = game.getField().getFruit((int) point.getX(), (int) point.getY());
        if (fruit != null) {
            game.getField().eatFruit(fruit);
            this.growAmount++;
        }
        BigFruit bigFruit = game.getField().getBigFruit((int) point.getX(), (int) point.getY());
        if (bigFruit != null) {
            game.getField().eatBigFruit(bigFruit);
            this.growAmount += 3;
        }
        if (this.growAmount > 0) {
            this.grow(lastLimbOldPosition);
        }
        this.notMovedYet = false;
        lastStep = System.currentTimeMillis();
    }

    private boolean snakeEatItself(Limb lastLimb) {
        for (Limb limb: limbs) {
            if (lastLimb == limb || limbs.indexOf(limb) == 0) { continue; }
            if (limb.getPosition().getX() == lastLimb.getPosition().getX()
                    && limb.getPosition().getY() == lastLimb.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }

    protected void changeDirection() {
        if (this.notMovedYet == true) {
            return;
        }
        Engine engine = Engine.gate();
        Character tempDirection;
        if (engine.isKeyPressed(37)) {
            tempDirection = 'L';
        } else if (engine.isKeyPressed(38)) {
            tempDirection = 'U';
        } else if (engine.isKeyPressed(39)) {
            tempDirection = 'R';
        } else if (engine.isKeyPressed(40)) {
            tempDirection = 'D';
        } else {
            tempDirection = 'N';
        }
        if (tempDirection == 'N' || (tempDirection == 'L' && direction == 'R') || (tempDirection == 'R' && direction == 'L')
                || (tempDirection == 'U' && direction == 'D') || (tempDirection == 'D' && direction == 'U')) {
            return;
        }
        if (direction != tempDirection) {
            direction = tempDirection;
            this.notMovedYet = true;
        }
    }

    public void grow(Point position) {
        Limb limb = new Limb(false, position);
        this.limbs.add(limb);
        Game.gate().addScore(Config.SNAKE_SPEED * Config.SNAKE_SPEED);
        this.growAmount--;
    }

    public Field getField() {
        Game game = Game.gate();
        return game.getField();
    }

    public boolean isFillCell(int x, int y) {
        for (Limb limb:limbs) {
            if (limb.getPosition().getX() == x && limb.getPosition().getY() == y) {
                return true;
            }
        }
        return false;
    }

    protected class Limb {
        private Point position;
        private boolean isHead;

        public Limb(boolean isHead, Point point) {
            this.isHead = isHead;
            position = point;
        }

        public void render(Graphics graphics) {
            graphics.setColor(isHead ? Color.WHITE : Color.GREEN);
            graphics.fillRect(
                    (int) position.getX() * Config.CELL_SIZE,
                    (int) position.getY() * Config.CELL_SIZE + Config.INFO_PANEL_HEIGHT,
                    Config.CELL_SIZE,
                    Config.CELL_SIZE);
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

        public boolean outOfBounds() {

            return getField().outOfBounds(this.getPosition());
        }

        public void teleport(Character direction) {
            switch (direction) {
                case 'U':
                    this.position.y = (int) getField().size.getHeight() - 1;
                    break;
                case 'D':
                    this.position.y = 0;
                    break;
                case 'L':
                    this.position.x = (int) getField().size.getWidth() - 1;
                    break;
                case 'R':
                    this.position.x = 0;
                    break;

            }
        }

    }
}
