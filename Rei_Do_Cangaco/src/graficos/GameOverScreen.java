/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import main.Game;

/**
 *
 * @author diogo
 */
public class GameOverScreen {
    
    private int frame=0, maxFrame=40, index = 0, maxIndex = 1;
    
    public GameOverScreen(){
        
    }
    public void tick(){
        frame++;
        if(frame == maxFrame){
            index++;
            frame = 0;
            if(index > maxIndex){
                index = 0;
            }        
        }
        if(Game.player.space){
            Game.player.space = false;
            Game.GameState = Game.GameStatePossible[0];
        }
    }
    
    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("GAME OVER", 85, 100);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        if(index == 0)
            g.drawString("PRESS SPACE FOR CONTINUE", 73, 150);
    }
}
