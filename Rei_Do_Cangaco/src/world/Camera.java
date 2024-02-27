/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world;

/**
 *
 * @author diogo
 */
public class Camera {
    
    public static int x;
    public static int y;
    
    public static int clamp(int atual, int min, int max){
        if(atual < min){
            atual = min;
        }if(atual > max){
            atual = max;
        }
        return atual;
    }
}
