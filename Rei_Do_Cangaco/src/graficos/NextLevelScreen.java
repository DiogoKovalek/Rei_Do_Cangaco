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
import world.World;

/**
 *
 * @author diogo
 */
public class NextLevelScreen {

    private final static BufferedImage face = Game.spritesheet.getSprite(16 * 2, 16 * 10, 16, 16);
    private int frame = 0, maxFrame = 50, index = 0, maxindex = 1;

    public NextLevelScreen() {

    }

    public void tick() {
        frame++;
        if (frame == maxFrame) {
            frame = 0;
            index++;
            if (index > maxindex) {
                index = 0;
            }
        }
        if (Game.player.space) {
            Game.player.space = false;
            Sound.mOverWorld.loop();
            Game.GameState = Game.GameStatePossible[1];
        }
    }

    public void render(Graphics g) {
        g.drawImage(face, 80, 90, 32, 32, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(Game.world.worldNum + "-" + Game.world.levelNum, 150, 115);
        g.setFont(new Font("Arial", Font.BOLD, 10) {
        });
        if (index == 0) {
            g.drawString("PRESS SPACE", 104, 150);
        }

    }
}
