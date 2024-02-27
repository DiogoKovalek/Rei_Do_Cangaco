/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AStar;

/**
 *
 * @author diogo
 */
public class Node { // Node serve para guardar informacoes
    
    public Vector2i tile;
    public Node parent;
    public double fCost, gCost, hCost;
    
    public Node(Vector2i tile, Node parent, double gCost, double hCost){
        this.tile = tile;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = gCost + hCost;
    }
}
