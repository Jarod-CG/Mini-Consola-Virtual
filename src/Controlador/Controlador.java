package Controlador;


import Modelo.Cliente;
import Modelo.ColorType;
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
    private int puertoSalida = 50000;
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
        this.ventana.setSize(300,300);//tamaño de ventana
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
        
        int size = 3;
        int distance = 1;
        this.botones = new JButton[size][size];
        this.panel.setLayout(new GridLayout(size, size, distance, distance));//y,x,...,...
        Color clr = new Color(ColorType.VINO.getR(),ColorType.VINO.getG(),ColorType.VINO.getB());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.botones[i][j] = new JButton();
                this.botones[i][j].setBackground(clr);
                //this.botones[i][j].setPreferredSize(new Dimension(50, 50));
                this.panel.add(this.botones[i][j]);
            }
        }
        clr = new Color(ColorType.NEGRO.getR(),ColorType.NEGRO.getG(),ColorType.NEGRO.getB());
        botones[0][0].setBackground(clr);
        botones[0][2].setBackground(clr);
        botones[2][0].setBackground(clr);
        botones[2][2].setBackground(clr);
    }





    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int b = e.getKeyCode();
        Color clr = new Color(ColorType.NARANJA.getR(),ColorType.NARANJA.getG(),ColorType.NARANJA.getB());
        switch (b) {
            case KeyEvent.VK_W:
                enviarMensaje(createJson(JSON.ARRIBA));
                 this.botones[0][1].setBackground(clr);
                break;
            case KeyEvent.VK_S:
                enviarMensaje(createJson(JSON.ABAJO));
                this.botones[2][1].setBackground(clr);
                break;
            case KeyEvent.VK_D:
                enviarMensaje(createJson(JSON.DERECHA));
                this.botones[1][2].setBackground(clr);
                break;
            case KeyEvent.VK_A:
                enviarMensaje(createJson(JSON.IZQUIERDA));
                this.botones[1][0].setBackground(clr);
                break;
            case KeyEvent.VK_SPACE:
                enviarMensaje(createJson(JSON.ACCION));
                this.botones[1][1].setBackground(clr);
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int b = e.getKeyCode();
        Color clr = new Color(ColorType.VINO.getR(),ColorType.VINO.getG(),ColorType.VINO.getB());
        switch (b) {
            case KeyEvent.VK_W:
                this.botones[0][1].setBackground(clr);
                break;
            case KeyEvent.VK_S:
                this.botones[2][1].setBackground(clr);
                break;
            case KeyEvent.VK_D:
                this.botones[1][2].setBackground(clr);
                break;
            case KeyEvent.VK_A:
                this.botones[1][0].setBackground(clr);
                break;
            case KeyEvent.VK_SPACE:
                this.botones[1][1].setBackground(clr);
                break;

        }

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