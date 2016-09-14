/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaNueva;

/**
 *
 * @author Luis08
 */
public class Nodo {
    
    private int _id, _puerto, _numTel, bytesTot, bytesUso;
    private String _ip;
    private Nodo _siguiente, _anterior;
    
//Constructor cuando no exiten nodos
    public Nodo(int pId){
        this(pId, null, null);
        this.setId(pId);
    }
    
//Constructor cuando existen nodos
    public Nodo(int pId, Nodo pSiguiente, Nodo pAnterior){
        _id = pId;
        _siguiente = pSiguiente;
        _anterior = pAnterior;
        this.setId(pId);
    }
    
    
//Asignar el id    
public void setId(int pId){
        _id = pId;
    }

//Extraer el id
public int getId(){
    return _id;
}

//Asignar siguiente
public void setNext(Nodo pNodo){
    _siguiente = pNodo;
}

//Extraer siguiente
public Nodo getNext(){
    return _siguiente;
}

//Asignar anterior
public void setPrevious(Nodo pNodo){
    _anterior = pNodo;
}

//Extraer siguiente
public Nodo getPrevious(){
    return _anterior;
}

//Extraer ip
public String getIp(){
    return _ip;
}


}
