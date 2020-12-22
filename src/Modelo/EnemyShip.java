/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jarod
 */
public class EnemyShip extends Ship {

    private boolean alive;

    public EnemyShip(int[] pos) {
        super(pos);

        this.alive = true;
        buildBody();
    }

    protected void buildBody() {
        this.body = new ColorType[8][8];
        /*for (int i = 0; i < body.length; i++) {
            for (int j = 0; j < body[i].length; j++) {
                body[i][j] = ColorType.NEGRO;
            }
        }*/
        Random rand = new Random();
        int n = rand.nextInt(4);
        
        ColorType primero = ColorType.AMARILLO;
        ColorType segundo = ColorType.VERDE;
        ColorType tercero = ColorType.VERDEAGUA;
        switch (n) {
            case 0:
                primero = ColorType.AMARILLO;
                segundo = ColorType.VERDE;
                tercero = ColorType.VERDEAGUA;
                break;
            case 1:
                primero = ColorType.MORADO;
                segundo = ColorType.ROSADO;
                tercero = ColorType.FUCSIA;
                break;
            case 2:
                primero = ColorType.CELESTE;
                segundo = ColorType.AZULCLARO;
                tercero = ColorType.AZUL;
                break;
            case 3:
                primero = ColorType.ROJO;
                segundo = ColorType.VINO;
                tercero = ColorType.MORADO;
                break;
        }
        body[1][2] = body[1][5]
                = body[5][3] = body[5][4]
                = body[7][0] = body[7][7] = primero;
        body[3][0] = body[3][7]
                = body[4][3] = body[4][4]
                = body[6][1] = body[6][6] = segundo;
        body[2][2] = body[2][5]
                = body[3][3] = body[3][4]
                = body[4][0] = body[4][7]
                = body[4][2] = body[4][5]
                = body[5][0] = body[5][7]
                = body[5][2] = body[5][5]
                = body[6][0] = body[6][7] = tercero;

    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

}
