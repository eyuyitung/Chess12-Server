package chess;

import javax.sound.sampled.*;
import java.io.*;


public enum Music {

    MII("Mii Channel.wav"),
    SONIC("Green Hills.wav");

    public enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;
    private Clip clip;

    Music(String song) {
        try {
            String localDir = System.getProperty("user.dir");
            File file = new File(localDir + "\\Music\\" + song);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }
    }
    public void stop() {
        if (clip.isRunning())
            clip.stop();   // Stop the player if it is still running
    }
}
