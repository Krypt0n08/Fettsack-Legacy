package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class MouseInput implements MouseMotionListener {

    private static int mouseX;
    private static int mouseY;

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }
}
