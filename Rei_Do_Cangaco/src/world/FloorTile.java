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
public class FloorTile extends Tile {

    public static BufferedImage[] Tile_Floor_Sand1 = {Game.spritesheet.getSprite(16 * 7, 16 * 0, 16, 16),
        Game.spritesheet.getSprite(16 * 7, 16 * 1, 16, 16),
        Game.spritesheet.getSprite(16 * 7, 16 * 2, 16, 16)};
    public static BufferedImage[] Tile_Floor_Sand2 = {Game.spritesheet.getSprite(16 * 8, 16 * 0, 16, 16),
        Game.spritesheet.getSprite(16 * 8, 16 * 1, 16, 16),
        Game.spritesheet.getSprite(16 * 8, 16 * 2, 16, 16)};
    public static BufferedImage[] Tile_Floor_Rock2 = {Game.spritesheet.getSprite(16 * 5, 16 * 0, 16, 16),
        Game.spritesheet.getSprite(16 * 5, 16 * 1, 16, 16),
        Game.spritesheet.getSprite(16 * 5, 16 * 2, 16, 16)};
    public static BufferedImage[] Tile_Floor_Rock3 = {Game.spritesheet.getSprite(16 * 6, 16 * 0, 16, 16),
        Game.spritesheet.getSprite(16 * 6, 16 * 1, 16, 16),
        Game.spritesheet.getSprite(16 * 6, 16 * 2, 16, 16)};

    public FloorTile(int x, int y, BufferedImage sprite, boolean isCollision) {
        super(x, y, sprite, isCollision);
    }
}
