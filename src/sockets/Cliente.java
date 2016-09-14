/*Jonatan Francisco Gonzalez Donis
 * Carne: 20314219
 */
package sockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente{

    //private JLabel etiqueta, carne;
    private final int _puerto;
    private final String _ip;
    private DataInputStream _entrada;
    private Socket _socket;
   
    public Cliente(){
        _puerto = 5000;
        _ip = "localhost";
        crearConexion();  
    }
  
    public void crearConexion(){
        try{
         _socket = new Socket(_ip,_puerto);
            try{
             _entrada = new DataInputStream(_socket.getInputStream());   
             //carne.setText(entrada.readUTF());
             System.out.println(_entrada.readUTF());
            }catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }
         _socket.close();
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
    
    public static void main(String[] args) {
           Cliente sv = new Cliente();
    }
}
