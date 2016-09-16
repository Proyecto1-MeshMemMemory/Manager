/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
/**
 * Clase LinkedListNode que guarda los mininodos.
 * @author betolan
 */
public class LinkedListNode{ 
    // Variable de identidad del nodo.
    private int _pos;
    // Crear tail y head.
    private LinkedListNode _siguiente, _anterior;
    // Mininodos apuntando a nulo.
    private Minions _master = null;
    private Minions _slave = null;
    // Cantidad total de bytes establecida por el primer nodo que entra,
    // de ahi se considera constante.
    private int _totalBytes;
    /**
     * Constructor cuando existen nodos.
     * @param pPos del nodo grande.
     * @param pSiguiente el nodo siguiente.
     * @param pAnterior el nodo anterior.
     * @param pID del mininodo.
     * @param pIP del mininodo.
     * @param pPort del mininodo.
     * @param pNumber del mininodo.
     * @param pTotalBytes para respectivo nodo de la lista.
     */
    public LinkedListNode(int pPos, LinkedListNode pSiguiente, LinkedListNode pAnterior, int pID, int pIP, int pPort, int pNumber, int pTotalBytes){
        _pos = pPos;
        _siguiente = pSiguiente;
        _anterior = pAnterior;
        // Creacion del master en el nuevo nodo.
        _master = new Minions(pID, pIP, pPort, pNumber);
        _totalBytes = pTotalBytes;
    }
     /**
     * Metodo tipo void para concertir slave a master.
     */
    public void slaveToMaster(){
        _master = _slave;
        _slave = null;
    }
    /**
     * Metodo tipo void para asignar el pos al nodo.
     * @param pPos 
     */    
    public void setPos(int pPos){
            _pos = pPos;
    }
    /**
     * Metodo tipo int para extraer el id.
     * @return _pos
     */
    public int getPos(){
        return _pos;
    }
    /**
     * Metodo tipo void para asignar siguiente.
     * @param pNodo 
     */
    public void setNext(LinkedListNode pNodo){
        _siguiente = pNodo;
    }
    /**
     * Metodo para extraer el siguiente.
     * @return _siguiente
     */
    public LinkedListNode getNext(){
        return _siguiente;
    }
    /**
     * Metodo tipo void para asignar siguiente.
     * @param pNodo 
     */
    public void setPrevious(LinkedListNode pNodo){
        _anterior = pNodo;
    }
    /**
     * Metodo para extraer el anterior.
     * @return _anterior
     */
    public LinkedListNode getPrevious(){
        return _anterior;
    }
    /**
     * Metodo tipo void para asignar el master.
     * @param pNewMaster 
     */
    public void setMaster(Minions pNewMaster){
        _master=pNewMaster;
    }
    /**
     * Metodo que retorna el master.
     * @return _master
     */
    public Minions getMaster(){
        return _master;
    } 
    /**
     * Metodo tipo void para crear el slave.
     * @param pID del mininodo.
     * @param pIP del mininodo.
     * @param pPort del mininodo.
     * @param pNumber del mininodo.
     */
    public void setSlave(int pID, int pIP, int pPort, int pNumber){
        _slave = new Minions(pID, pIP, pPort, pNumber);
    }
    /**
     * Metodo para extraer el slave.
     * @return _slave
     */
    public Minions getSlave(){
        return _slave;
    }
    /**
     * Metodo tipo void para asignar el total de bytes.
     * @param pTotalBytes
     * @param _totalBytes the _totalBytes to set
     */
    public void setTotalBytes(int pTotalBytes) {
        this._totalBytes = pTotalBytes;
    }
    /**
     * Metodo tipo int para extraer el total de bytes.
     * @return the _totalBytes
     */
    public int getTotalBytes() {
        return _totalBytes;
    } 
        /**
     * Metodo tipo int para retornar el total de bytes.
     * @return 
     */
    public int getBytes(){
        return getTotalBytes();
    }
}