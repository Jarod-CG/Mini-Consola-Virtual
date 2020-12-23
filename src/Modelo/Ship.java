/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jarod
 */
public abstract class Ship {
    protected ColorType[][] body;
    protected int[] pos;

    public Ship(int[] pos) {
        this.pos = pos;
    }
    
     public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
    public ColorType[][] getBody() {
        return body;
    }

    public void setBody(ColorType[][] body) {
        this.body = body;
    }

    public boolean isIn(int[] p){
        boolean retorno = false;
        if ((pos[0] <= p[0] && pos[0] + body[0].length > p[0]) &&
            (pos[1] <= p[1] && pos[1] + body.length > p[1])){
            
            retorno = true;
        }
        return retorno;
    }
    
   protected abstract void buildBody();
    
    
}
