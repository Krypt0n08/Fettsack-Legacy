package game;

import java.awt.*;

public class Food extends GameObject {

    public Food(int x, int y) {
        super(x, y);
        size = 30;
    }

    @Override
    public void update(GamePanel panel) {
        for (GameObject obj : panel.getObjects()) {
            if (obj instanceof Player && obj.getBounds().intersects(getBounds())) {
                markedForRemoval = true;
                ScoreManager.addScore(1);
                panel.spawnFood();
                break;
            }
        }
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, size, size);
    }
}
