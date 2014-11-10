package snake.object;

import snake.Config;
import snake.object.field.BigFruit;
import snake.object.field.InfoPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 31.08.2014.
 */
public class Field {
    public Dimension size;
    private Random generator;
    public InfoPanel infoPanel;
    private int bigFruitCounter = 0;
    protected ArrayList<Fruit> fruits = new ArrayList<Fruit>();
    protected ArrayList<BigFruit> bigFruits = new ArrayList<>();

    public Field(Dimension size) {
        generator = new Random();
        this.size = size;
        this.infoPanel = new InfoPanel(this);
    }

    public void render(Graphics graphics) {
        this.infoPanel.render(graphics);
        for (Fruit fruit : fruits) {
            fruit.render(graphics);
        }
        for (BigFruit bigFruit : bigFruits) {
            bigFruit.render(graphics);
        }
    }

    public void update(double delta) {
        this.generateFood();
    }

    public Point getCenter() {
        return new Point((int) (size.getWidth() / 2), (int) Math.floor(size.getHeight() / 2));
    }

    public void generateFood() {
        if (this.fruits.size() > 0) {
            return;
        }
        // Создаем еду для змейки
        // Выбираем рандомную точку, проверяем, нет ли там змеи, и создаем фрукт
        int x = this.generator.nextInt((int) size.getWidth());
        int y = this.generator.nextInt((int) size.getHeight());
        Fruit fruit = new Fruit(x, y);
        this.fruits.add(fruit);
        this.bigFruitCounter++;
        if (this.bigFruitCounter >= Config.BIG_FRUIT_STEPS) {
            this.generateBigFruit();
            this.bigFruitCounter = 0;
        }
    }

    private void generateBigFruit() {
        if (this.bigFruits.size() > 0) {
            return;
        }
        int x = this.generator.nextInt((int) size.getWidth() - 1);
        int y = this.generator.nextInt((int) size.getHeight() - 1);
        BigFruit bigFruit = new BigFruit(x, y);
        this.bigFruits.add(bigFruit);
    }

    public Fruit getFruit(int x, int y) {
        for (Fruit fruit : fruits) {
            if (fruit.x == x && fruit.y == y) {
                return fruit;
            }
        }
        return null;
    }

    public void eatFruit(Fruit fruit) {
        this.fruits.remove(fruit);
    }

    public BigFruit getBigFruit(int x, int y) {
        for (BigFruit bigFruit : bigFruits) {
            if (bigFruit.containsCell(x, y)) {
                return bigFruit;
            }
        }
        return null;
    }

    public void eatBigFruit(BigFruit bigFruit) {
        this.bigFruits.remove(bigFruit);
    }

    public boolean outOfBounds(Point position) {
        return position.getX() < 0 || position.getY() < 0 || position.getX() >= size.getWidth() || position.getY() >= size.getHeight();
    }
}
