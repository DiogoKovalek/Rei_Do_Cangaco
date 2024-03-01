/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficos;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HexFormat;
import main.Controller;
import main.Game;
import static main.Game.player;
import world.World;



/**
 *
 * @author diogo
 */
public class UI {
    
    
    private BufferedImage ContLife = Game.spritesheet.getSprite(16*2, 16*10, 16, 16);
    private BufferedImage Clock = Game.spritesheet.getSprite(16*3, 16*10, 16, 16);
    private BufferedImage Coin = Game.spritesheet.getSprite(16*4, 16*10, 16, 16);
    private BufferedImage tutorial = Game.spritesheet.getSprite(16*6, 16*5, 16*5, 16*3);
    public boolean intitGame = false;
    
    private double porcentTime;
    
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(World.WIDTH*16, 0, Game.WIDTH - World.WIDTH, Game.WIDTH);
        g.drawImage(ContLife, 258, 30,null);
        g.drawImage(Coin, 258, 45,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 8));
        if(player.life >= 0){
            g.drawString("X"+ player.life, 273, 41);
        }else{
            g.drawString("X0", 273, 41);
        }
        g.drawString("X" + player.coins, 273, 56);
        g.drawImage(Clock, 0, 0, null);
        
        if(Game.control.timeGame <= Game.control.maxTimeGame*0.8){
            g.setColor(new Color(0XFFbee52f));
        }else{
            g.setColor(new Color(0xFFa40000));
        }
        porcentTime = ((Game.control.maxTimeGame/60) - (Game.control.timeGame/60))/(Game.control.maxTimeGame/60);
        g.fillRect(16, 5, (int) ((World.WIDTH*World.TILE_SIZE - 25)*porcentTime), 6);
        
        if(!intitGame){
            g.drawImage(tutorial, 90, 180, null);
            if (Game.player.up || Game.player.right || Game.player.down || Game.player.left
                    || Game.player.upAin || Game.player.rightAin || Game.player.downAin || Game.player.leftAin
                    || Game.player.space) {
                intitGame = true;
            }
        }
        
    }
}
