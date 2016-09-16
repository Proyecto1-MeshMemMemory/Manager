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

public class ServerListener implements Runnable, Constantes{
    private int _port;
    private ServerSocket _server;
    private List<ServerApiListener> _listenerList;
    private DataOutputStream _out;
    private BufferedReader _in;
    
    public ServerListener(int pPort){
        _port=pPort;
        _listenerList= new ArrayList<>();
    }
    
    public int getSizeListListener(){
        int sizeTemp;
        synchronized(this){
            sizeTemp=_listenerList.size();
        }
        return sizeTemp;
    }
    
    public List<ServerApiListener> getActualListListeners(){
        List<ServerApiListener> temp;
        synchronized(this){
            temp=_listenerList;
        }
        return temp;
    }
    
    public ServerApiListener getNewClientApi(){
        ServerApiListener temp;
        synchronized(this){
            temp=_listenerList.get(_listenerList.size()-UNO);
        }
        return temp;
    }
    //public 
    @Override
    public void run() {
        try {
            _server= new ServerSocket(_port);
            System.out.println("Server creado en puerto "+String.valueOf(_port)+
                    ", en "+InetAddress.getLocalHost().getHostAddress());
            Socket clientSocket=_server.accept();
            System.out.println("Cliente aceptado");
            synchronized(this){
                _listenerList.add(new ServerApiListener(clientSocket));
            }
        } catch (IOException ex) {
            System.out.println("Error: Falla en proceso de creacion de server "
                    + "para API");
        }
    }
}
