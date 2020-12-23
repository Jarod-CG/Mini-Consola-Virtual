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
public class PacManGame extends Consola {

    private ColorType[][] tablero;
    private int dim;
    private PacMan pacman;
    private ArrayList<int[]> bolitas;

    private int[][] matrizTablero;

    public PacManGame(int dim) {
        this.dim = dim;
        matrizTablero = new int[dim][dim];
        tablero = new ColorType[dim][dim];
        bolitas = new ArrayList();
        int[] n = {1, 1};
        pacman = new PacMan(n);
        loadTablero();
        loadBolitas();
        copiar();
        enviarTablero();
        pintarBolitas();
        pintarPacMan();

    }

    private void loadBolitas() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if ((i + 4) % 3 == 1 && (j + 4) % 3 == 1) {
                    if (!tablero[i][j].equals(ColorType.NEGRO)) {
                        int[] n = {j, i};
                        bolitas.add(n);
                    }
                }
            }
        }
    }

    private void pintarPacMan() {
        enviarMatriz(pacman.getBody(), pacman.getPos());
    }

    private void depintarPacMan() {
        ColorType[][] mClr = new ColorType[5][5];
        int[] pos = pacman.getPos();
        for (int i = 0; i < mClr.length; i++) {
            for (int j = 0; j < mClr[i].length; j++) {
                mClr[i][j] = getColor(matrizTablero[i + pos[1]][j + pos[0]]);
            }
        }

        enviarMatriz(mClr, pacman.getPos());
    }

    private void copiar() {

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tablero[i][j].equals(ColorType.NEGRO)) {
                    matrizTablero[i][j] = ColorType.NEGRO.getNum();
                } else {
                    matrizTablero[i][j] = ColorType.CELESTE.getNum();
                }
            }
        }

    }

    private void imprimir() {
        ColorType[][] t = new ColorType[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tablero[i][j].equals(ColorType.NEGRO)) {
                    System.out.print("X");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }

    private void loadTablero() {
        int contador = 0;
        for (int i = 0; i < dim / 2 + 1; i++) {
            for (int j = 0; j < dim / 2 + 1; j++) {
                if (i == 0 || j == 0) {
                    tablero[i][j] = ColorType.NEGRO;
                } else if (i == 6 && (j >= 6 && j < 19)) {
                    tablero[i][j] = ColorType.NEGRO;
                } else if (j == 6 && (i > 6 && i < 13)) {
                    tablero[i][j] = ColorType.NEGRO;
                } else if (j == 18 && (i > 6 && i < 19)) {
                    tablero[i][j] = ColorType.NEGRO;
                } else if (i == 18 && j <= 6) {
                    tablero[i][j] = ColorType.NEGRO;
                } else if (j == 12 && i >= 12) {
                    tablero[i][j] = ColorType.NEGRO;
                } else if (i == dim / 2 - 1 && (j > 5 && j < 19)) {
                    tablero[i][j] = ColorType.NEGRO;
                } else if (i == 12 && j > 18) {
                    tablero[i][j] = ColorType.NEGRO;
                } else if ((i <= 6 || i >= 18) && j == 24) {
                    tablero[i][j] = ColorType.NEGRO;
                } else {
                    tablero[i][j] = ColorType.AMARILLO;
                }
            }
        }
        //System.out.println("hay  : " + contador + " bolitas");
        invertir();
        invertir2();
        ajustar();
        ajustar2();
    }

    private void rotate() {
        ColorType[][] t = new ColorType[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tablero[i][j] != null) {
                    t[j][dim - i - 1] = tablero[i][j];
                }
            }
        }
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (t[i][j] != null) {
                    tablero[i][j] = t[i][j];
                }
            }
        }
        //tablero = t;
    }

    private void ajustar() {
        for (int i = 0; i < dim; i++) {
            for (int j = dim / 2; j < dim; j++) {
                tablero[i][j - 1] = tablero[i][j];
            }
        }
    }

    private void ajustar2() {
        for (int i = dim / 2; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                tablero[i - 1][j] = tablero[i][j];
            }
        }
    }

    private void invertir() {
        ColorType[][] t = new ColorType[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tablero[i][j] != null) {
                    t[i][dim - j - 1] = tablero[i][j];
                }
            }
        }
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (t[i][j] != null) {
                    tablero[i][j] = t[i][j];
                }
            }
        }

    }

    private void invertir2() {
        ColorType[][] t = new ColorType[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tablero[i][j] != null) {
                    t[dim - i - 1][j] = tablero[i][j];
                }
            }
        }
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (t[i][j] != null) {
                    tablero[i][j] = t[i][j];
                }
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

    private void enviarTablero() {
        String str = "";

        for (int i = 0; i < matrizTablero.length; i++) {
            JSONObject json = new JSONObject();
            JSONArray arrPixeles = new JSONArray();
            for (int j = 0; j < matrizTablero[i].length; j++) {

                JSONObject pixel = new JSONObject();
                JSONObject punto = new JSONObject();
                JSONObject color = new JSONObject();
                punto.put("x", j);
                punto.put("y", i);
                ColorType clr = getColor((int) matrizTablero[i][j]);
                color.put("R", clr.getR());
                color.put("G", clr.getG());
                color.put("B", clr.getB());
                pixel.put("pos", punto);

                pixel.put("color", color);
                arrPixeles.put(pixel);

            }
            json.put(JSON.PIXELES.getStr(), arrPixeles);

            str = json.toString();

            enviarMensaje(str);

        }

    }

    private void pintarBolitas() {
        int[][] mBolitas = new int[bolitas.size()][2];
        for (int i = 0; i < mBolitas.length; i++) {
            for (int j = 0; j < mBolitas[i].length; j++) {
                mBolitas[i][j] = bolitas.get(i)[j];
            }
        }
        String str = "";

        for (int i = 0; i < mBolitas.length; i++) {
            JSONObject json = new JSONObject();
            JSONArray arrPixeles = new JSONArray();

            JSONObject pixel = new JSONObject();
            JSONObject punto = new JSONObject();
            JSONObject color = new JSONObject();
            punto.put("x", mBolitas[i][0]);
            punto.put("y", mBolitas[i][1]);
            ColorType clr = ColorType.ROSADO;
            color.put("R", clr.getR());
            color.put("G", clr.getG());
            color.put("B", clr.getB());
            pixel.put("pos", punto);

            pixel.put("color", color);
            arrPixeles.put(pixel);

            json.put(JSON.PIXELES.getStr(), arrPixeles);

            str = json.toString();

            enviarMensaje(str);

        }

    }

    private ColorType getColor(int num) {
        ColorType clr = null;
        return ColorType.values()[num];
    }

    @Override
    public void notifyObserver(String mensaje) {
        actuar(mensaje);
    }

    private void actuar(String mensaje) {

        JSONObject json = new JSONObject(mensaje);
        //borrar disparos
        depintarPacMan();
        if (json.get(JSON.BOTON.getStr()).equals(JSON.ACCION.getStr())) {

        } else if (json.get(JSON.BOTON.getStr()).equals(JSON.ARRIBA.getStr())) {
            moverArriba();
        } else if (json.get(JSON.BOTON.getStr()).equals(JSON.ABAJO.getStr())) {
            moverAbajo();
        } else if (json.get(JSON.BOTON.getStr()).equals(JSON.DERECHA.getStr())) {
            moverDerecha();
        } else if (json.get(JSON.BOTON.getStr()).equals(JSON.IZQUIERDA.getStr())) {
            moverIzquierda();
        }
        pintarPacMan();
    }

    private void moverArriba() {
        int[] pos = pacman.getPos();
        if (matrizTablero[pos[1] - 1][pos[0]] != ColorType.NEGRO.getNum()) {
            pacman.moverArriba();
        }

    }

    private void moverAbajo() {
        int[] pos = pacman.getPos();
        int d = pacman.getBody().length;
        if (matrizTablero[pos[1] + d][pos[0]] != ColorType.NEGRO.getNum()) {
            pacman.moverAbajo();
        }
    }

    private void moverDerecha() {
        int[] pos = pacman.getPos();
        int d = pacman.getBody().length;
        if (matrizTablero[pos[1]][pos[0] + d] != ColorType.NEGRO.getNum()) {
            pacman.moverDerecha();
        }
    }

    private void moverIzquierda() {
        int[] pos = pacman.getPos();
        if (matrizTablero[pos[1]][pos[0] - 1] != ColorType.NEGRO.getNum()) {
            pacman.moverIzquierda();
        }
    }

}
