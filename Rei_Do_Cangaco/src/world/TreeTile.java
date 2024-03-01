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
public class TreeTile extends Tile{
    
    private int frame = 0, maxFrames = 30, index = 0, maxIndex = 1;
    
    public static BufferedImage[][] Tile_Tree = {{Game.spritesheet.getSprite(16*9, 16*0, 16, 16), Game.spritesheet.getSprite(16*10, 16*0, 16, 16)},
    {Game.spritesheet.getSprite(16*9, 16*1, 16, 16), Game.spritesheet.getSprite(16*10, 16*1, 16, 16)},
    {Game.spritesheet.getSprite(16*9, 16*2, 16, 16), Game.spritesheet.getSprite(16*10, 16*2, 16, 16)}};
    
    public TreeTile(int x, int y, BufferedImage sprite, boolean isCollision) {
        super(x, y, sprite, isCollision);
    }
    
    public void tick(){
        
        frame++;
        if(frame == maxFrames){
            frame = 0;
            index++;
            if(index > maxIndex){
                index = 0;
            }
        }
    }
    public void render(Graphics g){
        g.drawImage(Tile_Tree[getWorldForDraw()][index], x - Camera.x, y - Camera.y, null);
    }
}
