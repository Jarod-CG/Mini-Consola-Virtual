package src.Modelo;

//import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Runnable {

    private int puerto;
    private String mensaje;
    private String HOST;

    public Cliente( String HOST,int puerto,String mensaje) {
        this.puerto = puerto;
        this.mensaje = mensaje;
        this.HOST = HOST;   
    }

    @Override
    public void run() {
        //Host del servidor
        
        //Puerto del servidor
        DataOutputStream out;

        try {
            //Creo el socket para conectarme con el cliente
            //System.out.println(HOST + " " + puerto);
            Socket sc = new Socket(HOST, puerto);

            out = new DataOutputStream(sc.getOutputStream());
            //System.out.println("mensaje en clientes antes de enviar");
            //System.out.println(mensaje);
            //System.out.println();
            //Envio un mensaje al cliente
            out.writeUTF(mensaje);

            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}