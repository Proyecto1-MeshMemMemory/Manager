/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 * Clase NodeThread encargada del hilo para 
 * eliminar los tokens con un tiempo de caducidad.
 * @author betolan
 */
public class TokenNode implements Constantes{
    private long _createdTime;
    private String _token;
    private TokenNode _next;
    /**
     * constructor de los nodos.
     * @param pToken dato tipo entero que recibe segun el token que se quiere
     */
    public  TokenNode(String pToken, long pCreateTime){
        _token=pToken;
        _createdTime=pCreateTime;
        _next=null;
    }
    
    public void setNext(TokenNode pNext){
        _next=pNext;
    }
    
    public TokenNode getNext(){
        return _next;
    }
    
    public long getTimeCreation(){
        return _createdTime;
    }
}