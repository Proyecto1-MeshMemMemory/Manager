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
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para hacer los listener de los apis conectados que va a enviar la
 * informacion y este sera el medio de comunicacion entre ellos.
 * @author ellioth
 */
public class ServerAndroidListener implements Runnable, Constantes{

    private int _port;
    private ServerSocket _server;
    private List<Socket> _listenerList;
    private DataOutputStream _out;
    private BufferedReader _in;
    
    /**
     * Constructor de la clase que recibe el puerto en donde va a ser 
     * instanceado este.
     * @param pPort dato tipo entero que es el puerto donde vamos a 
     * establecer.
     */
    public ServerAndroidListener(int pPort){
        _port=pPort;
        _listenerList= new ArrayList<>();
    }
    
    /**
     * metodo para obtener el tamaño de la lista de los sockets de las con
     * @return retorna un dato tipo entero.
     */
    public int getSizeListListener(){
        int sizeTemp;
        synchronized(this){
            sizeTemp=_listenerList.size();
        }
        return sizeTemp;
    }
    
    /**
     * metodo para obtener la lista actual de los listener que tenemos.
     * @return retorna la lista de los sockets.
     */
    public List<Socket> getActualListListeners(){
        List<Socket> temp;
        synchronized(this){
            temp=_listenerList;
        }
        return temp;
    }
    
    /**
     * metodo para obtener un socket en especifico pasandole un index de la 
     * lista que contiene estos.
     * @param pSocket dato tipo entero que es la posicion del sockets.
     * @return retorna un socket.
     */
    public Socket getNewAndroidSocket(int pSocket){
        Socket temp;
        synchronized(this){
            temp=_listenerList.get(pSocket-UNO);
        }
        return temp;
    }
    
    /**
     * metodo para obtener un mensaje inicial del android que nos indicara la 
     * informacion personal del archivo.
     * @param pConnection recibe socket para realizar la conexion.
     * @return retorna un string del mensaje.
     */
    public String getMsg(Socket pConnection){
        String returnedMsg="";
        try {
            _in= new BufferedReader(new InputStreamReader(
                    pConnection.getInputStream()));
            returnedMsg=_in.readLine();
        } catch (IOException ex) {
            cout("Error: recepcion de mensaje a un nodo android");
        }
        return returnedMsg;
    }
    
    /**
     * metodo para enviar un mensaje al android especificandole un socket y 
     * un mensaje.
     * @param pMsg mensaje que enviaremos.
     * @param pConnection sockect de la coneccion.
     */
    public void sendMsg(String pMsg, Socket pConnection){
        try {
            _out= new DataOutputStream(pConnection.getOutputStream());
            _out.writeUTF(pMsg);
        } catch (IOException ex) {
            cout("Error: envio de mensaje a un nodo android");
        }
    }
    
    /**
     * metodo para enviar un Json a un Andoird y esperar un mensaje .
     * @param pMsg mensaje que envia.
     * @param pConnection socket que relaiza la conecxion para enviar.
     * @return 
     */
    public String sendJsonToAndroid(String pMsg, Socket pConnection){
        String returnedMsg="";
        try {
            _out= new DataOutputStream(pConnection.getOutputStream());
            _in= new BufferedReader(new InputStreamReader(
                    pConnection.getInputStream()));
            _out.writeUTF(pMsg);
            returnedMsg=_in.readLine();
        } catch (IOException ex) {
            cout("Error: recepcion y envio de mensaje a un nodo android");
        }
        return returnedMsg;
    }
    
    /**
     * hilo para ir aceptando los android que se van conectando.
     */
    @Override
    public void run() {
        try {
            _server= new ServerSocket(_port);
            synchronized(this){
                cout("Server creado en puerto "+String.valueOf(_port)+
                    ", en "+InetAddress.getLocalHost().getHostAddress());
            }
            while(true){
                Socket clientSocket=_server.accept();
                synchronized(this){
                    cout("Android aceptado");
                    _listenerList.add(clientSocket);
                }
            }
        } catch (IOException ex) {
            cout("Error: Falla en proceso de creacion de server "
                    + "para Android node");
        }
    }
}
