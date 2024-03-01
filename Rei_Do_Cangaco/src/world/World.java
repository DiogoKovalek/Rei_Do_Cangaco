/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world;


import entities.Bullet;
import entities.GreenMan;
import entities.Entity;
import entities.Player;
import entities.WindowsPowerUp;
import graficos.UI;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import main.Controller;
import main.Game;


/**
 *
 * @author USUARIO
 */
public class World {
    
    public static Tile[] tiles;
    public static int WIDTH, HEIGHT;
    public static final int TILE_SIZE = 16;
    
    private static List<Tile> tilesAnimation;
    
    public static String levelNum;
    public static String worldNum;
    
    public World(String world_Num, String level_Num){
        String path = "/res/map_W"+world_Num+"_L"+level_Num+".png";
        levelNum = level_Num;
        worldNum = world_Num;
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            int[] pixels = new int[map.getWidth()*map.getHeight()];
            WIDTH = map.getWidth();
            HEIGHT = map.getHeight();
            tiles = new Tile[map.getWidth()*map.getHeight()];
            //Comando para pegar a posicao de todos os pixels e colocar na list pixels
            map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
            
            tilesAnimation = new ArrayList<Tile>();
            
            for(int xx = 0; xx < map.getWidth(); xx++){
                for(int yy = 0; yy < map.getHeight(); yy++){
                    int pixelAtual = pixels[xx + (yy*map.getWidth())];
                    
                    //Instanciando
                    
                    tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16, yy*16, FloorTile.Tile_Floor_Sand1[Tile.getWorldForDraw()], false); //Sempre vai ter chao
                    
                    //Instanciar Elementos do Mapa ===================================
                    if(pixelAtual == 0xFF000000){
                        //Tree
                        tiles[xx + (yy * WIDTH)] = new TreeTile(xx*16, yy*16, TreeTile.Tile_Tree[Tile.getWorldForDraw()][0], true);
                        tilesAnimation.add(tiles[xx + (yy*WIDTH)]);
                    }else if(pixelAtual == 0xFFFFFFFF){
                        //Floor
                        tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16, yy*16, FloorTile.Tile_Floor_Sand1[Tile.getWorldForDraw()], false);
                    }else if(pixelAtual == 0xFFac3232){
                        //Player
                        Game.player.setX((xx*16)+8);
                        Game.player.setY((yy*16)+8);
                    }else if(pixelAtual == 0xFF490000){
                        tiles[xx+ (yy*WIDTH)] = new SpawTile(xx*16, yy*16, SpawTile.Tile_Floor_Rock1[Tile.getWorldForDraw()], false);
                    }else if(pixelAtual == 0xFFa93d3d){
                        tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16, FloorTile.Tile_Floor_Rock3[Tile.getWorldForDraw()], false);
                    }else if(pixelAtual == 0xFFb6b6b6){
                        tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16, FloorTile.Tile_Floor_Sand2[Tile.getWorldForDraw()], false);
                    }else if(pixelAtual == 0xFF001749){
                        GreenMan enemy = new GreenMan(xx*16, yy*16, 16, 16, 0.5, null);
                        Game.entities.add(enemy);
                        Game.enemys.add(enemy);
                    }else if(pixelAtual == 0xFF232323){
                        tiles[xx + (yy*WIDTH)] = new WallTile(xx*16, yy*16, WallTile.Tile_Wall[Tile.getWorldForDraw()], true);
                    }
                    //================================================================
                    
