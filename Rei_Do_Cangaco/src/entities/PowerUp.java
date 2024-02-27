/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;

/**
 *
 * @author diogo
 */
public class PowerUp extends Entity {

    private final static BufferedImage pwCoffee = Game.spritesheet.getSprite(16 * 6, 16 * 10, 16, 16);
    private final static BufferedImage pwWheel = Game.spritesheet.getSprite(16 * 2, 16 * 11, 16, 16);
    private final static BufferedImage pwMachineGun = Game.spritesheet.getSprite(16 * 3, 16 * 11, 16, 16);
//    private final static BufferedImage pwBomb = Game.spritesheet.getSprite(16 * 4, 16 * 11, 16, 16);
    private final static BufferedImage pw12 = Game.spritesheet.getSprite(16 * 5, 16 * 11, 16, 16);
    private final static BufferedImage pwSherif = Game.spritesheet.getSprite(16 * 6, 16 * 11, 16, 16);
    private final static BufferedImage pwTimeStop = Game.spritesheet.getSprite(16 * 3, 16 * 10, 16, 16);

    public BufferedImage pwCurrent;

    // Variables to run PowerUp ==============================
    private int timePowerUp, maxTimePowerUp;

    //Coffe =============================
    private double speedInit;
    private double speedADD = 0.5;
    //===================================
    //Machine Gun =======================
    private int framesToShotInit;
    private int framesToShotDiscounted = 11;
    //===================================
    
    

    //========================================================
    private int frame = 0, maxFrame = (int) (60 * 8), index = 0;

    public PowerUp(double x, double y, int width, int height, double speed, BufferedImage sprite) {
        super(x, y, width, height, speed, sprite);
        // Decided powerUp =====================
        int aux = rand.nextInt(200);
        
        if (aux < 50) {
            pwCurrent = pwCoffee;
        } else if (aux >= 50 && aux < 100) {
            pwCurrent = pwWheel;
        } else if (aux >= 100 && aux < 150) {
            pwCurrent = pwMachineGun;
        } else if (aux >= 150 && aux < 200) {
            pwCurrent = pw12;
        } else if (aux >= 200 && aux < 250) {
            pwCurrent = pwSherif;
        } else if (aux >= 250 && aux < 300) {
            pwCurrent = pwTimeStop;
        } 
        //======================================
    }

    // Running Power-Up =========================================================================
    public void initializePowerUp() {
        // if power-Up is not running =========================
        for (int i = 0; i < Game.windowsPowerUp.powerUpUsing.size(); i++) {
            if (Game.windowsPowerUp.powerUpUsing.get(i).pwCurrent.equals(this.pwCurrent)) {
                Game.windowsPowerUp.powerUpUsing.get(i).endPowerUp();
            }
        }
        //=====================================================
        // declaring duration and Condition====================
        this.timePowerUp = 0;
        if (this.pwCurrent.equals(pwCoffee)) { // Coffee
            this.maxTimePowerUp = 60 * 10;//Time
            speedInit = Game.player.speed;
            Game.player.speed += speedADD;
            
        } else if (this.pwCurrent.equals(pwWheel)) { // Wheel
            this.maxTimePowerUp = 60 * 10;//Time
            Game.player.wheelActivated = true;
            
        } else if (this.pwCurrent.equals(pwMachineGun)) { // Machine Gun
            this.maxTimePowerUp = 60 * 10;//Time
            framesToShotInit = Game.player.maxFramesToShot;
            Game.player.maxFramesToShot -= framesToShotDiscounted;
            Game.player.framesToShot = Game.player.maxFramesToShot;
            
        } else if (this.pwCurrent.equals(pw12)) { // 12
            this.maxTimePowerUp = 60 * 10;//Time
            Game.player.shotGunActivated = true;
            
        } else if (this.pwCurrent.equals(pwSherif)) { //Sherif
            this.maxTimePowerUp = 60 * 15;//Time
            
        } else if (this.pwCurrent.equals(pwTimeStop)) { //Time Stop -- ZA WARUDO
            this.maxTimePowerUp = 60 * 10;//Time
            
        }
        // ====================================================
        Game.windowsPowerUp.powerUpUsing.add(this);
    }

    public void runningPowerUp() {
        timePowerUp++;
        if (timePowerUp >= maxTimePowerUp) {
            this.endPowerUp();
        }
    }
    // ===========================================================================================

    // if Player died, end power-ups==============================================================
    public void endPowerUp() {
        if (this.pwCurrent.equals(pwCoffee)) {
            Game.player.speed = speedInit;
        } else if (this.pwCurrent.equals(pwWheel)) {
            Game.player.wheelActivated = false;
        } else if (this.pwCurrent.equals(pwMachineGun)) {
            Game.player.maxFramesToShot = framesToShotInit;
            Game.player.framesToShot = Game.player.maxFramesToShot;
        } else if (this.pwCurrent.equals(pw12)) {
            Game.player.shotGunActivated = false;
        } else if (this.pwCurrent.equals(pwSherif)) {
            
        } else if (this.pwCurrent.equals(pwTimeStop)) {

        }
        Game.windowsPowerUp.powerUpUsing.remove(this); // Remove this Power-Up
    }
    //============================================================================================

    public void tick() {
        depth = 2;
        frame++;
        if (frame == maxFrame) {
            index++;
            maxFrame = 20;
            frame = 0;
            if (index == 20) {
                Game.entities.remove(this);
            }
        }
    }

    public void render(Graphics g) {
        if (index % 2 == 0) {
            g.drawImage(pwCurrent, this.getX(), this.getY(), null);
        }
    }
}
