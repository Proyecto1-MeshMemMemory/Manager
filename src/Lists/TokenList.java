/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lists;

import Logic.Constantes;

/**
 * metodo para obtener la lista de los tokens actuales que estan usando.
 * @author ellioth
 */
public class TokenList implements Runnable, Constantes{
    // Puntero que indica el inicio de la lista o conocida tambien
    // como cabeza de la lista.
    private TokenNode _head;
    // Variable para registrar el tamaño de la lista.
    private int _size;
    
    /**
     * Constructor con un tamanio e inicio determinado.
     */
    public void Lista(){
        _head = null;
        _size = 0;
    }
    
    /**
     * Metodo tipo int que consulta cuantos tokens (nodos) tiene la lista.
     * @return numero entero entre donde n es el numero de elementos
     * que contenga la lista.
     */
    public int getSize(){
        return _size;
    }
    
    /**
     * Metodo tipo void que agrega un nuevo nodo al final de la lista.
     * @param pValue a agregar.
     */
    public void add(String pValue){
        if(_size==CERO || _head==null){
            synchronized(this){
                _head=new TokenNode(pValue,System.currentTimeMillis());
                _size=UNO;
            }
            return;
        }
        synchronized(this){
            TokenNode nuevo = new TokenNode(pValue, System.currentTimeMillis());
            nuevo.setNext(_head);
            _head=nuevo;
            _size++;
        }
    }
    
    /***
     * metodo para borrar los token con tiempos expirados, si el parametro de 
     * entrada no concuerda con el rango del tamaño de la lista o no existe
     * nada para eliminar simplemente no hace nada.
     * @param pNodePos recibe un dato tipo entero que es la posicion del 
     * nodo que vamos a eliminar.
     */
    private void delete(int pNodePos){
        if(_head==null || pNodePos>=_size){
            return;
        }
        TokenNode nodeToErase=_head, last=null;
        for(int i =0; i<pNodePos; i++){
            last= nodeToErase;
            nodeToErase= nodeToErase.getNext();
        }
        if(_head==nodeToErase){
            synchronized(this){
                _head=_head.getNext();
                _size--;
            }
            return;
        }
        synchronized(this){
            last.setNext(nodeToErase.getNext());
            _size--;
        }
    }
     
    /**
     * metodo para buscar nodos es un 
     * @param dato recibe un dato generico, este dato sirve para buscar el nodo 
     * que queremos.
     * @return retorna un booleando de si encontro el nodo o no.
     */
    public boolean find(String dato){
        synchronized(this){
            TokenNode tmp =_head;
            while(tmp!=null && !tmp.getData().equals(dato))
                tmp=tmp.getNext();
            if(tmp==null)
                return false;
            return true;
        }
    }
    
    /**
     * hilo para hacer que la lista vaya borrando los token por si solos.
     */
    @Override
    public void run() {
        while(true){
            try {
                TokenNode temp=_head;
                while(temp!=null){
                    long actualTime= System.currentTimeMillis();
                    for(int i=0; i<_size;i++){
                        if((actualTime-temp.getTimeCreation())>ALIVE_TIME_FOR_TOKENS){
                            delete(i);
                            cout("Token eliminado");
                        }
                        temp=temp.getNext();
                    }
                }
                Thread.sleep(SLEEPING_THREAD_TIME);
            } catch (InterruptedException ex) {
                cout("Error:No se puede poner a dormir el hilo en el la lista "
                        + "de Tokens");
            }
        }
    }
}
