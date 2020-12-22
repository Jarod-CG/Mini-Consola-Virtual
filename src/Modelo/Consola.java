package Modelo;

public class Consola implements IObserver{
    private Servidor servidor;

    //private Cliente cliente;
    private String IP = "127.0.0.1";
    //debe coincider con puerto de entrada en Consola
    private int puertoSalida = 5001;//puerto de salida para Vista

    public Consola (){
        this.servidor = new Servidor(5000);//es el puerto de entrada
        this.servidor.addObserver(this);
        Thread t = new Thread(this.servidor);
        t.start();
    }

    @Override
    public void notifyObserver(String mensaje) {
        //System.out.println("----------------------- mensaje recibido ---------------------");
        //System.err.println(mensaje);
        //System.out.println("reenviar");
        enviarMensaje("Reenvio : " + mensaje);

    }

    public void enviarMensaje(String mensaje) {
        Cliente c = new Cliente(IP, puertoSalida, mensaje);
        Thread t = new Thread(c);
        t.start();
    }
}
