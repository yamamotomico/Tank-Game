package src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoundPlayer  {
    private AudioInputStream soundStream;
    private String musicFile;
    private Clip soundClip;

    public SoundPlayer(String music){
        this.musicFile = music;
        try{
            soundStream = AudioSystem.getAudioInputStream(SoundPlayer.class.getResource(music));
            soundClip = AudioSystem.getClip();
            soundClip.open(soundStream);
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "No sound documents are fouond");
        }
            Runnable run = () -> {
                while(true){
                    soundClip.start();
                    soundClip.loop(soundClip.LOOP_CONTINUOUSLY);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            };
            Thread thread = new Thread(run);
            thread.start();
    }
}

