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
public class Horn extends Entity {

    private BufferedImage[] ENEMY1_MOVE = {Game.spritesheet.getSprite(16 * 0, 16 * 3, 16, 16),
        Game.spritesheet.getSprite(16 * 1, 16 * 3, 16, 16)};
    private int frame = 0, maxFrame = 17, index = 0, maxIndex = 1;
    private int frameDying = 0, maxFrameDying = 6, indexDying = 0, maxIndexDying = 5;

    private boolean died = false;
    private int life = 4;

    public Horn(double x, double y, int width, int height, double speed, BufferedImage sprite) {
        super(x, y, width, height, speed, sprite);
    }

    public void tick() {
        depth = 2;
        // MOVING ====================================
        
        if(!died){
            if (!isColiddingWithPlayer()) {
                if (path == null || path.size() == 0) {
                    Vector2i start = new Vector2i((int) (x / 16), (int) (y / 16));
                    Vector2i end = new Vector2i((int) (Game.player.x / 16), (int) (Game.player.y / 16));
                    path = AStar.findPath(Game.world, start, end);
                }
            } else { // Damege in Player

            }
            //atualizando a localizacao do player
            if (new Random().nextInt(100) < 20 && path != null && x % 16 == 0 && y % 16 == 0) {
                Vector2i start = new Vector2i((int) (x / 16), (int) (y / 16));
                Vector2i end = new Vector2i((int) (Game.player.x / 16), (int) (Game.player.y / 16));
                path = AStar.findPath(Game.world, start, end);
            }

            followPath(path);
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
    
    //Aplicando A*
    public void followPath(List<Node> path){
        if(path != null){
            if(path.size() > 0){
                Vector2i target = path.get(path.size() - 1).tile;
                //xprev = x;
                //yprev = y;
                if(this.getX() < target.x * 16 && !isColidding(this.getX() + 1, this.getY())){
                    x += speed;
                }else if(x > target.x * 16 && !isColidding(this.getX() - 1, this.getY())){
                    x -= speed;
                } 
                //Se quiser que os inimigos andem apenas nos lados, continue com else if, se nao
                //ele vai andar pelas diagonais
                else if(y < target.y * 16 && !isColidding(this.getX(), this.getY() + 1)){
                    y += speed;
                }else if(y > target.y * 16 && !isColidding(this.getX(), this.getY() - 1)){
                    y -= speed;
                }
                
                if(x == target.x * 16 && y == target.y * 16){
                    path.remove(path.size() - 1);
                }
                
                
            }
        }
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
                    if(Entity.rand.nextInt(100) <= 20){
                        int aux = rand.nextInt(105);
                        if(aux < 50){// Coin        
                            Coins coin = new Coins(this.getX(), this.getY(), 16, 16, 0, null);
                            Game.entities.add(coin);
                        }else if(aux >= 50 && aux < 100){// Power-Up
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