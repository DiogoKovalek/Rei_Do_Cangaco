/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import main.Sound;
import world.World;

/**
 *
 * @author diogo
 */
public class Bullet extends Entity {

    private double vspd, hspd;
    private BufferedImage[] spriteBullet = {Game.spritesheet.getSprite(16 * 3 + 6, 16 * 1 + 0, this.width, this.height),
        Game.spritesheet.getSprite(16 * 3 + 6, 16 * 1 + 4, this.width, this.height),
        Game.spritesheet.getSprite(16 * 3 + 6, 16 * 1 + 8, this.width, this.height),
        Game.spritesheet.getSprite(16 * 3 + 6, 16 * 1 + 12, this.width, this.height)};

    public Bullet(double x, double y, int width, int height, double speed, double vspd, double hspd) {
        super(x, y, width, height, speed, null);
        this.vspd = vspd;
        this.hspd = hspd;
        Sound.mShoot.play();
    }

    public void tick() {
        x += vspd;
        y += hspd;
        
        if(this.x > World.WIDTH*World.TILE_SIZE || this.x < 0 || this.y > World.HEIGHT*World.TILE_SIZE || this.y < 0){
            Game.bullets.remove(this);
        }
        
        if(World.colisionWallTile(this)){
            Game.bullets.remove(this);
            
        }
    }
    

    public void render(Graphics g) {
        g.drawImage(spriteBullet[0], this.getX(), this.getY(), null);
    }

    
}
