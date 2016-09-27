/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Lists.CircularLinkedList;
import Lists.MiniNode;
import Lists.SuperNodeMemory;

/**
 *
 * @author ellioth
 */
public class MakingPing implements Runnable, Constantes{
    
    private CircularLinkedList _circularListToCheck;
    
    public MakingPing(CircularLinkedList pCircularListToCheck){
        _circularListToCheck=pCircularListToCheck;
    }

    /**
     * hilo para realizar el ping a los nodos de Android conectados y elimina
     * los que ya se hayan desconectados.
     */
    @Override
    public void run() {
        SuperNodeMemory temp;
        MiniNode tempMiniMaster=null;
        MiniNode tempMiniSlave=null;
        int size;
        synchronized(this){
            size=_circularListToCheck.getSize();
            temp=_circularListToCheck.getHead();
            
        }
        for(int i=0; i<size; i++){
            if(temp.getSlave()!=null){
                synchronized(this){
                    tempMiniMaster=temp.getMaster();
                    tempMiniSlave=temp.getSlave();
                }
            }
            if(tempMiniMaster!=null){
                if(tempMiniMaster.ping()==DESCONNECTED){
                    cout("Error: La coneccion con el Master se ha perdido.");
                    _circularListToCheck.eliminarMiniNode(tempMiniMaster.getID());
                }
            }
            if(tempMiniSlave!=null){
                if(tempMiniSlave.ping()==DESCONNECTED){
                    cout("Error: La coneccion con el Master se ha perdido.");
                    _circularListToCheck.eliminarMiniNode(tempMiniSlave.getID());
                }
            }
            temp=temp.getNext();
        }
        
    }
    
    
    
}
