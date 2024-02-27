/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entities.Butterfly;
import entities.GreenMan;
import entities.Horn;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import world.SpawTile;
import world.World;

/**
 *
 * @author diogo
 */
public class Controller {

    private static Random rand = new Random();

    private int minTimeForGenerator = 4, maxTimeForGenerator = 9;
    private int minToGenerated = 4, maxToGenerated = 20;
    private int timer = 0, maxTimer = rand.nextInt(60 * minTimeForGenerator, 60 * maxTimeForGenerator);

    private int enemysToGenerate = 0;
    private static List<SpawTile> spaw;

    
    public int time = 60*0, maxTime = 60*30;
    
    public Controller() {
        spaw = new ArrayList<SpawTile>();
    }

    public void EnemyGenerator() {
        if (spaw.size() == 0) {
            for (int i = 0; i < World.tiles.length; i++) {
                if (World.tiles[i] instanceof SpawTile) {
                    spaw.add((SpawTile) World.tiles[i]);
                }
            }
        }

        if (!Game.player.died && Game.ui.intitGame) {
            timer++;
            if (timer == maxTimer) {
                timer = 0;
                maxTimer = rand.nextInt(60 * minTimeForGenerator, 60 * maxTimeForGenerator);
                enemysToGenerate += rand.nextInt(minToGenerated, maxToGenerated);
            }

            if (enemysToGenerate > 0) {
                int pos = rand.nextInt(spaw.size());
                if (spaw.get(pos).isFree()) {
                    SpawTile sp = spaw.get(pos);
                    int factorEne = rand.nextInt(110);
                    if(factorEne > 90 && factorEne <= 100){
                        // Horn
                        Horn ene = new Horn(sp.getX(), sp.getY(), 16,16,0.5, null);
                        Game.entities.add(ene);
                        Game.enemys.add(ene);
                    }else if(factorEne <= 90){
                        // Green Man
                        GreenMan ene = new GreenMan(sp.getX(), sp.getY(), 16, 16, 0.5, null);
                        Game.entities.add(ene);
                        Game.enemys.add(ene);
                    }else if(factorEne > 100){
                        Butterfly ene = new Butterfly(sp.getX(), sp.getY(), 16, 16, 0.5, null);
                        Game.entities.add(ene);
                        Game.enemys.add(ene);
                    }
                    
                    enemysToGenerate--;
                }
            }
        }

    }
    
    public void TimeGame(){
        
    }

    public void setEnemysToGenerate(int enemysToGenerate) {
        this.enemysToGenerate = enemysToGenerate;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

}
