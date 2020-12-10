package src.Modelo;

import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends AbstractObservable implements Runnable {

    private int puerto;

    public Servidor(int puerto) {
        super();
        this.puerto = puerto;
        
    }

    @Override
    public void run() {

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;

        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(puerto);
            //System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            while (true) {//implmentar stop y pause de hilos

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                //System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
               
                //Leo el mensaje que me envia
                String mensaje = in.readUTF();
                //System.out.println("mensaje recibido");
                //System.out.println(mensaje);
                //System.out.println();
                
                
                //este hostio de la clase observable
                //cambiarlo por el patron
                notifyAllObservers(mensaje);
                
                //Cierro el socket
                sc.close();
                //System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}