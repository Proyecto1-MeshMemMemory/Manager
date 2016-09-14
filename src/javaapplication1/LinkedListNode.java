/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author betolan
 */
public class LinkedListNode { 
    private int _pos;
    private LinkedListNode _siguiente, _anterior;
    private Minions _master = null;
    private Minions _slave = null;
    private int _totalBytes;
    
    //Constructor cuando existen nodos
    public LinkedListNode(int pPos, LinkedListNode pSiguiente, LinkedListNode pAnterior, int pID, int pIP, int pPort, int pNumber, int pTotalBytes, int pUsedBytes){
        _pos = pPos;
        _siguiente = pSiguiente;
        _anterior = pAnterior;
        _master = new Minions(pID, pIP, pPort, pNumber, pUsedBytes);
        this.setId(pPos);
        this.setTotalBytes(pTotalBytes);
    }
    public LinkedListNode(Minions pMaster){
        _master=pMaster;
    }
    //Asignar el id    
    public void setId(int pId){
            _pos = pId;
        }
    
    public void slaveToMaster(){
        _master = _slave;
        _slave = null;
    }

    //Extraer el id
    public int getId(){
        return _pos;
    }

    //Asignar siguiente
    public void setNext(LinkedListNode pNodo){
        _siguiente = pNodo;
    }

    //Extraer siguiente
    public LinkedListNode getNext(){
        return _siguiente;
    }

    //Asignar anterior
    public void setPrevious(LinkedListNode pNodo){
        _anterior = pNodo;
    }

    //Extraer siguiente
    public LinkedListNode getPrevious(){
        return _anterior;
    }
    
    public Minions getMaster(){
        return _master;
    }
    
    public void setMaster(Minions pNewMaster){
        _master=pNewMaster;
    }
    
    public Minions getSlave(){
        return _slave;
    }
    
    public void setSlave(int pID, int pIP, int pPort, int pNumber, int pUsedBytes){
        _slave = new Minions(pID, pIP, pPort, pNumber, pUsedBytes);
    }

    public void setSlave(Minions pNewSlave){
        _slave=pNewSlave;
    }
    
    public int getBytes(){
        return getTotalBytes();
    }
    
    /**
     * @return the _totalBytes
     */
    public int getTotalBytes() {
        return _totalBytes;
    }

    /**
     * @param pTotalBytes
     * @param _totalBytes the _totalBytes to set
     */
    public void setTotalBytes(int pTotalBytes) {
        this._totalBytes = pTotalBytes;
    }
}