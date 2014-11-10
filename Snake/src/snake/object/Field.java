package snake.object;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 31.08.2014.
 */
public class Field {
    public Dimension size;
    private Random generator;
    protected ArrayList<Fruit> fruits = new ArrayList<Fruit>();

    public Field(Dimension size) {
        generator = new Random();
        this.size = size;
    }

    public void render(Graphics graphics) {
        for (Fruit fruit : fruits) {
            fruit.render(graphics);
        }
    }

    public void update(double delta) {
        this.generateFood();
    }

    public Point getCenter()
    {
        return new Point((int)(size.getWidth() / 2), (int)Math.floor(size.getHeight() / 2));
    }

    public void generateFood() {
        if (this.fruits.size() > 0) {
            return;
        }
        // Создаем еду для змейки
        // Выбираем рандомную точку, проверяем, нет ли там змеи, и создаем фрукт
        int x = this.generator.nextInt((int)size.getWidth()) + 1;
        int y = this.generator.nextInt((int)size.getHeight()) + 1;
        Fruit fruit = new Fruit(x, y);
        this.fruits.add(fruit);
    }

    public Fruit getFruit(int x, int y) {
        for (Fruit fruit: fruits) {
            if (fruit.x == x && fruit.y == y) {
                return fruit;
            }
        }
        return null;
    }

    public void eatFruit(Fruit fruit) {
        this.fruits.remove(fruit);
    }
}
