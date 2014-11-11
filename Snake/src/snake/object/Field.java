package snake.object;

import snake.Config;
import snake.activity.Game;
import snake.object.field.BigFruit;
import snake.object.field.InfoPanel;

import java.awt.*;
import java.lang.reflect.Array;
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
    private int eatenFruitCount = 0;
    protected ArrayList<Fruit> fruits = new ArrayList<>();
    protected ArrayList<BigFruit> bigFruits = new ArrayList<>();
    protected ArrayList<BigFruit> bigFruitsToRemove = new ArrayList<>();

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
        for(BigFruit bigFruit: bigFruits) {
            bigFruit.update(delta);
        }
        for (BigFruit bigFruit : bigFruitsToRemove) {
            bigFruits.remove(bigFruit);
        }
        this.bigFruitsToRemove.clear();
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
        if (!Game.gate().getSnake().isFillCell(x,y)) {
            this.fruits.add(fruit);
            this.eatenFruitCount++;
        }

        // Большой фрукт появляется раз в N шагов
        this.bigFruitCounter++;
        if (this.bigFruitCounter >= Config.BIG_FRUIT_STEPS) {
            this.generateBigFruit();
        }

        if (eatenFruitCount > Config.CHANGE_SPEED_AFTER_FRUITS) {
            eatenFruitCount = 0;
            Config.SNAKE_SPEED++;
            Game.gate().getSnake().setSpeed(Config.SNAKE_SPEED);
        }
    }

    private void generateBigFruit() {
        if (this.bigFruits.size() > 0) {
            return;
        }
        int x = this.generator.nextInt((int) size.getWidth() - 1);
        int y = this.generator.nextInt((int) size.getHeight() - 1);
        BigFruit bigFruit = new BigFruit(x, y);
        if (!bigFruit.intersectsWithSnake(Game.gate().getSnake())) {
            this.bigFruits.add(bigFruit);
            this.bigFruitCounter = 0;
        }
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

    public void destroyBigFruit(BigFruit bigFruit) {
        this.bigFruitsToRemove.add(bigFruit);
    }
}
