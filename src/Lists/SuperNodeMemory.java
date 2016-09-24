/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lists;

/**
 * Clase para hacer los Super Nodos que contendran la informacion de la 
 * memoria que hay en los dispositivos de android.
 * @author betolan
 */
public class SuperNodeMemory { 
    
    private int _pos;
    private SuperNodeMemory _siguiente, _anterior;
    private MiniNode _master;
    private MiniNode _slave;
    private int _totalBytes;
    private ListaOfMemory _listOfMemory;
    
    /**
     * constructor que inicializa el Super nodo de memoria con un master 
     * ya creado y la posicion que tiene en la lista.
     * @param pMaster 
     * @param pPos 
     */
    public SuperNodeMemory(MiniNode pMaster , int pPos){
        _master=pMaster;
        _totalBytes= pMaster.getTotalBytes();
        _anterior=null;
        _siguiente=null;
        _slave=null;
        _listOfMemory=new ListaOfMemory(_totalBytes);
    }
    
    /**
     * metodo para cambiar un Slave a un Master una ves que el master se 
     * pierda.
     */
    public void setSlaveToMaster(){
        _master = _slave;
        _slave = null;
    }

    /**
     * metodo para obtener la posicon del Super Nodo en la lista.
     * @return 
     */
    public int getPos(){
        return _pos;
    }

    /**
     * metodo para establecer el nodo que sigue de este.
     * @param pNodo 
     */
    public void setNext(SuperNodeMemory pNodo){
        _siguiente = pNodo;
    }

    /**
     * metodo para obtener el nodo que continua de este.
     * @return 
     */
    public SuperNodeMemory getNext(){
        return _siguiente;
    }

    /**
     * metodo para establecer el nodo que esta detras de este.
     * @param pNodo 
     */
    public void setPrevious(SuperNodeMemory pNodo){
        _anterior = pNodo;
    }

    /**
     * metodo para obtener el nodo que esta antes de este.
     * @return 
     */
    public SuperNodeMemory getPrevious(){
        return _anterior;
    }
    
    /**
     * metodo para obtener el MiniNodo master.
     * @return 
     */
    public MiniNode getMaster(){
        return _master;
    }
    
    /**
     * Metodo para obtener el MiniNodo slave.
     * @return 
     */
    public MiniNode getSlave(){
        return _slave;
    }
    
    /**
     * Metodo para establecer el MiniNodo Slave.
     * @param pSlave 
     */
    public void setSlave(MiniNode pSlave){
        _slave = pSlave;
    }
    
    /**
     * metodo para obtener la cantidad total de bytes que hay en 
     * el Super Nodo, como el nodo Slave es una redundancia del Master,
     * el Super Nodo solo tendra la misma cantidad que tiene el Master.
     * @return the _totalBytes
     */
    public int getTotalBytes() {
        return _totalBytes;
    }
    
    /**
     * meotodo para obtener la lista de la memoria sincronizada con el android.
     * @return retorna un dato tipo ListaOfMemory.
     */
    public ListaOfMemory getListMemory(){
        return _listOfMemory;
    }
}