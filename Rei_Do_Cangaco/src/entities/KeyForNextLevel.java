/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import world.World;

/**
 *
 * @author diogo
 */
public class KeyForNextLevel extends Entity {

    private BufferedImage[] sprite = {Game.spritesheet.getSprite(16 * 9, 16 * 3, 16, 16),
        Game.spritesheet.getSprite(16 * 10, 16 * 3, 16, 16),
        Game.spritesheet.getSprite(16 * 11, 16 * 3, 16, 16)};
    private BufferedImage arrow = Game.spritesheet.getSprite(16*8, 16*3, 16,16);
    
    private int frame = 0, maxFrame = 50, index = 0, maxIndex = 1;
        
    private byte level;
    public KeyForNextLevel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
        super(x, y, width, height, speed, sprite);
        switch(World.worldNum){
            case "01":
                this.level = 0;
                break;
            case "02":
                this.level = 1;
                break;
            case "03":
                this.level = 2;
                break;
        }
    }

    public void tick() {
        //Colision with player ======================
        if(isColidding(this, Game.player)){
            Game.control.nextLevel();
            Game.entities.remove(this);
        }      
        // ==========================================
        
        //Animation =================================
        frame++;
        if(frame == maxFrame){
            index++;
            frame=0;
            if(index > maxIndex){
                index = 0;
            }
        }
        // ==========================================
    }

    public void render(Graphics g) {
        g.drawImage(sprite[level], this.getX(), this.getY(), null);
        if(index == 1){
            g.drawImage(arrow, this.getX(), (this.getY() - 16), null);
        }
    }
}
