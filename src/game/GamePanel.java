package game;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1500;
    public static final int HEIGHT = 800;

    private Thread gameThread;
    private boolean running = false;

    private Player player;
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Random random = new Random();

    private final ArrayList<GameObject> objectsToAdd = new ArrayList<>();

    private long gameTimer = 45_000;
    private long startTime;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);

        addMouseMotionListener(new MouseInput());

        initGame();
    }


    private void initGame() {
        player = new Player(WIDTH / 2, HEIGHT / 2);
        objects.add(player);

        for (int i = 0; i < 5; i++) {
            spawnFood();
        }

        spawnVegetable();
    }

    public void startGame() {
        running = true;
        startTime = System.currentTimeMillis();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            update();
            repaint();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException ignored) {}
        }
    }

    private void update() {
        long elapsed = System.currentTimeMillis() - startTime;
        if (elapsed >= gameTimer) {
            running = false;
            System.out.println("Game Over! Score: " + ScoreManager.getScore());
        }

        for (GameObject obj : objects) {
            obj.update(this);
        }

        objects.removeIf(GameObject::isMarkedForRemoval);
        
        objects.addAll(objectsToAdd);
        objectsToAdd.clear();
    }


    public void spawnFood() {
        objectsToAdd.add(
                new Food(random.nextInt(WIDTH), random.nextInt(HEIGHT))
        );
    }

    public void spawnVegetable() {
        objects.add(new Vegetable(random.nextInt(WIDTH), random.nextInt(HEIGHT)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (GameObject obj : objects) {
            obj.draw(g);
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + ScoreManager.getScore(), 20, 20);
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }
}
