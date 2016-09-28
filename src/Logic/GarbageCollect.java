/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Lists.CircularLinkedList;
import Lists.ListaOfMemory;
import Lists.NodesOfMemory;
import Lists.SuperNodeMemory;

/**
 *
 * @author ellioth
 */
public class GarbageCollect implements Runnable, Constantes{
    private CircularLinkedList _circularMemoryList;
    
    /**
     * 
     */
    public GarbageCollect(){
        
    }
    
    /**
     * 
     * @param pCircularMemoryList 
     */
    public void setMemoryList(CircularLinkedList pCircularMemoryList){
        _circularMemoryList=pCircularMemoryList;
    }

    /**
     * 
     */
    @Override
    public void run() {
        SuperNodeMemory temp =_circularMemoryList.getHead();
        ListaOfMemory tempListOfMemory=temp.getListMemory();
        int size= _circularMemoryList.getSize();
        NodesOfMemory tempMemoryNode= tempListOfMemory.getHead(), 
                tempNextMemory;
        for(int i =0; i<size; i++){
            while(tempMemoryNode!=null){
                if(tempMemoryNode.getRefCounter()==CERO){
                    tempNextMemory=tempMemoryNode.getNext();
                    synchronized(this){
                        tempListOfMemory.deleteNode(tempMemoryNode.getID());
                    }
                    tempMemoryNode=tempNextMemory;
                    continue;
                }
                tempMemoryNode= tempMemoryNode.getNext();
            }
            temp=temp.getNext();
            tempListOfMemory=temp.getListMemory();
            tempMemoryNode=tempListOfMemory.getHead();
        }
    }
    
    
}
