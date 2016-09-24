/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lists;

import Logic.Constantes;
import java.net.Socket;

/**
 * Clase Minions que representa cada dispositivo ya sea slave o master.
 * @author betolan
 */
public class MiniNode implements Constantes{
    // Atributos necesarios para cada dispositivo.
    private int _ID;
    private String _IP;
    private int _port;
    private int _number;
    private int _totalBytes;
    private Socket _Connetion;
    
    /**
     * Constructor de lo 
     * @param pID asignado.
     * @param pConnect socket al android correspondiente
     * @param pNumber de telefono.
     * @param pTotalBytes actualmente. 
     */
    public MiniNode(int pID, int pNumber, int pTotalBytes, Socket pConnect){
        _ID = pID;
        _number = pNumber;
        _totalBytes=pTotalBytes;
        _Connetion= pConnect;
    }  

    /**
     * metodo para conocer el ID del mini nodo que es el dispositivo de
     * android conectado al manager.
     * @return the _ID
     */
    public int getID() {
        return _ID;
    }

    /**
     * metodo para conocer el ip del dispositivo android.
     * @return the _IP
     */
    public String getIP() {
        return _IP;
    }

    /**
     * metodo para conocer el puerto del dispositvo.
     * @return the _port
     */
    public int getPort() {
        return _port;
    }

    /**
     * metodo para conocer el numero de telefono del dispositivo 
     * conectado.
     * @return the _number
     */
    public int getNumber() {
        return _number;
    }

    /**
     * metodo que nos indica cual es la cantidad total 
     * del bytes en el mini nodo.
     * @return 
     */
    public int getTotalBytes(){
        return _totalBytes;
    }
    
    /**
     * metodo para obtener el socket del mini nodo.
     * @return 
     */
    public Socket getScoket(){
        return _Connetion;
    }
}
