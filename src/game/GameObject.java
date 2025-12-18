package game;

import java.awt.*;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int size = 40;
    protected boolean markedForRemoval = false; // 👈 neu

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(GamePanel panel);
    public abstract void draw(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public boolean isMarkedForRemoval() {
        return markedForRemoval;
    }
}

