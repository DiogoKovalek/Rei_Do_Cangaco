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
 * @author diogo
 */
public class WallTile extends Tile {

    public static BufferedImage[] Tile_Wall = {Game.spritesheet.getSprite(16 * 11, 16 * 0, 16, 16),
        Game.spritesheet.getSprite(16 * 11, 16 * 1, 16, 16),
        Game.spritesheet.getSprite(16 * 11, 16 * 2, 16, 16)};

    public WallTile(int x, int y, BufferedImage sprite, boolean isCollision) {
        super(x, y, sprite, isCollision);
    }
}
