package game;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Simple background music player using WAV files.
 */
public class MusicPlayer {

    private Clip clip;

    /**
     * Loads and starts looping background music.
     */
    public void playLoop(String path) {
        try {
            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(
                            getClass().getResource(path)
                    );

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (UnsupportedAudioFileException |
                 IOException |
                 LineUnavailableException e) {

            System.err.println("Could not play music!");
            e.printStackTrace();
        }
    }

    /**
     * Stops the music.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
