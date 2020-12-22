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
public class MyShip extends Ship{

    public MyShip(int[] pos) {
        super(pos);
        buildBody();
    }
    
    

    @Override
    protected void buildBody() {
        this.body = new ColorType[7][7];
        /*for (int i = 0; i < body.length; i++) {
            for (int j = 0; j < body[i].length; j++) {
                body[i][j] = ColorType.NEGRO;
            }
        }*/
        Random rand = new Random();
        int n = rand.nextInt(4);
        n= 0;
        switch (n){
            case 0:
                body[2][3] = body[3][0] =
                body[3][3] = body[3][6] = ColorType.NARANJA;
                body[3][2] = body[3][4] =
                body[4][2] = body[4][3] =
                body[4][4] = body[6][0] = 
                body[6][6] = ColorType.ROJO;
                body[0][3] = body[1][3] =
                body[4][0] = body[4][1] =
                body[4][5] = body[4][6] =
                body[5][0] = body[5][1] =
                body[5][5] = body[5][6] = ColorType.VINO;
                        
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public void mover(String str) {
        if (str.equals(JSON.ARRIBA.getStr())){
            moverArriba();
        }
        else if (str.equals(JSON.ABAJO.getStr())){
            moverAbajo();
        }
        else if (str.equals(JSON.DERECHA.getStr())){
            moverDerecha();
        }
        else if (str.equals(JSON.IZQUIERDA.getStr())){
            moverIzquierda();
        }
        else if (str.equals(JSON.ACCION.getStr())){
            
        }
    }
    
    private void moverArriba(){
        if (pos[1]>1){
            pos[1]--;
        }
    }
    private void moverAbajo(){
        if (pos[1]+7<49){
            pos[1]++;
        }
    }
    private void moverDerecha(){
        if (pos[0]+7<49){
            pos[0]++;
        }
    }
    private void moverIzquierda(){
        if (pos[0]>1){
            pos[0]--;
        }
    }
    
    
}
