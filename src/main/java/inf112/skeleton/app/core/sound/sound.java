package inf112.skeleton.app.core.sound;
import java.applet.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class sound {
    public static void shootLaser () throws Exception{

        URL url = sound.class.getResource("back.wav");
        AudioClip clip = Applet.newAudioClip(url);
        clip.play();
    }

    public static void laserHit () throws Exception {
        URL url = sound.class.getResource("back.wav");
        AudioClip clip = Applet.newAudioClip(url);
        Thread.sleep(1000);
        clip.play();
    }

    public static void Main (String[] args) throws Exception {
        // TEST for playing clips
        sound playclip = new sound();
        playclip.shootLaser();
    }
}

