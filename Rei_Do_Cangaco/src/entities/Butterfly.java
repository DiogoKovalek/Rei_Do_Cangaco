/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import AStar.AStar;
import AStar.Node;
import AStar.Vector2i;
import static entities.Entity.ENEMY_DYING;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import main.Game;
import main.Sound;

/**
 *
 * @author diogo
 */
public class Butterfly extends Entity {

    private BufferedImage[] ENEMY1_MOVE = {Game.spritesheet.getSprite(16 * 2, 16 * 2, 16, 16),
        Game.spritesheet.getSprite(16 * 3, 16 * 2, 16, 16)};
    private int frame = 0, maxFrame = 17, index = 0, maxIndex = 1;
    private int frameDying = 0, maxFrameDying = 6, indexDying = 0, maxIndexDying = 5;

    private boolean died = false;
    private int life = 1;

    public Butterfly(double x, double y, int width, int height, double speed, BufferedImage sprite) {
        super(x, y, width, height, speed, sprite);
    }

    public void tick() {
        depth = 2;
        // MOVING ====================================
        // Algorithm made by Diogo
        if(!died){
            if(this.x <= Game.player.getX()){
                x += speed;
            }else{
                x -= speed;
            }
            if(this.y <= Game.player.getY()){
                y += speed;
            }else{
                y -= speed;
            }
        }
        // ===========================================
        // Is Dead and Damege to him==================
        if (!died) {
            colidingBullet();
        }
        // ===========================================

        // Animation =================================
        if (!died) {
            frame++;
            if (frame == maxFrame) {
                frame = 0;
                index++;
                if (index > maxIndex) {
                    index = 0;
                }
            }
        } else if (indexDying < maxIndexDying) {
            frameDying++;
            if (frameDying == maxFrameDying) {
                frameDying = 0;
                indexDying++;
            }
        } else if (indexDying == maxIndexDying && frameDying <= 60 * 7) {
            frameDying++;
        } else if (indexDying == maxIndexDying) {
            Game.entities.remove(this);
            for (int i = 0; i < Game.entities.size(); i++) {
                Entity e = Game.entities.get(i);
            }
        }

        // ============================================
    }
    
   
    
    public boolean isColiddingWithPlayer() {
        Rectangle enemyCurrent = new Rectangle(this.getX(), this.getY(), width, height); // cria retangulos ficticios
        Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16); // cria retangulos ficticios

        return enemyCurrent.intersects(player);
    }
    
    public boolean isColidding(int xNext, int yNext){
        Rectangle enemyCurrent = new Rectangle(xNext , yNext, width , height); // cria retangulos ficticios
        for(int i = 0; i < Game.enemys.size(); i++){
            Entity e = Game.enemys.get(i);
            if(e == this)
                continue;
            Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), width, height); // Cria uma mascara de colisao
            if(enemyCurrent.intersects(targetEnemy)){
                return true;
            }
        }
        
        return false;
    }

    public void colidingBullet() {
        for (int i = 0; i < Game.bullets.size(); i++) {
            Bullet b = Game.bullets.get(i);
            if (Entity.isColidding(this, b)) {
                this.life -= Game.player.bulletDamege;
                Game.bullets.remove(b);
                if (this.life <= 0) {
                    died = true;
                    Game.enemys.remove(this);
                    Sound.mHurt.play();
                    this.depth = 0;
                    // drop Item ===================================
                    if(Entity.rand.nextInt(100) <= 10){
                        int aux = rand.nextInt(105);
                        if(aux < 50){ // Coin       
                            Coins coin = new Coins(this.getX(), this.getY(), 16, 16, 0, null);
                            Game.entities.add(coin);
                        }else if(aux >= 50 && aux < 100){// Power-up
                            PowerUp powerUp = new PowerUp(this.getX(), this.getY(), 16, 16, 0, null);
                            Game.entities.add(powerUp);
                        }else if(aux >= 100){// Life
                            Life life = new Life(this.getX(), this.getY(),16,16,0,null);
                            Game.entities.add(life);
                        }
                    }
                    //=============================================         
                }
            }
        }
    }

    public void render(Graphics g) {
        if (!died) {
            g.drawImage(ENEMY1_MOVE[index], this.getX(), this.getY(), null);
        } else if (died) {
            g.drawImage(ENEMY_DYING[indexDying], this.getX(), this.getY(), null);

        }
    }
}
