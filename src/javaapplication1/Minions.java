/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 * Clase Minions que representa cada dispositivo ya sea slave o master.
 * @author betolan
 */
public class Minions {
    // Atributos necesarios para cada dispositivo.
    private int _ID;
    private int _IP;
    private int _port;
    private int _number;
    private int _usedBytes;
    /**
     * Constructor del nodo que pertenece para cada nodo.
     * @param pID asignado.
     * @param pIP del dispositivo.
     * @param pPort del dispositivo.
     * @param pNumber de telefono.
     * @param pUsedBytes actualmente. 
     */
    public Minions(int pID, int pIP, int pPort, int pNumber, int pUsedBytes){
        _ID = pID;
        _IP = pIP;
        _port = pPort;
        _number = pNumber;
        _usedBytes = pUsedBytes;
    }  

    /**
     * @return the _ID
     */
    public int getID() {
        return _ID;
    }

    /**
     * @param _ID the _ID to set
     */
    public void setID(int _ID) {
        this._ID = _ID;
    }

    /**
     * @return the _IP
     */
    public int getIP() {
        return _IP;
    }

    /**
     * @param _IP the _IP to set
     */
    public void setIP(int _IP) {
        this._IP = _IP;
    }

    /**
     * @return the _port
     */
    public int getPort() {
        return _port;
    }

    /**
     * @param _port the _port to set
     */
    public void setPort(int _port) {
        this._port = _port;
    }

    /**
     * @return the _number
     */
    public int getNumber() {
        return _number;
    }

    /**
     * @param _number the _number to set
     */
    public void setNumber(int _number) {
        this._number = _number;
    }
    /**
     * @return the _usedBytes
     */
    public int getUsedBytes() {
        return _usedBytes;
    }

    /**
     * @param _usedBytes the _usedBytes to set
     */
    public void setUsedBytes(int _usedBytes) {
        this._usedBytes = _usedBytes;
    }
}
