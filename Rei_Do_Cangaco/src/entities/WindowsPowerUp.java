/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import entities.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import main.Game;

/**
 *
 * @author diogo
 */

// The WindowsPowerUp class controls all power-ups.
public class WindowsPowerUp extends Entity{
    
    private final static BufferedImage WindowsPowerUp = Game.spritesheet.getSprite(16*0, 16*10, 22, 22);
    public PowerUp powerUpStored;
    public List<PowerUp> powerUpUsing;
    
    public WindowsPowerUp() {
        super(261, 5, 22, 22, 0, null);
        powerUpUsing = new ArrayList<PowerUp>();
    }
    
    public void tick(){
        // Use Item stored ===========================
        if(Game.player.space && powerUpStored != null && !Game.player.died){
            powerUpStored.initializePowerUp();
            powerUpStored = null;
            Game.player.space = false;
        }
        //============================================
        for(int i = 0; i < powerUpUsing.size(); i++){
            powerUpUsing.get(i).runningPowerUp();
        }
        
    }
    
    public void resetListPowerUpUsing(){
//        for(int i =0; i < powerUpUsing.size(); i++){
//            powerUpUsing.get(0).endPowerUp();
//            
//        }
        for(int i = powerUpUsing.size()-1; i >= 0; i--){
            powerUpUsing.get(i).endPowerUp();
        }
    }
            
    
    public void render(Graphics g){
        g.drawImage(WindowsPowerUp, this.getX(), this.getY(),  null);
        if(powerUpStored != null){
            g.drawImage(powerUpStored.pwCurrent, this.getX()+3, this.getY()+3, null);
        }
    }
}
