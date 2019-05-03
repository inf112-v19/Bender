package inf112.skeleton.app.core.sound;

//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
import java.io.*;

public class Sound {

    public static void laserHit() throws IOException {
        String gongFile = "BringItOnShort.wav";
        InputStream in = new FileInputStream(gongFile);
        //AudioStream audioStream = new AudioStream(in);
        //AudioPlayer.player.start(audioStream);
    }

    public static void shootLaser() throws IOException {
        String gongFile = "Flash-laser-03.wav";
        InputStream in = new FileInputStream(gongFile);
        //sAudioStream audioStream = new AudioStream(in);
    }

}

