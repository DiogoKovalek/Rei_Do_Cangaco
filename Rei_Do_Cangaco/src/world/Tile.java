/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;

/**
 *
 * @author USUARIO
 */
public class Tile {
    
    protected BufferedImage sprite;
    protected int x,y;
    protected boolean isCollision;
    
    public Tile(int x, int y, BufferedImage sprite, boolean isCollision){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.isCollision = isCollision;
    }
    
    public void tick(){
        
    }
    // Get World num for draw
    public static int getWorldForDraw(){
        switch (World.worldNum) {
            case "01":
                return 0;
            case "02":
                return 1;
            case "03":
                return 2;
            default:
                throw new AssertionError();
        }
    }
    
    public void render(Graphics g){
        g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
    }
    
    public boolean isCollision(){
        return isCollision;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
