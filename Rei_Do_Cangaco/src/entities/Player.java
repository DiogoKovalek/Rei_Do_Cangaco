package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Controller;
import main.Game;
import main.Sound;
import world.World;
import static world.World.isFree;

public class Player extends Entity {

    private final static BufferedImage[] PLAYER_MOVE = {Game.spritesheet.getSprite(16 * 0, 16 * 0, 16, 16),
        Game.spritesheet.getSprite(16 * 1, 16 * 0, 16, 16),
        Game.spritesheet.getSprite(16 * 2, 16 * 0, 16, 16),
        Game.spritesheet.getSprite(16 * 3, 16 * 0, 16, 16)};
    private final static BufferedImage[] PLAYER_LEGS = {Game.spritesheet.getSprite(16 * 1, 16 * 1 + 0, 16, 3),
        Game.spritesheet.getSprite(16 * 1, 16 * 1 + 4, 16, 3),
        Game.spritesheet.getSprite(16 * 1, 16 * 1 + 9, 16, 3),
        Game.spritesheet.getSprite(16 * 1, 16 * 1 + 13, 16, 3),};
    private final static BufferedImage PLAYER_INIT = Game.spritesheet.getSprite(16 * 2, 16 * 1, 16, 16);
    private final static BufferedImage PLAYER_WIN = Game.spritesheet.getSprite(16 * 0, 16 * 1, 16, 16);
    private final static BufferedImage[] PLATER_DYING = {Game.spritesheet.getSprite(16 * 6, 16 * 4, 16, 16),
        Game.spritesheet.getSprite(16 * 7, 16 * 4, 16, 16),
        Game.spritesheet.getSprite(16 * 8, 16 * 4, 16, 16),
        Game.spritesheet.getSprite(16 * 9, 16 * 4, 16, 16),
        Game.spritesheet.getSprite(16 * 10, 16 * 4, 16, 16)};

    public boolean right, left, up, down;
    public boolean space;
    public boolean rightAin, leftAin, upAin, downAin;

    private final int createBulletZone = 0;
    private double bulletSpeed = 2;
    private final int bulletWidth = 4, bulletHeight = 4;
    public int framesToShot = 15, maxFramesToShot = framesToShot;
    public int bulletDamege = 1;

    public int life = 3;
    public int coins = 0;

    private boolean move = false;
    public boolean died = false;
    private final static int upDir = 0, rightDir = 1, downDir = 2, leftDir = 3, initDir = 4, winDir = 5, diedDir = 6, notingDir = -1;
    public int dir = 4;
    private int frames = 0, maxFrames = 5, index = 0, maxindex = 3;

    //Varible for Power-Up ====================================
    public boolean wheelActivated = false;
    public boolean shotGunActivated = false;
    private double deslocationShotGun = 0.5;
    //=========================================================

