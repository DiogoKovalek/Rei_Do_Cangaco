/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AStar;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;
import world.Tile;
import world.TreeTile;
import world.World;

/**
 *
 * @author diogo
 */
public class AStar {
    
    public static double lastTime = System.currentTimeMillis();
    private static Comparator<Node> nodeSorter = new Comparator<Node>(){ // Node serve para guardar informacoes
        
        @Override
        public int compare(Node n0, Node n1){
            if(n1.fCost < n0.fCost)
                return +1;
            if(n1.fCost > n0.fCost)
                return -1;
            return 0;
        }
    }; 
    
    public static boolean clear(){
        if(System.currentTimeMillis() - lastTime >= 1000){
            return true;
        }
        return false;
    }
    
    public static List<Node> findPath(World world, Vector2i start, Vector2i end){ // Acha os caminhos
        lastTime = System.currentTimeMillis();
        List<Node> openList = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();
        
        Node current = new Node(start,null,0,getDistance(start, end));
        openList.add(current);
        while(openList.size() > 0){
            Collections.sort(openList, nodeSorter);
            current = openList.get(0);
            
            if(current.tile.equals(end)){ //chegou ao final do destino
                //Chegamos no ponto final
                //Basta retornar o valor
                List<Node> path = new ArrayList<Node>();
                while(current.parent != null){
                    path.add(current);
                    current = current.parent;
                }
                openList.clear();
                closedList.clear();
                return path;
            }
            
            openList.remove(current);
            closedList.add(current);
            
            for(int i = 0; i < 9; i++){ // vai verificar se os lados estao lives
                if(i==4) continue; // posicao 4 e o player, entao nao leva em conta
                int x = current.tile.x;
                int y = current.tile.y;
                int xi = (i%3) - 1;
                int yi = (i/3) - 1;
                Tile tile;
                try {
                    tile = world.tiles[x + xi + ((y+yi)*world.WIDTH)];
                } catch (Exception e) {
                    continue;
                }
                if(tile == null) continue;
                if(tile.isCollision()) continue;
                if(i==0){
                    Tile test = world.tiles[x+xi+1+((y+yi)*world.WIDTH)]; // Existem esses dois testes para possibilitar andar nas diagonais
                    Tile test2 = world.tiles[x+xi+((y+yi+1)*world.WIDTH)]; 
                    if(test.isCollision() || test2.isCollision()){
                        continue;
                    }
                }else if(i==2){
                    Tile test = world.tiles[x+xi-1+((y+yi)*world.WIDTH)]; // Existem esses dois testes para possibilitar andar nas diagonais
                    Tile test2 = world.tiles[x+xi+((y+yi+1)*world.WIDTH)]; 
                    if(test.isCollision() || test2.isCollision()){
                        continue;
                    }
                }else if(i==6){
                    Tile test = world.tiles[x+xi+((y+yi-1)*world.WIDTH)]; // Existem esses dois testes para possibilitar andar nas diagonais
                    Tile test2 = world.tiles[x+xi+1+((y+yi)*world.WIDTH)]; 
                    if(test.isCollision() || test2.isCollision()){
                        continue;
                    }
                }else if(i==8){
                    Tile test = world.tiles[x+xi+((y+yi-1)*world.WIDTH)]; // Existem esses dois testes para possibilitar andar nas diagonais
                    Tile test2 = world.tiles[x+xi-1+((y+yi)*world.WIDTH)]; 
                    if(test.isCollision() || test2.isCollision()){
                        continue;
                    }
                }
                
                Vector2i a = new Vector2i(x+xi, y+yi);
                double gCost = current.gCost + getDistance(current.tile, a); // Calcula o tempo que vai demorar o trajeto
                double hCost = getDistance(a, end);
                
                Node node = new Node(a,current,gCost,hCost);
                
                if(vecInList(closedList, a) && gCost >=current.gCost) continue;
                
                if(!vecInList(openList, a)){
                    openList.add(node);
                }else if(gCost < current.gCost){
                    openList.remove(current);
                    openList.add(node);
                }    
            }
        }
        closedList.clear();
        System.out.println("Nao encontrou");
        return null;
    }
    
    public static boolean vecInList(List<Node> list, Vector2i vector){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).tile.equals(vector)){
                return true;
            }
        }
        return false;
    }
    
    private static double getDistance(Vector2i tile, Vector2i goal){
        double dx = tile.x - goal.x;
        double dy = tile.y - goal.y;
        
        return Math.sqrt(dx*dx + dy*dy); 
    }
}
