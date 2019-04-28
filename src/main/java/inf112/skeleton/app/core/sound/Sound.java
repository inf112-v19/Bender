package inf112.skeleton.app.core.sound;
import java.applet.*;
import java.net.*;

public class Sound {
    public static void Main (String[] args) throws Exception {
        // TEST for playing clips
        Sound playclip = new Sound();
        playclip.laserHit();
    }

    public static void shootLaser () throws Exception{

        URL url = Sound.class.getResource("");
        AudioClip clip = Applet.newAudioClip(url);
        clip.play();
    }

    public static void laserHit () throws Exception {
        URL url = Sound.class.getResource("Bring it on.wav");
        AudioClip clip = Applet.newAudioClip(url);
        Thread.sleep(1000);
        clip.play();
    }


}

