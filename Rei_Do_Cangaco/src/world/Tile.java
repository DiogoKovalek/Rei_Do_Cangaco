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
    
    public static BufferedImage Tile_Floor = Game.spritesheet.getSprite(16*7, 16*0, 16, 16);
    public static BufferedImage[] Tile_Tree = {Game.spritesheet.getSprite(16*9, 16*0, 16, 16),
    Game.spritesheet.getSprite(16*10, 16*0, 16, 16)};
    public static BufferedImage Tile_Floor_Rock1 = Game.spritesheet.getSprite(16*4, 16*0, 16, 16);
    public static BufferedImage Tile_Floor_Rock2 = Game.spritesheet.getSprite(16*5, 16*0, 16, 16);
    public static BufferedImage Tile_Floor_Rock3 = Game.spritesheet.getSprite(16*6, 16*0, 16, 16);
    public static BufferedImage Tile_Floor_Sand2 = Game.spritesheet.getSprite(16*8, 16*0, 16, 16);
    public static BufferedImage Tile_Wall = Game.spritesheet.getSprite(16*11, 16*0, 16, 16);
    
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
