/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import Logic.Constantes;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * clase para hacer las escucha de los api que se conectan y guarda las
 * conexiones de estas.
 * @author ellioth
 */
public class ServerListener implements Runnable, Constantes{
    
    private int _port;
    private ServerSocket _server;
    private List<ServerApiListener> _listenerList;
    private DataOutputStream _out;
    private BufferedReader _in;
    
    /**
     * constructor de la clase, recibe el puerto para instancear el 
     * servidor.
     * @param pPort dato tipo entero.
     */
    public ServerListener(int pPort){
        _port=pPort;
        _listenerList= new ArrayList<>();
    }
    
    /**
     * metodo para obtener el tamaño de la lista de la cantidad de Api's
     * conectadas.
     * @return retorna un entero de la cantidad.
     */
    public int getSizeListListener(){
        int sizeTemp;
        synchronized(this){
            sizeTemp=_listenerList.size();
        }
        return sizeTemp;
    }
    
    /**
     * metodo para obtener la lista de las clases guardadas de los 
     * apis listeners.
     * @return retorna un dato tipo List<ServerApiListener>.
     */
    public List<ServerApiListener> getActualListListeners(){
        List<ServerApiListener> temp;
        synchronized(this){
            temp=_listenerList;
        }
        return temp;
    }
    
    /**
     * metodo para obtener la clase que contiene los datos de la nueva 
     * conexion del nuevo cliente api.
     * @return retorna un dato tipo ServerApiListener.
     */
    public ServerApiListener getNewClientApi(){
        ServerApiListener temp;
        synchronized(this){
            temp=_listenerList.get(_listenerList.size()-UNO);
        }
        return temp;
    }
    
    /**
     * hilo que se mantendra escuchando los api's que se van conectando.
     */
    @Override
    public void run() {
        try {
            _server= new ServerSocket(_port);
            System.out.println("Server creado en puerto "+String.valueOf(_port)+
                    ", en "+InetAddress.getLocalHost().getHostAddress());
            while(true){
                Socket clientSocket=_server.accept();
                System.out.println("Cliente aceptado");
                synchronized(this){
                    _listenerList.add(new ServerApiListener(clientSocket));
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: Falla en proceso de creacion de server "
                    + "para API");
        }
    }
}
