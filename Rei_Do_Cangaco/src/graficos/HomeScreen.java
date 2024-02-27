/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import main.Sound;

/**
 *
 * @author diogo
 */
public class HomeScreen {
    
    private static BufferedImage Title = Game.spritesheet.getSprite(16*0, 16*5, 6*16, 16*4);
    private static BufferedImage PressSpace = Game.spritesheet.getSprite(16*0, 16*9, 16*6, 16*1);
    
    private static int frame = 0, maxFrame=50, index = 0;
    private static boolean isPresedSpace = false;
    private static int optionChoice = 1, maxOpitionChoice = 2;
    
    public HomeScreen(){
           
    }
    
    public void tick(){
        if(isPresedSpace){
            if(Game.player.down || Game.player.downAin){
                if(optionChoice >= maxOpitionChoice){
                    optionChoice = 1;
                }else{
                    optionChoice++;
                }
                Game.player.down = false;
                Game.player.downAin = false;
            }else if(Game.player.up || Game.player.upAin){
                if(optionChoice <= 1){
                    optionChoice = maxOpitionChoice;
                }else{
                    optionChoice--;
                }
                Game.player.up = false;
                Game.player.upAin = false;
            }
            if(Game.player.space){
                if(optionChoice == 1){ // One Player
                    Game.GameState = Game.GameStatePossible[1];
                }else if(optionChoice == 2){ //Two Player
                    Game.GameState = Game.GameStatePossible[1];
                }
                Sound.mOverWorld.loop();
                Game.player.space = false;
            }
        }else{
        frame++;
        if(frame == maxFrame){
            index++;
            if(index==2){
                index = 0;
            }
            frame = 0;
        }
        }
        if(Game.player.space){
            isPresedSpace = true;
            Game.player.space = false;
        }
        
         
    }
    public void render(Graphics g){
        g.drawImage(Title, 95, 80, null);
        if(index == 0 && ! isPresedSpace)
            g.drawImage(PressSpace, 95, 144, null);
        if(isPresedSpace){
            g.setFont(new Font("Arial", Font.BOLD,10));
            g.setColor(Color.WHITE);
            g.drawString("ONE PLAYER", 115, 160);
            g.drawString("TWO PLAYER", 115, 176);
            if(optionChoice == 1){
                g.drawString(":::", 95, 160);
            }else if(optionChoice == 2){
                g.drawString(":::", 95, 176);
            }
        }
    }
}
