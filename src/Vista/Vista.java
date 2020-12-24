package Vista;

//import javax.swing.JButton;
import Modelo.ColorType;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Modelo.IObserver;
import Modelo.JSON;
import Modelo.Servidor;

import java.awt.*;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class Vista extends JPanel implements IObserver   {

    private JFrame ventana;
    private JPanel panel;
    private JPanel [][] pixeles;
    private Servidor servidor;


    public static void main(String[] args) {
        /*JFrame window = new JFrame();
        window.setTitle("Vista");
        window.setSize(600, 600);
*/
       Vista vista = new Vista();
/*
        window.add(vista);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);*/
    }

    public Vista () {
        this.servidor = new Servidor(50001);//es el puerto de entrada
        this.servidor.addObserver(this);
        Thread t = new Thread(this.servidor);
        t.start();
       /* this.ventana = new JFrame();
        this.ventana.setTitle("Vista");//nombre de ventana
        this.ventana.setSize(600,600);//tamaño de ventana
        this.ventana.setResizable(false);
        this.ventana.setLayout(new BorderLayout());*/
        
        createFrame();
        createPanel();
        /*this.panel = new JPanel();
        this.panel.setBackground(Color.PINK);*/
       

        

        
        this.ventana.add(panel, BorderLayout.CENTER);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.ventana.setVisible(true);

        
    }

    private void createFrame(){
        this.ventana = new JFrame();
        this.ventana.setTitle("Vista");//nombre de ventana
        this.ventana.setSize(600,600);//tamaño de ventana
        this.ventana.setResizable(false);
        this.ventana.setLayout(new BorderLayout());

    }

    private void createPanel(){
        this.panel = new JPanel();
        this.panel.setBackground(Color.BLACK);
 
        createPixeles();
    }

    private void createPixeles(){
        int size = 50;
        int distance = 1;
        this.pixeles = new JPanel[size][size];
        this.panel.setLayout(new GridLayout(size, size, distance, distance));//y,x,...,...
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.pixeles[i][j] = new JPanel();
                this.pixeles[i][j].setBackground(getRandomColor());
                //this.pixeles[i][j].setPreferredSize(new Dimension(50, 50));
                this.panel.add(this.pixeles[i][j]);
            }
        }
    }

    private Color getRandomColor(){
        Random rand = new Random();
        int largo = ColorType.values().length;
        int index = rand.nextInt(largo);
        ColorType clr = ColorType.values()[index];
        
        int R,G,B;
        R = clr.getR();
        G = clr.getG();
        B = clr.getB();
        return new Color (R,G,B);
    }


    @Override
    public void notifyObserver(String mensaje) {
        //System.out.println(mensaje);
        pintar(mensaje);

    }
    
    private void pintar(String str){
        JSONObject json = new JSONObject(str);
        JSONArray arr = (JSONArray) json.get(JSON.PIXELES.getStr());
        for (int i = 0; i < arr.length(); i++) {
            JSONObject pix = (JSONObject) arr.get(i);
            JSONObject pos = (JSONObject) pix.get("pos");
            JSONObject color = (JSONObject) pix.get("color");
            int x = (int) pos.get("x");
            int y = (int) pos.get("y");
            //System.out.println("x : " +x + " y : " + y + " color : " + color);
            Color clr = new Color(color.getInt("R"),color.getInt("G"),color.getInt("B"));
            pixeles[y][x].setBackground(clr);
        }
    }
    
   

}