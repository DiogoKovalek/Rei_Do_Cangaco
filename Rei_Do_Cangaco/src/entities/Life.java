/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;

/**
 *
 * @author diogo
 */
public class Life extends Entity{
    
    private static BufferedImage pwLife = Game.spritesheet.getSprite(16*2, 16*10, 16, 16);
    private int frame = 0, maxFrame = (int) (60*8), index = 0;
    private int valueLife = 1;
    
    public Life(double x, double y, int width, int height, double speed, BufferedImage sprite) {
        super(x, y, width, height, speed, sprite);
    }
    
    public void tick(){
        depth = 2; 
        frame++;
        if(frame == maxFrame){
            index++;
            maxFrame = 20;
            frame = 0;
            if(index == 20){    
                Game.entities.remove(this);
            }
        }
    }
    
    public void render(Graphics g){
        if(index%2 == 0){
            g.drawImage(pwLife, this.getX(), this.getY(), null);
        }
    }
    public int getValue(){
        return valueLife;
    }
}
