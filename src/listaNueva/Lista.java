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

import javax.swing.JOptionPane;


public class Lista {
    
    private Nodo  _inicio, _fin;
    private int _id = 1, _sizeLista = 0;
    
    //Constructor
    public Lista(){
        _inicio = _fin = null;
    }
    
    //Revisar si la lista está vacía
    public boolean verificarLista(){
        if (_inicio == null){
            return true;
        }
        else {
            return false;
        }
        }
    
    //Insertar nodo al inicio
    public void agregarAlInicio(int pId){
        if(!verificarLista()){
            _inicio = new Nodo(pId, _inicio, null);
            _inicio.getNext().setPrevious(_inicio);
        }
        else{
            _inicio = _fin = new Nodo(pId);
        }
        _sizeLista++;
        System.out.println("id del nodo agregado: " + pId);
        
    }
    
    //Insertar nodo al final
    public void agregarAlFinal(int pId){
        if(!verificarLista()){
            _fin = new Nodo(pId, null, _fin);
            _fin.getPrevious().setNext(_fin);
        }
        else{
            _inicio = _fin = new Nodo(pId);
        }
        _sizeLista++;
        System.out.println("id del nodo agregado: " + pId);
    }
    
    //Eliminar nodo
    public void eliminar(int pId){
        Nodo actual;
	//en caso de q sea el primero
	if(_inicio.getId()==pId){
                
                if(_inicio == _fin){
                    _inicio=_fin=null;
                }
                else{
                    _inicio = _inicio.getNext();
                    _inicio.setPrevious(null);
        }   
                System.out.println("Elemento eliminado: " + pId);
		return;
	}
        //en caso de q sea el último
        if(_fin.getId()==pId){
            if(_inicio==_fin){
                _inicio=_fin=null;
            }
            else{
                _fin=_fin.getPrevious();
                _fin.setNext(null);
            }
            
            System.out.println("Elemento eliminado:" + pId);
            return;
        }
        
        //En caso de q no sea ni el primero ni el último
        else{
            actual = _inicio;
            while (actual.getId()!= _fin.getId()){
                if (actual.getId() != pId){
                    actual = actual.getNext();
                }
                else{
                    actual.getPrevious().setNext(actual.getNext());
                    actual.getNext().setPrevious(actual.getPrevious());
                    System.out.println("Elemento eliminado:" + pId);
                    return;
                }
            }
        }
        
}
    
    //Asignar id a cada nodo creado
    public int setId(){
        int _temp = _id;
        _id++;
        return _temp;
    }
    
    //Mostrar lista
    public void mostrarLista(){
        if(!verificarLista()){
            String _datos = "-";
            Nodo _aux = _inicio;
            while (_aux != null){
                _datos = _datos + "["+_aux.getId()+"]-";
                _aux = _aux.getNext();
            }
            JOptionPane.showMessageDialog(null, _datos, "Mostrando lista", JOptionPane.INFORMATION_MESSAGE);

        }

    }
    
}
