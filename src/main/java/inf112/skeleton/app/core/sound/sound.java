package inf112.skeleton.app.core.sound;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;

public class sound {


    public static void main(String[] args) throws Exception
    {
        shootLaser();
        //laserHit();

        // open the sound file as a Java input stream
        //String gongFile = "BringItOnShort.wav";
        //InputStream in = new FileInputStream(gongFile);

        // create an audiostream from the inputstream
        // AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        //AudioPlayer.player.start(audioStream);
    }

    public static void laserHit() throws IOException {
        String gongFile = "BringItOnShort.wav";
        InputStream in = new FileInputStream(gongFile);

        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }

    public static void shootLaser() throws IOException {
        String gongFile = "Flash-laser-03.wav";
        InputStream in = new FileInputStream(gongFile);

        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }
}

