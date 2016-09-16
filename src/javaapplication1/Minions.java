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
     * Constructor del nodo que pertenece para cada nodo 
     * con cantidad iniciarl cero de bytes usados.
     * @param pID asignado.
     * @param pIP del dispositivo.
     * @param pPort del dispositivo.
     * @param pNumber de telefono.
     */
    public Minions(int pID, int pIP, int pPort, int pNumber){
        _ID = pID;
        _IP = pIP;
        _port = pPort;
        _number = pNumber;
        _usedBytes = 0;
    }  
    /** 
     * Metodo tipo int que retorna el ID.
     * @return the _ID
     */
    public int getID() {
        return _ID;
    }
    /**
     * Metodo tipo void para asignar valor al ID.
     * @param _ID the _ID to set
     */
    public void setID(int _ID) {
        this._ID = _ID;
    }
    /**
     * Metodo tipo int que retorna el IP.
     * @return the _IP
     */
    public int getIP() {
        return _IP;
    }
    /**
     * Metodo tipo void para asignar valor al IP.
     * @param _IP the _IP to set
     */
    public void setIP(int _IP) {
        this._IP = _IP;
    }
    /**
     * Metodo tipo int que retorna el puerto.
     * @return the _port
     */
    public int getPort() {
        return _port;
    }
    /**
     * Metodo tipo void para asignar valor al puerto.
     * @param _port the _port to set
     */
    public void setPort(int _port) {
        this._port = _port;
    }
    /**
     * Metodo tipo int que retorna el numero de telefono.
     * @return the _number
     */
    public int getNumber() {
        return _number;
    }
    /**
     * Metodo tipo void para asignar valor a numero de telefono.
     * @param _number the _number to set
     */
    public void setNumber(int _number) {
        this._number = _number;
    }
    /**
     * Metodo tipo int que retorna la cantidad de bytes usados.
     * @return the _usedBytes
     */
    public int getUsedBytes() {
        return _usedBytes;
    }
    /**
     * Metodo tipo void para asignar valor a los bytes usados.
     * @param _usedBytes the _usedBytes to set
     */
    public void setUsedBytes(int _usedBytes) {
        this._usedBytes = _usedBytes;
    }
}