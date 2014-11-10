package engine;

import java.awt.*;
import java.lang.reflect.Method;

/**
 * Created by Никита on 11/11/2014.
 */
abstract public class Menu extends Activity{

    protected String title = "";
    protected MenuItem[] items;
    protected boolean keyReleased = true;
    protected boolean startingGame = false;

    abstract protected void init();

    public Menu() {
        this.init();
    }

    public void update(long delta) {
        Engine engine = Engine.gate();
        if (engine.isKeyPressed(38)) { // up button
            this.changeSelectedItem("up");
            this.keyReleased = false;
        } else if (engine.isKeyPressed(40)) { // down button
            this.changeSelectedItem("down");
            this.keyReleased = false;
        } else if (engine.isKeyPressed(10)) { // Enter pressed
            this.callMenuItem();
            this.keyReleased = false;
        } else {
            // can go next item
            this.keyReleased = true;
        }
    }

    protected void callMenuItem()
    {
        if (this.keyReleased == false) {
            return;
        }
        MenuItem item = this.items[this.getSelectedItemIndex()];
        try {
            Method method;
            if (item.getParam() != null) {
                method = this.getClass().getDeclaredMethod(item.methodName, Integer.class);
            } else {
                method = this.getClass().getDeclaredMethod(item.methodName);
            }

            if (item.getParam() == null) {
                method.invoke(this);
            } else {
                Object[] args = {item.getParam()};
                method.invoke(this, item.getParam());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void render(Graphics graphics) {
        this.clearScreen(graphics);
        this.renderTitle(graphics);
        this.renderMenuItems(graphics);
    }

    public void renderTextAtCenter(Graphics graphics, String text, int topOffset) {
        Engine engine = Engine.gate();

        FontMetrics fm = graphics.getFontMetrics();
        int x = (engine.getCanvas().getWidth() - fm.stringWidth(text)) / 2;

        graphics.drawString(text, x, topOffset);
    }

    public void renderTitle(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Consolas",Font.BOLD,30));
        this.renderTextAtCenter(graphics, this.title, 150);
    }

    public void renderMenuItems(Graphics graphics) {
        int offset = 0;
        for (MenuItem item:this.items) {
            item.render(graphics, offset);
            offset++;
        }
    }

    protected int getSelectedItemIndex()
    {
        int selectedIndex = -1;
        for(int i = 0; i < this.items.length; i++) {
            if (this.items[i].selected) {
                selectedIndex = i;
            }
        }
        return selectedIndex;
    }

    protected void changeSelectedItem(String direction) {
        if (this.keyReleased == false) {
            return;
        }
        int selectedIndex = this.getSelectedItemIndex();
        if (selectedIndex == -1) {
            return;
        }
        if ((selectedIndex == 0 && direction == "up") || (selectedIndex == this.items.length - 1 && direction == "down")) {
            return;
        }
        // unset all
        this.items[selectedIndex].setSelected(false);
        if (direction == "up") {
            this.items[selectedIndex - 1].setSelected(true);
        } else if (direction == "down") {
            this.items[selectedIndex + 1].setSelected(true);
        }

    }

    public class MenuItem{
        private String title;
        public String methodName;
        private int totalOffset = 300;
        public boolean selected = false;
        public Object param;

        public MenuItem(String title, String methodName) {
            this.title = title;
            this.methodName = methodName;
            this.param = null;
        }

        public MenuItem(String title, String methodName, Object param) {
            this.title = title;
            this.methodName = methodName;
            this.param = param;
        }

        public Object getParam() {
            return this.param;
        }

        /**
         *
         * @param graphics
         * @param position Положение элемента среди ему подобных
         */
        public void render(Graphics graphics, int position) {
            graphics.setColor(this.selected ? Color.YELLOW : Color.WHITE);
            graphics.setFont(new Font("Consolas",Font.BOLD,20));
            renderTextAtCenter(graphics, this.title, this.totalOffset + position * 30);
        }

        public void setSelected(Boolean value) {
            this.selected = value;
        }
    }
}
