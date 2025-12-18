package game;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends GameObject {

    private double angleRad = 0.0;

    private final double speed = 20.0;

    public Player(int x, int y) {
        super(x, y);
        size = 40;
    }

    @Override
    public void update(GamePanel panel) {

        int dx = MouseInput.getMouseX() - x;
        int dy = MouseInput.getMouseY() - y;
        angleRad = Math.atan2(dy, dx);

        x += (int) (Math.cos(angleRad) * speed);
        y += (int) (Math.sin(angleRad) * speed);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        AffineTransform oldTransform = g2.getTransform();

        g2.translate(x + size / 2.0, y + size / 2.0);
        g2.rotate(angleRad);
        g2.setColor(Color.BLUE);
        g2.fillOval(-size / 2, -size / 2, size, size);

        g2.setTransform(oldTransform);
    }
}
