package src.Modelo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;



public class Prueba extends JPanel implements MouseMotionListener, KeyListener {

    
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("RayCast Visualizer");
        window.setSize(600, 600);

        Prueba rcv = new Prueba();
        rcv.addKeyListener(rcv);

        window.add(rcv);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
    public Prueba() {
        this.setBackground(Color.BLACK);

        this.setLayout(null);


        addMouseMotionListener(this);

        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }

    
    

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("es presionado");
        int b = e.getKeyCode();
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