//             EX:    if(pixelAtual == 0xFFFF0000){
//                        //arvore
//                        tiles[xx + (yy * WIDTH)] = new TreeTile(xx*16, yy*16, Tile.Tile_Tree);
//                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static boolean isFree(int xNext, int yNext){
        int x1 = xNext / TILE_SIZE;
        int y1 = yNext / TILE_SIZE;
        
        int x2 = (xNext+TILE_SIZE-1) / TILE_SIZE;
        int y2 = yNext/TILE_SIZE;
        
        int x3 = xNext/TILE_SIZE;
        int y3 = (yNext+TILE_SIZE-1) / TILE_SIZE;
        
        int x4 = (xNext+TILE_SIZE-1) / TILE_SIZE;
        int y4 = (yNext+TILE_SIZE-1) / TILE_SIZE;
        
        return !((tiles[x1 + (y1*World.WIDTH)].isCollision) ||
                (tiles[x2 + (y2*World.WIDTH)].isCollision) ||
                (tiles[x3 + (y3*World.WIDTH)].isCollision) ||
                (tiles[x4 + (y4*World.WIDTH)].isCollision));
               
    }
    
    public static boolean colisionWallTile(Entity e){
        Rectangle eMask = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
        for(int i = 0; i < tiles.length; i++){
            if(tiles[i] instanceof WallTile){
                Tile t = tiles[i];
                Rectangle wMask = new Rectangle(t.getX(), t.getY(), TILE_SIZE, TILE_SIZE);
                if(eMask.intersects(wMask)){
                    return true;
                }
            }else{
                continue;
            }
        }
        return false;    
    }
    
    public static boolean isFreeOfTheSpawTile(int xNext, int yNext){
        int x1 = xNext / TILE_SIZE;
        int y1 = yNext / TILE_SIZE;
        
        int x2 = (xNext+TILE_SIZE-1) / TILE_SIZE;
        int y2 = yNext/TILE_SIZE;
        
        int x3 = xNext/TILE_SIZE;
        int y3 = (yNext+TILE_SIZE-1) / TILE_SIZE;
        
        int x4 = (xNext+TILE_SIZE-1) / TILE_SIZE;
        int y4 = (yNext+TILE_SIZE-1) / TILE_SIZE;
        
        return !((tiles[x1 + (y1*World.WIDTH)] instanceof SpawTile) ||
                (tiles[x2 + (y2*World.WIDTH)] instanceof SpawTile) ||
                (tiles[x3 + (y3*World.WIDTH)] instanceof SpawTile) ||
                (tiles[x4 + (y4*World.WIDTH)] instanceof SpawTile));
    }
    
    public static void restartGame(String level){
        Game.entities = new ArrayList<Entity>();
        Game.enemys = new ArrayList<Entity>();
        Game.bullets = new ArrayList<Bullet>();
        Game.control = new Controller();
        Game.ui = new UI();
        Game.windowsPowerUp = new WindowsPowerUp();
        Game.player = new Player(0, 0, 16, 16, 1.5, null);
        Game.entities.add(Game.player);
        
        Game.world = new World("01", "01");
    }
    
    public static void loadNextLevel() {
        String newLevelNum = "";
        String newWorldNum = worldNum;

        switch (levelNum) {
            case "01":
                newLevelNum = "02";
                break;
            case "02":
                newLevelNum = "03";
                break;
            case "03":
                newLevelNum = "04";
                break;
            case "04":
                switch (worldNum) {
                    case "01":
                        newWorldNum = "02";
                        break;
                    case "02":
                        newWorldNum = "03";
                        break;
                    case "03":
                    //Credt Screen
                }
                newLevelNum = "01"; // return to fist level
                break;
            default:
                throw new AssertionError();
        }
        Game.world = new World(newWorldNum, newLevelNum);
    }
    
    public void tick(){
        for(int i = 0; i < tilesAnimation.size(); i++){
            tilesAnimation.get(i).tick();
        }
    }
    
    public void render(Graphics g){
        
        int xStart = Camera.x >> 4;// Sistema para renderizar apenas o que aparece
        int yStart = Camera.y >> 4; // E a mesma coisa que Camera.y/16
        
        
        int xFinal = xStart + (Game.WIDTH >> 4);
        int yFinal = yStart + (Game.HEIGHT >> 4);
        
        for(int xx = xStart; xx <= xFinal; xx++){
            for(int yy = yStart; yy <= yFinal; yy++){
                try {
                    Tile tile = tiles[xx + (yy*WIDTH)]; 
                    tile.render(g);
                } catch (Exception e) {
                }
                
            }
        }
//        int xStart = Camera.x / 16;
//        int yStart = Camera.y / 16; 
//        
//        
//        int xFinal = xStart + (Game.WIDTH / 16);
//        int yFinal = yStart + (Game.HEIGHT / 16);
//        
//        for(int xx = xStart; xx <= xFinal; xx++){
//            for(int yy = yStart; yy <= yFinal; yy++){
//                try {
//                    Tile tile = tiles[xx + (yy*WIDTH)]; 
//                    tile.render(g);
//                } catch (Exception e) {
//                }
//                
//            }
//        }
//    }
    }
    
}
