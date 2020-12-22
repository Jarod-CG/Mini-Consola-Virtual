package Controlador;


import Modelo.Cliente;
import Modelo.JSON;


import javax.swing.*;

import org.json.JSONObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//añadir el action listener
//crear el metodo para la función de crear mensaje

//extiende un JPanel para poder detectar las acciones del boton

public class Controlador extends JPanel implements KeyListener {

    //private Servidor servidor;
    //private Cliente cliente;
    private String IP = "127.0.0.1";
    //debe coincider con puerto de entrada en Consola
    private int puertoSalida = 5000;
    private JButton [][] botones;
    private JFrame ventana;
    private JPanel panel;
    private String mensaje;

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
    }

    public Controlador (){
        createFrame();
        createPanel();

        this.ventana.add(this, null);//hay que añadir la clase al panel
        this.ventana.add(panel, BorderLayout.CENTER);
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.ventana.setVisible(true);
        



   
    }

    private void createFrame(){
        this.ventana = new JFrame();
        this.ventana.setTitle("Controlador");//nombre de ventana
        this.ventana.setSize(600,600);//tamaño de ventana
        this.ventana.setResizable(true);
        this.ventana.setLayout(new BorderLayout());

    }

    private void createPanel(){
        this.panel = new JPanel();
        this.panel.setBackground(Color.BLACK);
        this.addKeyListener(this);
        createBotones();
    }


    private void createBotones(){
        System.out.println("crear botones");
        int size = 3;
        int distance = 1;
        this.botones = new JButton[size][size];
        this.panel.setLayout(new GridLayout(size, size, distance, distance));//y,x,...,...
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.botones[i][j] = new JButton();
                this.botones[i][j].setBackground(Color.RED);
                //this.botones[i][j].setPreferredSize(new Dimension(50, 50));
                this.panel.add(this.botones[i][j]);
            }
        }
    }





    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int b = e.getKeyCode();
        switch (b) {
            case KeyEvent.VK_W:
                enviarMensaje(createJson(JSON.ARRIBA));
                break;
            case KeyEvent.VK_S:
                enviarMensaje(createJson(JSON.ABAJO));
                break;
            case KeyEvent.VK_D:
                enviarMensaje(createJson(JSON.DERECHA));
                break;
            case KeyEvent.VK_A:
                enviarMensaje(createJson(JSON.IZQUIERDA));
                break;
            case KeyEvent.VK_SPACE:
                enviarMensaje(createJson(JSON.ACCION));
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        /*int b = e.getKeyCode();
        if (b == KeyEvent.VK_W 
            || b == KeyEvent.VK_S
            || b == KeyEvent.VK_D
            || b == KeyEvent.VK_A
            || b == KeyEvent.VK_SPACE){
            enviarMensaje(mensaje); 
            mensaje = "";  
            
        }*/
        // TODO Auto-generated method stub

    }

    public void enviarMensaje(String mensaje) {
        Cliente c = new Cliente(IP, puertoSalida, mensaje);
        Thread t = new Thread(c);
        t.start();
    }


    private String createJson (JSON j){
        String str = "";
        JSONObject json = new JSONObject();
        json.put(JSON.BOTON.getStr(), j.getStr());
        str = json.toString();
        return str;
    }

}