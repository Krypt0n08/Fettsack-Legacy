package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Player with a sprite image that
 * always rotates towards the mouse
 * and moves with constant speed.
 */
public class Player extends GameObject {

    private double angleRad = 0.0;
    private final double speed = 3.0;

    private BufferedImage sprite;

    public Player(int x, int y) {
        super(x, y);
        loadImage();
        size = sprite.getWidth(); // use image size
    }

    /**
     * Loads the player image from assets folder.
     */
    private void loadImage() {
        try {
            sprite = ImageIO.read(
                    getClass().getResource("/game/assets/Player.png")
            );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Could not load player image!");
            e.printStackTrace();
        }
    }

    @Override
    public void update(GamePanel panel) {

        // Rotate towards mouse
        int dx = MouseInput.getMouseX() - x;
        int dy = MouseInput.getMouseY() - y;
        angleRad = Math.atan2(dy, dx);

        // Constant forward movement
        x += (int) (Math.cos(angleRad) * speed);
        y += (int) (Math.sin(angleRad) * speed);
    }

    @Override
    public void draw(Graphics g) {
        if (sprite == null) return;

        Graphics2D g2 = (Graphics2D) g;
        AffineTransform oldTransform = g2.getTransform();

        // Move to center of sprite
        g2.translate(
                x + sprite.getWidth() / 2.0,
                y + sprite.getHeight() / 2.0
        );

        // Rotate sprite
        g2.rotate(angleRad);

        // Draw sprite centered
        g2.drawImage(
                sprite,
                -sprite.getWidth() / 2,
                -sprite.getHeight() / 2,
                null
        );

        g2.setTransform(oldTransform);
    }
}

