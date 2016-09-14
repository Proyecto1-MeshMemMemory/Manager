package sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

    private final int _puerto;
    private ServerSocket _server;
    private DataOutputStream salida;
    private Socket socket;
    
    public Servidor(){       
        _puerto = 5000;
    }
    public void crearConexion(){
        try{
            _server = new ServerSocket(_puerto);
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        
        socket = null;
        
        try{
            //Escucha por un cliente
            socket = _server.accept();
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        
        try{
            salida = new DataOutputStream(socket.getOutputStream());
            salida.writeUTF("Conexión completada");
            socket.close();
            _server.close();
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    public static void main(String[] args) {
        Servidor sv = new Servidor();
        sv.crearConexion();
    }
}
