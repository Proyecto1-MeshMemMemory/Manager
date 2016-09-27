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

/**
 * Clase para hacer la escucha de los api que se conectan.
 * @author ellioth
 */
public class ServerApiListener implements Runnable, Constantes{

    private Socket _socketToApi;
    private DataOutputStream _out;
    private BufferedReader _in;
    private boolean _flag;
    private String _msgFromClient;
    
    /**
     * Constructor de la clase, recibe un puerto y establece el server en ese 
     * puerto, recibe un socket que es en cual va a escuchar a las apis.
     * @param pSocket dato tipo Socket.
     */
    public ServerApiListener(Socket pSocket){
        try {
            _socketToApi=pSocket;
            _flag=true;
            _out= new DataOutputStream(_socketToApi.getOutputStream());
            _in= new BufferedReader(new InputStreamReader(
                    _socketToApi.getInputStream()));
        } catch (IOException ex) {
            cout("Error: creacion de las variables para escuchar y enviar "
                    + "mensajes al api.");
        }
    }
    
    /**
     * metodo para estebelcer la bandera de poner en falso la bandera de
     * mensaje y recibir nuevos mensajes del API.
     */
    public void setFlagListenerFalse(){
        synchronized(this){
            _flag=false;
        }
    }
    
    /**
     * metodo para obtener la bandera que escucha, si es positiva o negativa.
     * @return 
     */
    public boolean getFlagListener(){
        boolean temp;
        synchronized(this){
            temp=_flag;
        }
        return temp;
    }
    
    /**
     * metodo para obtener los mensajes de un cliente api
     * @return retorna un dato tipo string.
     */
    public String getMsgFromApiClient(){
        String temp="";
        synchronized(this){
            temp=_msgFromClient;
        }
        return temp;
    }
    
    /**
     * metodo para enviarle un mensaje a un cliente api.
     * @param pJsonSendToApi dato tipo string.
     */
    public void sendToClientApi(String pJsonSendToApi){
        try {
            _out.write(pJsonSendToApi.getBytes());
            cout("Mensaje enviado: "+ pJsonSendToApi);
        } catch (IOException ex) {
            cout("Error:Falla en envio de mensaje, mensaje: "+ pJsonSendToApi);
        }
    }
    
    /**
     * hilo para estar bloqueandose y esperando a que se le permita recibir 
     * nuevos mensajes.
     */
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
            cout("Error: recepcion de mensaje del api");
        }
    }
    
}
