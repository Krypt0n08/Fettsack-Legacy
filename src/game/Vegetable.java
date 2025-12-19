package game;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Vegetable extends GameObject {

    private BufferedImage sprite;

    public Vegetable(int x, int y, String path) {
        super(x, y);
        loadImage(path);
        size = sprite.getWidth();
    }

    private void loadImage(String path) {
        try {
            sprite = ImageIO.read(
                    getClass().getResource(path)
            );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Could not load player image!");
            e.printStackTrace();
        }
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
        if (sprite == null) return;

        Graphics2D g2 = (Graphics2D) g;
        AffineTransform oldTransform = g2.getTransform();

        g2.translate(
                x + sprite.getWidth() / 2.0,
                y + sprite.getHeight() / 2.0
        );

        g2.drawImage(
                sprite,
                -sprite.getWidth() / 2,
                -sprite.getHeight() / 2,
                null
        );

        g2.setTransform(oldTransform);
    }
}
