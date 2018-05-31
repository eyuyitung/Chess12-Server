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

    Music(String song)  {
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

    /*** play ***********************************************
     * Purpose: Play music from start and loop indefinitely *
     * Parameters: none                                     *
     * Returns: none                                        *
     ******************************************************/
    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY);     // Start playing
        }
    }
    /*** stop ***********************************************
     * Purpose: stop music if music is playing              *
     * Parameters: none                                     *
     * Returns: none                                        *
     ******************************************************/
    public void stop() {
        if (clip.isRunning())
            clip.stop();   // Stop the player if it is still running
    }
}
