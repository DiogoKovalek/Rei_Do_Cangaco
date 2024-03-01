/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world;

import entities.Entity;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Game;

/**
 *
 * @author diogo
 */
public class SpawTile extends Tile {

    public static BufferedImage[] Tile_Floor_Rock1 = {Game.spritesheet.getSprite(16 * 4, 16 * 0, 16, 16),
        Game.spritesheet.getSprite(16 * 4, 16 * 1, 16, 16),
        Game.spritesheet.getSprite(16 * 4, 16 * 2, 16, 16)};

    public SpawTile(int x, int y, BufferedImage sprite, boolean isCollision) {
        super(x, y, sprite, isCollision);
    }

    public boolean isFree() {
        for (int i = 0; i < Game.enemys.size(); i++) {
            Entity e = (Entity) Game.enemys.get(i);
            Rectangle thisMask = new Rectangle(this.getX(), this.getY(), World.TILE_SIZE, World.TILE_SIZE);
            Rectangle eMask = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());

            if (thisMask.intersects(eMask)) {
                return false;
            }
        }
        return true;
    }
}
