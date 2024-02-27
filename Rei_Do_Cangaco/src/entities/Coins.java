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
public class Coins extends Entity{
    
    private int Value;
    private int frame = 0, maxFrame = (int) (60*8), index = 0;
    
    private BufferedImage coin1 = Game.spritesheet.getSprite(16*4, 16*10, 16, 16);
    private BufferedImage coin5 = Game.spritesheet.getSprite(16*5, 16*10, 16, 16);
    
    public Coins(double x, double y, int width, int height, double speed, BufferedImage sprite) {
        super(x, y, width, height, speed, sprite);
        if(Entity.rand.nextInt(200) <= 5){
            Value = 5;
            System.out.println("this is luck");
        }else{
            Value = 1;
        }
        
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
        if(Value == 1 && index%2 == 0){
            g.drawImage(coin1, this.getX(), this.getY(), null);
        }else if(Value == 5 && index%2 == 0){
            g.drawImage(coin5, this.getX(), this.getY(), null);
        }
    }
    
    public int getValue(){
        return Value;
    }
}
