/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;

/**
 *
 * @author Jarod
 */
public enum ColorType {
    NEGRO(22,23,26,0)
    ,VINO(126,6,34,1)
    ,ROJO(214,36,17,2)
    ,NARANJA(255,132,38,3)
    ,AMARILLO(255,209,0,4)
    ,BLANCO(250,253,255,5)
    ,ROSADO(255,128,164,6)
    ,FUCSIA(255,38,116,7)
    ,MORADO(148,33,106,8)
    ,MORADOOSCURO(67,0,103,9)
    ,AZULCLARO(35,73,117,10)
    ,CELESTE(104,174,212,11)
    ,VERDE(191,255,60,12)
    ,VERDEAGUA(16,210,117,13)
    ,CELESTEOSCURO(0,120,153,14)
    ,AZUL(0,40,89,15)
    ;
    
    
    
    private int R;
    private int G;
    private int B;
    private int num;

    private ColorType(int R, int G, int B, int num) {
        this.R = R;
        this.G = G;
        this.B = B;
        this.num = num;
    }


    public int getR() {
        return R;
    }

    public int getG() {
        return G;
    }

    public int getB() {
        return B;
    }

    public int getNum() {
        return num;
    }

    

    
     
}
