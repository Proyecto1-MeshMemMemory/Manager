/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import javax.swing.JOptionPane;
/**
 *Clase LinkedList donde cada nodo tiene dos nodos anexados internamente.
 * @author betolan
 */
public class LinkedList{  
    // Se crea el tail y el head.
    private LinkedListNode  _inicio, _fin;
    // Contador de nodos de la lista.
    private int _sizeLista = 0;  
    /**
     * Constructor de una nueva lista.
     */
    public LinkedList(){
        _inicio = _fin = null; // Apuntan a nulo por inexistencia de nodos.
    }   
    /**
     * Metodo tipo void para agregar nuevos nodos o slaves.
     * @param pPos del nodo grande.
     * @param pID asignado.
     * @param pIP del dispositivo.
     * @param pPort del dispositivo.
     * @param pNumber del telefono.
     * @param pTotalBytes del dispositivo.
     */
    public void addNode(int pPos, int pID, int pIP, int pPort, int pNumber, int pTotalBytes){
        // Creacion de un nodo para recorrer la lista.
        LinkedListNode aux = _inicio;
        // Variable necesaria para conocer si el master tiene misma cantidad de bytes totales.
        boolean _nodeFinded = false;
        // Si la lista esta vacia se crea un nuevo nodo donde el anterior y el siguiente apuntan a fin.
        if(_inicio==null){
            _inicio = new LinkedListNode(pPos, _fin, _fin, pID, pIP, pPort, pNumber, pTotalBytes);
            // Ahora el final es el nuevo nodo.
            _fin=_inicio;
            _sizeLista++;
            //Se olvida de lo demas a continuacion.
            return;
        }
        // Recorre la lista mientras el nodo no apunte al inicio (el ultimo nodo no entra).
        while(aux!=_fin){
            // Si el nodo no tiene slave y el total del master es igual al del nuevo.
            if((aux.getSlave()==null) && (aux.getBytes()==pTotalBytes)){
                aux.setSlave(pID, pIP, pPort, pNumber);
                _nodeFinded = true;
                // Sle del ciclo.
                break;
            }
            // Si no sigue con el siguiente
            else{
                aux=aux.getNext();
            }
        }
        // El ultimo si cumple con las condiciones ademas de que la
        // variable de encontrado sea false creara un nuevo slave en el nodo.
        if(aux.getSlave()==null && (aux.getBytes()==pTotalBytes) && (_nodeFinded == false)){
            aux.setSlave(pID, pIP, pPort, pNumber);
            _nodeFinded = true; 
            // Ignora lo demas.
           return;
        }
        // Si lo anterior no se cumple entonces lo unico que queda es 
        // crear un nuevo nodo al final de la lista que el anterior apunte al 
        // final actual y el siguiente apunte a inicio.
        if(_nodeFinded == false){
            _fin = new LinkedListNode(pPos, _inicio, _fin, pID,
                    pIP, pPort, pNumber, pTotalBytes);
            // El nodo anterio r apunta a fin (nuevo nodo)
            _fin.getPrevious().setNext(_fin);
            _sizeLista++;
        }
    }
/**
 * 
 * @param pPos
 * @param pID
 * @param pIP
 * @param pPort
 * @param pNumber
 * @param pTotalBytes
 * @param pUsedBytes 
 */
    public void eliminar(int pPos, int pID, int pIP, int pPort, int pNumber, int pTotalBytes, int pUsedBytes){
        LinkedListNode actual;
	//en caso de q sea el primero
	if(_inicio.getPos()==pPos){               
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
        if(_fin.getPos()==pPos){
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
            while (actual.getPos()!= _fin.getPos()){
                if (actual.getPos() != pPos){
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
    /**
     * Metodo tipo void para mostrar la lista.
     */
    public void mostrarLista(){
        // Si la lista no esta vacia.
        if(_inicio != null){
            String _datos = "-";
            // Se crea un nodo auxiliar para recorrer la lista.
            LinkedListNode _aux = _inicio;
            // Imprimeria los nodos mientras no sea el ultimo.
            while (_aux!=_fin){
                _datos = _datos + "["+_aux.getPos()+"]-";
                _aux = _aux.getNext();
            }
            // Imprime el ultimo nodo.
            _datos = _datos + "["+_aux.getPos()+"]-";
            JOptionPane.showMessageDialog(null, _datos, "Mostrando lista", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}