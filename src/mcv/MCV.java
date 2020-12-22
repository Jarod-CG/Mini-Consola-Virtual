/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcv;

import Controlador.Controlador;
import Modelo.Consola;
import Modelo.SpaceInvader;
import Vista.Vista;

/**
 *
 * @author Jarod
 */
public class MCV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Consola consola = new Consola();
        
        Controlador controlador = new Controlador();
        Vista vista = new Vista();
        Consola consola = new SpaceInvader(50);
        
    }
    
}
