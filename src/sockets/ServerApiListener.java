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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ellioth
 */
public class ServerApiListener implements Runnable, Constantes{

    private Socket _socketToApi;
    private DataOutputStream _out;
    private BufferedReader _in;
    private boolean _flag;
    private String _msgFromClient;
    
    public ServerApiListener(Socket pSocket){
        try {
            _socketToApi=pSocket;
            _flag=true;
            _out= new DataOutputStream(_socketToApi.getOutputStream());
            _in= new BufferedReader(new InputStreamReader(
                    _socketToApi.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ServerApiListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setFlagListenerFalse(){
        synchronized(this){
            _flag=false;
        }
    }
    
    public boolean getFlagListener(){
        boolean temp;
        synchronized(this){
            temp=_flag;
        }
        return temp;
    }
    
    public String getMsgFromApiClient(){
        String temp="";
        synchronized(this){
            temp=_msgFromClient;
        }
        return temp;
    }
    
    public void sendToClientApi(String pJsonSendToApi){
        try {
            _out.write(pJsonSendToApi.getBytes());
            System.out.println("Mensaje enviado: "+ pJsonSendToApi);
        } catch (IOException ex) {
            System.out.println("Error:Falla en envio de mensaje, mensaje: "+
                    pJsonSendToApi);
        }
    }
    
    @Override
    public void run() {
        try {
            while(true){
                while(!getFlagListener()){
                    String temp=_in.readLine();
                    synchronized(this){
                        _msgFromClient=temp;
                        _flag=true;
                    }
                }
            }
        } catch (IOException ex) {
        }
    }
    
}
