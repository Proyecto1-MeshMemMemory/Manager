/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
/**
 * Clase nodo para guardar la informacion.
 * @author betolan
 */
public class ListNode{
    // Variable en la cual se va a guardar el valor.
    private long _value;
    // Variable para enlazar los nodos.
    private ListNode _next;
    /**
     * Constructor que inicializamos el valor de las variables.
     */
    public void Nodo(){
        this._value = 0;
        this._next = null;
    }
    /**
     * Metodo tipo void para asignar el tiempo de creacion.
     * @param valor 
     */
    public void setValue(long valor){
        this._value = valor;
    }
    /**
     * Metodo tipo long para retornar el tiempo de creacion.
     * @return _value
     */ 
    public long getValue(){
        return _value;
    }
    /**
     * Metodo tipo void para asignar el nodo siguiente.
     * @param siguiente 
     */
    public void setNext(ListNode siguiente){
        this._next = siguiente;
    }   
    /**
     * Metodo para extraer el siguiente.
     * @return _next
     */
    public ListNode getNext(){
        return _next;
    }
}