    public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
        super(x, y, width, height, speed, sprite);
    }

    public void tick() {
        depth = 2;
        //  MOVING ===============================================
        if (!died) {
            if (right) {
                if (up) {
                    // TO RIGHT AND TO UP
                    if (isFree((int) (x + (Math.sqrt(2) * speed) / 2), (int) y) && World.isFreeOfTheSpawTile((int) (x + (Math.sqrt(2) * speed) / 2), (int) y)) {
                        x += (Math.sqrt(2) * speed) / 2;
                    }
                    if (isFree((int) x, (int) (y - (Math.sqrt(2) * speed) / 2)) && World.isFreeOfTheSpawTile((int) x, (int) (y - (Math.sqrt(2) * speed) / 2))) {
                        y -= (Math.sqrt(2) * speed) / 2;
                    }
                } else if (down) {
                    // TO RIGHT AND TO DOWN
                    if (isFree((int) (x + (Math.sqrt(2) * speed) / 2), (int) y) && World.isFreeOfTheSpawTile((int) (x + (Math.sqrt(2) * speed) / 2), (int) y)) {
                        x += (Math.sqrt(2) * speed) / 2;
                    }
                    if (isFree((int) x, (int) (y + (Math.sqrt(2) * speed) / 2)) && World.isFreeOfTheSpawTile((int) x, (int) (y + (Math.sqrt(2) * speed) / 2))) {
                        y += (Math.sqrt(2) * speed) / 2;
                    }
                } else {
                    // JUST RIGHT
                    if (isFree((int) (x + speed), (int) y) && World.isFreeOfTheSpawTile((int) (x + speed), (int) y)) {
                        x += speed;
                    }
                }
                move = true;
                dir = rightDir;
            } else if (left) {
                if (up) {
                    // TO LEFT  AND TO UP
                    if (isFree((int) (x - (Math.sqrt(2) * speed) / 2), (int) y) && World.isFreeOfTheSpawTile((int) (x - (Math.sqrt(2) * speed) / 2), (int) y)) {
                        x -= (Math.sqrt(2) * speed) / 2;
                    }
                    if (isFree((int) x, (int) (y - (Math.sqrt(2) * speed) / 2)) && World.isFreeOfTheSpawTile((int) x, (int) (y - (Math.sqrt(2) * speed) / 2))) {
                        y -= (Math.sqrt(2) * speed) / 2;
                    }
                } else if (down) {
                    // TO LEFT AND TO DOWN
                    if (isFree((int) (x - (Math.sqrt(2) * speed) / 2), (int) y) && World.isFreeOfTheSpawTile((int) (x - (Math.sqrt(2) * speed) / 2), (int) y)) {
                        x -= (Math.sqrt(2) * speed) / 2;
                    }
                    if (isFree((int) x, (int) (y + (Math.sqrt(2) * speed) / 2)) && World.isFreeOfTheSpawTile((int) x, (int) (y + (Math.sqrt(2) * speed) / 2))) {
                        y += (Math.sqrt(2) * speed) / 2;
                    }
                } else {
                    // JUST LEFT
                    if (isFree((int) (x - speed), (int) y) && World.isFreeOfTheSpawTile((int) (x - speed), (int) y)) {
                        x -= speed;
                    }
                }
                move = true;
                dir = leftDir;
            } else if (up && isFree((int) x, (int) (y - speed)) && World.isFreeOfTheSpawTile((int) x, (int) (y - speed))) {
                // JUST UP
                y -= speed;
                move = true;
                dir = upDir;
            } else if (down && isFree((int) x, (int) (y + speed)) && World.isFreeOfTheSpawTile((int) x, (int) (y + speed))) {
                // JUST DOWN
                y += speed;
                move = true;
                dir = downDir;
            } else {
                move = false;
                dir = initDir;
            }
        } else {
            move = false;
        }

        // =======================================================
        // AIM AND SHOT ==========================================
        if (!died) {
            if (framesToShot == maxFramesToShot) {
                if (!wheelActivated && !shotGunActivated) {
                    if (rightAin) {
                        if (upAin) {
                            // TO RIGHT AND TO UP    
                            Bullet bullet = new Bullet(x + this.width + createBulletZone, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, ((Math.sqrt(2) * bulletSpeed) / 2), -((Math.sqrt(2) * bulletSpeed) / 2));
                            Game.bullets.add(bullet);
                        } else if (downAin) {
                            // TO RIGHT AND TO DOWN
                            Bullet bullet = new Bullet(x + this.width + createBulletZone, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, ((Math.sqrt(2) * bulletSpeed) / 2), ((Math.sqrt(2) * bulletSpeed) / 2));
                            Game.bullets.add(bullet);
                        } else {
                            // JUST RIGHT 
                            Bullet bullet = new Bullet(x + this.width + createBulletZone, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, bulletSpeed, 0);
                            Game.bullets.add(bullet);
                        }
                        dir = rightDir;
                        framesToShot = 0;
                    } else if (leftAin) {
                        if (upAin) {
                            // TO LEFT  AND TO UP
                            Bullet bullet = new Bullet(x - createBulletZone - bulletWidth, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -((Math.sqrt(2) * bulletSpeed) / 2), -((Math.sqrt(2) * bulletSpeed) / 2));
                            Game.bullets.add(bullet);
                        } else if (downAin) {
                            // TO LEFT AND TO DOWN
                            Bullet bullet = new Bullet(x - createBulletZone - bulletWidth, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -((Math.sqrt(2) * bulletSpeed) / 2), ((Math.sqrt(2) * bulletSpeed) / 2));
                            Game.bullets.add(bullet);
                        } else {
                            // JUST LEFT
                            Bullet bullet = new Bullet(x - createBulletZone - bulletWidth, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, -bulletSpeed, 0);
                            Game.bullets.add(bullet);
                        }
                        dir = leftDir;
                        framesToShot = 0;
                    } else if (upAin) {
                        // JUST UP
                        Bullet bullet = new Bullet(x + (this.width / 2), y - createBulletZone - bulletHeight, bulletWidth, bulletHeight, bulletSpeed, 0, -bulletSpeed);
                        Game.bullets.add(bullet);
                        dir = upDir;
                        framesToShot = 0;
                    } else if (downAin) {
                        // JUST DOWN
                        Bullet bullet = new Bullet(x + (this.width / 2), y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, 0, bulletSpeed);
                        Game.bullets.add(bullet);
                        dir = downDir;
                        framesToShot = 0;
                    }
                } else if (wheelActivated && !shotGunActivated) {
                    if (rightAin || upAin || leftAin || downAin) {
                        Bullet bullet;

                        // TO RIGHT AND TO UP    
                        bullet = new Bullet(x + this.width + createBulletZone, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, ((Math.sqrt(2) * bulletSpeed) / 2), -((Math.sqrt(2) * bulletSpeed) / 2));
                        Game.bullets.add(bullet);
                        // TO RIGHT AND TO DOWN
                        bullet = new Bullet(x + this.width + createBulletZone, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, ((Math.sqrt(2) * bulletSpeed) / 2), ((Math.sqrt(2) * bulletSpeed) / 2));
                        Game.bullets.add(bullet);
                        // JUST RIGHT 
                        bullet = new Bullet(x + this.width + createBulletZone, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, bulletSpeed, 0);
                        Game.bullets.add(bullet);

                        // TO LEFT  AND TO UP
                        bullet = new Bullet(x - createBulletZone - bulletWidth, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -((Math.sqrt(2) * bulletSpeed) / 2), -((Math.sqrt(2) * bulletSpeed) / 2));
                        Game.bullets.add(bullet);
                        // TO LEFT AND TO DOWN
                        bullet = new Bullet(x - createBulletZone - bulletWidth, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -((Math.sqrt(2) * bulletSpeed) / 2), ((Math.sqrt(2) * bulletSpeed) / 2));
                        Game.bullets.add(bullet);
                        // JUST LEFT
                        bullet = new Bullet(x - createBulletZone - bulletWidth, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, -bulletSpeed, 0);
                        Game.bullets.add(bullet);

                        // JUST UP
                        bullet = new Bullet(x + (this.width / 2), y - createBulletZone - bulletHeight, bulletWidth, bulletHeight, bulletSpeed, 0, -bulletSpeed);
                        Game.bullets.add(bullet);

                        // JUST DOWN
                        bullet = new Bullet(x + (this.width / 2), y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, 0, bulletSpeed);
                        Game.bullets.add(bullet);

                        if (rightAin) {
                            dir = rightDir;
                        } else if (leftAin) {
                            dir = leftDir;
                        } else if (upAin) {
                            dir = upDir;
                        } else if (downAin) {
                            dir = downDir;
                        }
                        framesToShot = 0;
                    }
                } else if (!wheelActivated && shotGunActivated) {
                    Bullet bullet;
                    if (rightAin) {
                        if (upAin) {
                            // TO RIGHT AND TO UP   
                            bullet = new Bullet(x + this.width + createBulletZone, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, ((Math.sqrt(2) * bulletSpeed) / 2), -((Math.sqrt(2) * bulletSpeed) / 2));
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x + this.width + createBulletZone, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, (((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun), -(((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun));
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x + this.width + createBulletZone, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, (((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun), -(((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun));
                            Game.bullets.add(bullet);
                        } else if (downAin) {
                            // TO RIGHT AND TO DOWN
                            bullet = new Bullet(x + this.width + createBulletZone, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, ((Math.sqrt(2) * bulletSpeed) / 2), ((Math.sqrt(2) * bulletSpeed) / 2));
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x + this.width + createBulletZone, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, (((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun), (((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun));
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x + this.width + createBulletZone, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, (((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun), (((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun));
                            Game.bullets.add(bullet);
                        } else {
                            // JUST RIGHT 
                            bullet = new Bullet(x + this.width + createBulletZone, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, bulletSpeed, 0);
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x + this.width + createBulletZone, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, bulletSpeed, -deslocationShotGun);
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x + this.width + createBulletZone, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, bulletSpeed, deslocationShotGun);
                            Game.bullets.add(bullet);
                        }
                        dir = rightDir;
                        framesToShot = 0;
                    } else if (leftAin) {
                        if (upAin) {
                            // TO LEFT  AND TO UP
                            bullet = new Bullet(x - createBulletZone - bulletWidth, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -((Math.sqrt(2) * bulletSpeed) / 2), -((Math.sqrt(2) * bulletSpeed) / 2));
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x - createBulletZone - bulletWidth, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -(((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun), -(((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun));
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x - createBulletZone - bulletWidth, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -(((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun), -(((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun));
                            Game.bullets.add(bullet);
                        } else if (downAin) {
                            // TO LEFT AND TO DOWN
                            bullet = new Bullet(x - createBulletZone - bulletWidth, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -((Math.sqrt(2) * bulletSpeed) / 2), ((Math.sqrt(2) * bulletSpeed) / 2));
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x - createBulletZone - bulletWidth, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -(((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun), (((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun));
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x - createBulletZone - bulletWidth, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -(((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun), (((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun));
                            Game.bullets.add(bullet);
                        } else {
                            // JUST LEFT
                            bullet = new Bullet(x - createBulletZone - bulletWidth, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, -bulletSpeed, 0);
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x - createBulletZone - bulletWidth, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, -bulletSpeed, +deslocationShotGun);
                            Game.bullets.add(bullet);

                            bullet = new Bullet(x - createBulletZone - bulletWidth, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, -bulletSpeed, -deslocationShotGun);
                            Game.bullets.add(bullet);
                        }
                        dir = leftDir;
                        framesToShot = 0;
                    } else if (upAin) {
                        // JUST UP
                        bullet = new Bullet(x + (this.width / 2), y - createBulletZone - bulletHeight, bulletWidth, bulletHeight, bulletSpeed, 0, -bulletSpeed);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + (this.width / 2), y - createBulletZone - bulletHeight, bulletWidth, bulletHeight, bulletSpeed, +deslocationShotGun, -bulletSpeed);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + (this.width / 2), y - createBulletZone - bulletHeight, bulletWidth, bulletHeight, bulletSpeed, -deslocationShotGun, -bulletSpeed);
                        Game.bullets.add(bullet);
                        dir = upDir;
                        framesToShot = 0;
                    } else if (downAin) {
                        // JUST DOWN
                        bullet = new Bullet(x + (this.width / 2), y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, 0, bulletSpeed);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + (this.width / 2), y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, +deslocationShotGun, bulletSpeed);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + (this.width / 2), y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -deslocationShotGun, bulletSpeed);
                        Game.bullets.add(bullet);
                        dir = downDir;
                        framesToShot = 0;
                    }
                } else if (wheelActivated && shotGunActivated) {
                    if (rightAin || upAin || leftAin || downAin) {
                        Bullet bullet;

                        // TO RIGHT AND TO UP    
                        bullet = new Bullet(x + this.width + createBulletZone, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, ((Math.sqrt(2) * bulletSpeed) / 2), -((Math.sqrt(2) * bulletSpeed) / 2));
                        Game.bullets.add(bullet);
                        // TO RIGHT AND TO DOWN
                        bullet = new Bullet(x + this.width + createBulletZone, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, ((Math.sqrt(2) * bulletSpeed) / 2), ((Math.sqrt(2) * bulletSpeed) / 2));
                        Game.bullets.add(bullet);
                        // JUST RIGHT 
                        bullet = new Bullet(x + this.width + createBulletZone, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, bulletSpeed, 0);
                        Game.bullets.add(bullet);

                        // TO LEFT  AND TO UP
                        bullet = new Bullet(x - createBulletZone - bulletWidth, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -((Math.sqrt(2) * bulletSpeed) / 2), -((Math.sqrt(2) * bulletSpeed) / 2));
                        Game.bullets.add(bullet);
                        // TO LEFT AND TO DOWN
                        bullet = new Bullet(x - createBulletZone - bulletWidth, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -((Math.sqrt(2) * bulletSpeed) / 2), ((Math.sqrt(2) * bulletSpeed) / 2));
                        Game.bullets.add(bullet);
                        // JUST LEFT
                        bullet = new Bullet(x - createBulletZone - bulletWidth, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, -bulletSpeed, 0);
                        Game.bullets.add(bullet);

                        // JUST UP
                        bullet = new Bullet(x + (this.width / 2), y - createBulletZone - bulletHeight, bulletWidth, bulletHeight, bulletSpeed, 0, -bulletSpeed);
                        Game.bullets.add(bullet);

                        // JUST DOWN
                        bullet = new Bullet(x + (this.width / 2), y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, 0, bulletSpeed);
                        Game.bullets.add(bullet);

                        // Shot for ShotGun ======================================
                        bullet = new Bullet(x + this.width + createBulletZone, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, (((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun), -(((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun));
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + this.width + createBulletZone, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, (((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun), -(((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun));
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + this.width + createBulletZone, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, (((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun), (((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun));
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + this.width + createBulletZone, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, (((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun), (((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun));
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + this.width + createBulletZone, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, bulletSpeed, -deslocationShotGun);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + this.width + createBulletZone, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, bulletSpeed, deslocationShotGun);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x - createBulletZone - bulletWidth, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -(((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun), -(((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun));
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x - createBulletZone - bulletWidth, y - createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -(((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun), -(((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun));
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x - createBulletZone - bulletWidth, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -(((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun), (((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun));
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x - createBulletZone - bulletWidth, y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -(((Math.sqrt(2) * bulletSpeed) / 2) + deslocationShotGun), (((Math.sqrt(2) * bulletSpeed) / 2) - deslocationShotGun));
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x - createBulletZone - bulletWidth, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, -bulletSpeed, +deslocationShotGun);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x - createBulletZone - bulletWidth, y + (this.height / 2), bulletWidth, bulletHeight, bulletSpeed, -bulletSpeed, -deslocationShotGun);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + (this.width / 2), y - createBulletZone - bulletHeight, bulletWidth, bulletHeight, bulletSpeed, +deslocationShotGun, -bulletSpeed);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + (this.width / 2), y - createBulletZone - bulletHeight, bulletWidth, bulletHeight, bulletSpeed, -deslocationShotGun, -bulletSpeed);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + (this.width / 2), y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, +deslocationShotGun, bulletSpeed);
                        Game.bullets.add(bullet);

                        bullet = new Bullet(x + (this.width / 2), y + this.height + createBulletZone, bulletWidth, bulletHeight, bulletSpeed, -deslocationShotGun, bulletSpeed);
                        Game.bullets.add(bullet);
                        // =======================================================

                        if (rightAin) {
                            dir = rightDir;
                        } else if (leftAin) {
                            dir = leftDir;
                        } else if (upAin) {
                            dir = upDir;
                        } else if (downAin) {
                            dir = downDir;
                        }
                        framesToShot = 0;
                    }
                }
            } else if (rightAin || leftAin || upAin || downAin) {
                if (rightAin) {
                    dir = rightDir;
                } else if (leftAin) {
                    dir = leftDir;
                } else if (upAin) {
                    dir = upDir;
                } else if (downAin) {
                    dir = downDir;
                }
                framesToShot++;
            } else {
                framesToShot++;
            }
        }
        // =======================================================

        // Animation =============================================
        // Legs
        if (move) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if (index > maxindex) {
                    index = 0;
                }
            }
        }
        // If player died, wait one moment ============
        if (died) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if (index == 4) {
                    dir = notingDir;
                }
            } else if (index == 30) {
                restartPlayer();
                Sound.mOverWorld.resumeLoop();
            }
        }
        //=============================================
        // =======================================================

        // DamageToHim ===========================================
        for (int i = 0; i < Game.enemys.size(); i++) {
            if (isColidding(this, Game.enemys.get(i))) {
                life--;
                for (int j = 0; j < Game.enemys.size(); j++) {
                    Game.entities.remove(Game.enemys.get(j));
                }
                Game.windowsPowerUp.resetListPowerUpUsing();
                Game.enemys.clear();
                Game.bullets.clear();
                Game.control.setEnemysToGenerate(0);
                Game.control.setTimer(0);
                Game.control.addTimeGame(0.15); //AddTime
                Sound.mOverWorld.pausedLoop();
                Sound.mHurt.play();
                frames = 0;
                index = 0;
                dir = diedDir;
                died = true;
            }
        }
        // =======================================================

        // Get items ==============================================
        for (int i = 0; i < Game.entities.size(); i++) {
            if (Game.entities.get(i) instanceof Coins) { // Get Coin
                Coins coin = (Coins) Game.entities.get(i);
                if (isColidding(this, coin)) {
                    coins += coin.getValue();
                    Game.entities.remove(coin);
                    Sound.mCoin.play();
                }
            } else if (Game.entities.get(i) instanceof PowerUp) { // Get PowerUp
                PowerUp pw = (PowerUp) Game.entities.get(i);
                if (isColidding(this, pw)) {
                    // Stored Item in Windows Power-Up ==========================
                    if (Game.windowsPowerUp.powerUpStored == null) {
                        Game.windowsPowerUp.powerUpStored = pw;
                        Game.entities.remove(pw);
                    } else {
                        pw.initializePowerUp();
                        Game.entities.remove(pw);
                    }
                    //===========================================================
                }
            } else if (Game.entities.get(i) instanceof Life) {
                Life life = (Life) Game.entities.get(i);
                if (isColidding(this, life)) {
                    this.life += life.getValue();
                    Game.entities.remove(life);
                }
            }
        }
        // =======================================================

        // Game Over =============================================
        if (life < 0 && !died) {

            World.restartGame("");
            Sound.mOverWorld.stopLoop();
            Game.GameState = Game.GameStatePossible[2];

        }
        // =======================================================
    }

    private void restartPlayer() {
        index = 0;
        frames = 0;
        Game.ui.intitGame = false;
        died = false;
        this.x = (16 * 7)+8;
        this.y = (16 * 7)+8;
        dir = initDir;
    }

    public void render(Graphics g) {

        if (dir == upDir) {
            drawLeg(g, 13);
            g.drawImage(PLAYER_MOVE[0], this.getX(), this.getY(), null);
        } else if (dir == rightDir) {
            drawLeg(g, 13);
            g.drawImage(PLAYER_MOVE[1], this.getX(), this.getY(), null);
        } else if (dir == downDir) {
            drawLeg(g, 13);
            g.drawImage(PLAYER_MOVE[2], this.getX(), this.getY(), null);
        } else if (dir == leftDir) {
            drawLeg(g, 13);
            g.drawImage(PLAYER_MOVE[3], this.getX(), this.getY(), null);
        } else if (dir == initDir) {
            g.drawImage(PLAYER_INIT, this.getX(), this.getY(), null);
        } else if (dir == winDir) {
            g.drawImage(PLAYER_WIN, this.getX(), this.getY(), null);
        } else if (dir == diedDir) {
            g.drawImage(PLATER_DYING[index], this.getX(), this.getY(), null);
        }
    }

    private void drawLeg(Graphics g, int displacement) {
        g.drawImage(PLAYER_LEGS[index], this.getX(), this.getY() + displacement, null);
    }

}
