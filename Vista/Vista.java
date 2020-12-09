package Vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.*;

public class Vista extends JPanel {

    private JFrame ventana;
    private JPanel panel;

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
        this.ventana = new JFrame();
        this.ventana.setTitle("Vista");//nombre de ventana
        this.ventana.setSize(600,600);//tamaño de ventana
        
        this.panel = new JPanel();
        

        this.panel.setBackground(Color.PINK);

        this.panel.setLayout(null);

        
        this.ventana.add(panel);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.ventana.setVisible(true);
    }


}