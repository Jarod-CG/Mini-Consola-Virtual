/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Random;

/**
 *
 * @author Jarod
 */
public class PacMan {
    protected ColorType[][] body;
    protected int[] pos;

    public PacMan(int[] pos) {
        this.pos = pos;
         buildBody();
    }
    
    private void buildBody(){
        this.body = new ColorType[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!((i==0 || i==4) && (j==0 || j==4))){
                    this.body[i][j] = ColorType.AMARILLO;
                }
            }
        }
    }

    public ColorType[][] getBody() {
        return body;
    }

    public void setBody(ColorType[][] body) {
        this.body = body;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
    
    public void moverArriba(){
        if (pos[1]>1){ 
            pos[1]--;
        }
    }
    public void moverAbajo(){
        if (pos[1]+5<48){
            pos[1]++;
        }
    }
    public void moverDerecha(){
        if (pos[0]+5<48){
            pos[0]++;
        }
    }
    public void moverIzquierda(){
        if (pos[0]>1){
            pos[0]--;
        }
    }
    
    
}
