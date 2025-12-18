package game;

public class ScoreManager {

    private static int score = 0;

    public static void addScore(int value) {
        score += value;
    }

    public static int getScore() {
        return score;
    }

    public static void reset() {
        score = 0;
    }
}
