/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import javax.swing.JOptionPane;

/**
 *
 * @author betolan
 */
public class LinkedList {       
    private LinkedListNode  _inicio, _fin;
    private int _pos = 1, _sizeLista = 0;  
    //Constructor
    public LinkedList(){
        _inicio = _fin = null;
    }   
    
    public void addNode(int pPos, int pID, int pIP, int pPort, int pNumber, int pTotalBytes, int pUsedBytes){
        LinkedListNode aux = _inicio;
        boolean _nodeFinded = false;
        
        if(_inicio==null){
            _inicio = new LinkedListNode(pPos, _fin, _fin, pID, pIP, pPort, pNumber, pTotalBytes, pUsedBytes);
            _fin=_inicio;
            _sizeLista++;
            return;
        }
        while(aux!=_fin){
            if((aux.getSlave()==null) && (aux.getBytes()==pTotalBytes)){
                aux.setSlave(pID, pIP, pPort, pNumber, pUsedBytes);
                _nodeFinded = true;
                break;
            }
            else{
                aux=aux.getNext();
            }
        }
        if(aux.getSlave()==null && (aux.getBytes()==pTotalBytes) && (_nodeFinded == false)){
            aux.setSlave(pID, pIP, pPort, pNumber, pUsedBytes);
            _nodeFinded = true; 
           return;
        }
        if(_nodeFinded == false){
            _fin = new LinkedListNode(pPos, _inicio, _fin, pID,
                    pIP, pPort, pNumber, pTotalBytes, pUsedBytes);
            _fin.getPrevious().setNext(_fin);
            //_fin= temp;
            _sizeLista++;
        }
    }
 
    //Eliminar nodo
    public void eliminar(int pPos, int pID, int pIP, int pPort, int pNumber, int pTotalBytes, int pUsedBytes){
        LinkedListNode actual;
	//en caso de q sea el primero
	if(_inicio.getId()==pPos){               
            if(_inicio == _fin){
                _inicio=_fin=null;
            }
            else{
                _inicio = _inicio.getNext();
                _inicio.setPrevious(null);
            }   
            System.out.println("Elemento eliminado: " + pPos);
            return;
            }
        //en caso de q sea el último
        if(_fin.getId()==pPos){
            if(_inicio==_fin){
                _inicio=_fin=null;
            }
            else{
                _fin=_fin.getPrevious();
                _fin.setNext(null);
            }   
            System.out.println("Elemento eliminado:" + pPos);
            return;
        }      
        //En caso de q no sea ni el primero ni el último
        else{
            actual = _inicio;
            while (actual.getId()!= _fin.getId()){
                if (actual.getId() != pPos){
                    actual = actual.getNext();
                }
                else{
                    actual.getPrevious().setNext(actual.getNext());
                    actual.getNext().setPrevious(actual.getPrevious());
                    System.out.println("Elemento eliminado:" + pPos);
                    return;
                }
            }
        } 
}
    //Asignar id a cada nodo creado
    public int setId(){
        int _temp = _pos;
        _pos++;
        return _temp;
    }
    //Mostrar lista
    public void mostrarLista(){
        if(_inicio != null){
            String _datos = "-";
            LinkedListNode _aux = _inicio;
            
            while (_aux!=_fin){
                _datos = _datos + "["+_aux.getId()+"]-";
                _aux = _aux.getNext();
            }
            _datos = _datos + "["+_aux.getId()+"]-";
            JOptionPane.showMessageDialog(null, _datos, "Mostrando lista", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}