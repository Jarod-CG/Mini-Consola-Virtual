/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jarod
 */
public class SpaceInvader extends Consola {

    private ColorType[][] tablero;
    private ArrayList<EnemyShip> enemigos;
    private MyShip nave;

    public SpaceInvader(int dim) {
        tablero = new ColorType[dim][dim];
        enemigos = new ArrayList();
        int[] n = {(tablero.length / 2 - 3), (tablero.length - 8)};
        nave = new MyShip(n);

        loadTablero();
        createEnemies();
        enviarMatriz(this.tablero, new int[2]);
        enviarMatriz(nave.getBody(), nave.getPos());
        posicionaEnemigos();
    }

    private void posicionaEnemigos() {
        for (int i = 0; i < enemigos.size(); i++) {
            if (enemigos.get(i).isAlive()) {
                addTablero(enemigos.get(i).getBody(), enemigos.get(i).getPos());
                enviarMatriz(enemigos.get(i).getBody(), enemigos.get(i).getPos());
            }
        }
    }

    private void addTablero(ColorType[][] tablero, int[] offset) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j]!=null){
                    this.tablero[i + offset[1]][j + offset[0]] = tablero[i][j];
                }
                
            }
        }
    }

    private void loadTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = ColorType.NEGRO;
            }
        }
    }

    private void createEnemies() {

        for (int i = 0; i < tablero.length / 10 - 1; i++) {//8 el espacio ocupado por nave
            for (int j = 0; j < tablero.length / 10 - 1 * (i % 2); j++) {
                /*1       11
                6       11 + 5 
                1*/
                int[] n = {1 + 10 * j + 5 * (i % 2), 1 + 10 * i};
                enemigos.add(new EnemyShip(n));
            }
        }

    }

    private void enviarMatriz(ColorType[][] tablero, int[] offset) {
        String str = "";

        for (int i = 0; i < tablero.length; i++) {
            JSONObject json = new JSONObject();
            JSONArray arrPixeles = new JSONArray();
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null) {
                    JSONObject pixel = new JSONObject();
                    JSONObject punto = new JSONObject();
                    JSONObject color = new JSONObject();
                    punto.put("x", j + offset[0]);
                    punto.put("y", i + offset[1]);
                    color.put("R", tablero[i][j].getR());
                    color.put("G", tablero[i][j].getG());
                    color.put("B", tablero[i][j].getB());
                    pixel.put("pos", punto);

                    pixel.put("color", color);
                    arrPixeles.put(pixel);
                }

            }
            json.put(JSON.PIXELES.getStr(), arrPixeles);

            str = json.toString();

            enviarMensaje(str);

        }

    }

    @Override
    public void notifyObserver(String mensaje) {
        actuar(mensaje);
    }

    private void actuar(String mensaje) {
        
        JSONObject json = new JSONObject(mensaje);
        //borrar disparos
        
        if (json.get(JSON.BOTON.getStr()).equals(JSON.ACCION.getStr())){
            nave.disparar();
           
        }
        else{
            despinto();
            nave.mover((String) json.get(JSON.BOTON.getStr()));
            enviarMatriz(nave.getBody(), nave.getPos());
        }
        despintarDisparos();
        nave.moverDisparos();
        pintarDisparo();
        
        detectarEnemigo();
        //poner disparos
        
    }

    private void despinto() {
        int n = nave.getBody().length;
        ColorType[][] m = new ColorType[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nave.getBody()[i][j] != null){
                    m[i][j] = tablero[i+nave.getPos()[1]][j+nave.getPos()[0]];
                }
            }
        }
        enviarMatriz(m, nave.getPos());
    }
    
    //los disparos si tiene valores correctos en su posiciones
    private void despintarDisparos(){
        int n = nave.getDisparos().size();
        ColorType[][] m = new ColorType[tablero.length][tablero.length];
        for (int[] disparo: nave.getDisparos()) {
            m[disparo[1]][disparo[0]] = this.tablero[disparo[1]][disparo[0]];
        }
        enviarMatriz(m, new int[2]);
    }
    
    private void pintarDisparo(){
        ColorType[][] m = new ColorType[tablero.length][tablero.length];
        for (int[] disparo: nave.getDisparos()) {
            m[disparo[1]][disparo[0]] = ColorType.BLANCO;
        }
        enviarMatriz(m, new int[2]);
    }
    
    
    private void detectarEnemigo(){
        //por cada bala preguntar si esta sobre un color que no sea negro
        ArrayList<int[]> remover = new ArrayList();
        for (int[] disparo: nave.getDisparos()) {
            if (!this.tablero[disparo[1]][disparo[0]].equals(ColorType.NEGRO)){
                //detectar que enemigo es 
                destruirEnemigos(disparo);
                remover.add(disparo);
            }
        }
        
        for (int i = 0; i < remover.size(); i++) {
            nave.destruyo(remover.get(i));
        }
    }
    
    private void destruirEnemigos(int[] disparo){
        for(EnemyShip ship : enemigos){
            if (ship.isIn(disparo)){
                ship.setAlive(false);
                eliminarNave(ship);
            }
        }
    }
    
    private void eliminarNave(EnemyShip ship){
        int[] pos = ship.getPos();
        ColorType[][] m = new ColorType[tablero.length][tablero.length];
        for (int i = 0; i < ship.getBody().length; i++) {
            for (int j = 0; j < ship.getBody()[i].length; j++) {
                this.tablero[i+pos[1]][j+pos[0]] = ColorType.NEGRO;
                m[i+pos[1]][j+pos[0]] = this.tablero[i+pos[1]][j+pos[0]];
            }
        }
        enviarMatriz(m, new int[2]);
    }

}
