/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.*;
import javax.sound.sampled.*;

/**
 *
 * @author diogo
 */
public class Sound {

    public static class Clips {

        public Clip[] clips;
        private int p;
        private int count;
        
        private boolean paused = false;

        public Clips(byte[] buffer, int count) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
            if (buffer == null) {
                return;
            }

            clips = new Clip[count];
            this.count = count;

            for (int i = 0; i < count; i++) {
                clips[i] = AudioSystem.getClip();
                clips[i].open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
            }
        }

        public void play() {
            if (clips == null) {
                return;
            }
            clips[p].stop();
            clips[p].setFramePosition(0);
            clips[p].start();
            p++;
            if (p >= count) {
                p = 0;
            }
        }

        public void loop() {
            if (clips == null) {
                return;
            }
            clips[p].loop(999999999);
        }
        public void stopLoop(){
            if(clips == null){
                return;
            }
            clips[p].stop();
            clips[p].setFramePosition(0);
        }
        
        public void pausedLoop(){
            if(clips == null || paused){
                return;
            }
            paused = true;
            clips[p].stop();
        }
        
        public void resumeLoop(){
            if(clips == null || !paused){
                return;
            }
            paused = false;
            clips[p].loop(999999999);
        }
    }

    public static Clips mOverWorld = load("/res/OverWorld.wav", 1);
    public static Clips mShoot = load("/res/shoot.wav", 1);
    public static Clips mHurt = load("/res/hurt.wav",1);
    public static Clips mCoin = load("/res/coin.wav", 1);

    private static Clips load(String name, int count) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataInputStream dis = new DataInputStream(Sound.class.getResourceAsStream(name));

            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = dis.read(buffer)) >= 0) {
                baos.write(buffer, 0, read);
            }
            dis.close();
            byte[] data = baos.toByteArray();
            return new Clips(data, count);
        } catch (Exception e) {
            try {
                return new Clips(null, 0);
            } catch (Exception ee) {
                return null;
            }
        }
    }
}
