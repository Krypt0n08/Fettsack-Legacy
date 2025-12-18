package game;

import java.awt.*;

public class Vegetable extends GameObject {

    public Vegetable(int x, int y) {
        super(x, y);
        size = 50;
    }

    @Override
    public void update(GamePanel panel) {
        for (GameObject obj : panel.getObjects()) {
            if (obj instanceof Player && obj.getBounds().intersects(getBounds())) {
                System.out.println("You lost! Score: " + ScoreManager.getScore());
                System.exit(0);
            }
        }
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, size, size);
    }
}
