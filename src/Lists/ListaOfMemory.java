/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lists;

import Logic.Constantes;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * clase para hacer la lista de la memoria 
 * @author ellioth
 */
public class ListaOfMemory  implements Constantes{
    
    private NodesOfMemory _head;
    private NodesOfMemory _tail;
    private int _MemoryLeft;
    private int _totalMemory;
    
    /**
     * constructor de la clase, recibe la cantidad de memoria que puede aguantar
     * el nodo.
     * @param pMemory entero que es el dato de la cantidad de memoria que 
     * tenemos en el mini nodo.
     */
    public ListaOfMemory(int pMemory){
        _totalMemory=pMemory;
        _MemoryLeft=pMemory;
    }
    
    /**
     * metodo para intentar la insercion de un dato en el nodo.
     * @param pInfo
     * @param Id
     * @return 
     */
    public int tryInsertion(String pInfo, String Id){
        try {
            JSONObject tempData= new JSONObject(pInfo);
            int newSize=tempData.getInt(SIZE);
            //String newId=tempData.getString(ID);
            if (_head==null){
                _head=_tail=new NodesOfMemory(pInfo, CERO, Id);
                _MemoryLeft-=newSize;
                return CERO;
            }
            //tenemos que buscar un bloque que pueda almacenar el dato.
            NodesOfMemory temp=_head;
            int i=0;
            while(temp.getNext()!=null){
                int nextPlace=temp.getNext().getSpaceMemory();
                int actualPlace=temp.getSpaceMemory()+temp.getSize();
                if((nextPlace-actualPlace)>=newSize){
                    //se encontro un espacio donde meter el dato nuevo.
                    break;
                }
                temp=temp.getNext();
                i++;
            }
            if(temp.getNext()==null){
                if((_totalMemory-(temp.getSpaceMemory()+temp.getSize()))>=newSize){
                    int newSpace= temp.getSpaceMemory()+temp.getSize();
                    temp.setNext( new NodesOfMemory(pInfo, newSpace, Id));
                    _tail=temp.getNext();
                    _MemoryLeft-=tempData.getInt(SIZE);
                    return i;
                }
            }
            NodesOfMemory nextTemp=temp.getNext();
            int newSpace= temp.getSpaceMemory()+temp.getSize();
            temp.setNext(new NodesOfMemory(pInfo,newSpace, Id));
            temp.getNext().setNext(nextTemp);
            _MemoryLeft-=tempData.getInt(SIZE);
            return i;
        } catch (JSONException ex) {
            cout("Error: no se puede ingresar el nodo en la memoria del Android");
        }
        return -UNO;
    }
    
    /**
     * devuelve la cabeza de la lista o el primer nodo que se ingreso
     * @return dato de la clase NodesOfMemory, esta es la cabeza de la lista.
     */
    public NodesOfMemory getHead(){
        return _head;
    }
    
    /**
     * metodo para borrar el dato de la lista de memoria.
     * @param pId dato tipo string.
     * @return retorna un entero del resultado del intento de la eliminacion, 
     * cero para una eliminacion o un uno para no eliminacion.
     */
    public int deleteNode(String pId){
        if(_head.getID().equals(pId)){
            _MemoryLeft-=(_head.getSize());
            _head=_head.getNext();
            return DELETED;
        }
        NodesOfMemory temp=_head, last=null;
        while(temp!=null){
            if(temp.getID().equals(pId))
                break;
            last=temp;
            temp=temp.getNext();
            
        }
        if(temp==null){
            return NOTHING_DELETED;
        }
        else if(temp==_tail){
            _MemoryLeft-=(temp.getSize());
            _tail=_tail.getNext();
            last.setNext(_tail);
            return DELETED;
        }
        _MemoryLeft-=(temp.getSize());
        temp=temp.getNext();
        last.setNext(temp);
        return DELETED;
    }
    
    /**
     * Metodo para cambiar el conteo de referencias en los nodos, recibe un 
     * id del nodo al cual le queremos realizar la operacion y la operacion 
     * querida, aumentar o disminuir el conteo de referencias.
     * @param pID entero del id nodo que queremos.
     * @param pOperation entero de la operacion que haremos.
     */
    public void changeRefCounter(String pID, int pOperation){
        NodesOfMemory temp=_head;
        while(temp!=null){
            if(temp.getID().equals(pID))
                break;
            temp=temp.getNext();
        }
        if(temp==null){
            return;
        }
        if(pOperation==OPERATION_IR){
            temp.increaseRefCounter();
            return;
        }
        temp.decreaseRefCounter();
    }
    
    /**
     * 
     */
    public void BurpData(){
        
    }
    
    /**
     * metodo para imprimir todos los valores internos de los nodos.
     */
    public void print(){
        NodesOfMemory tmp=_head;
        while(tmp!=null){
            System.out.println(tmp.getData());
            tmp=tmp.getNext();
        }
    }
    
    /**
     * metodo para obtener el nodo en un espacio de la lista.
     * @param pIndex recibe un indice de donde esta el nodo que queremos, 
     * empieza desde cero.
     * @return retorna un dato tipo NodesOfMemory.
     */
    public NodesOfMemory getNode(int pIndex){
        NodesOfMemory temp=_head;
        for(int i =0;i<pIndex; i++ ){
            temp= temp.getNext();
        }
        return temp;
    }
    
    /**
     * metodo para buscar nodos es un 
     * @param dato recibe un dato generico, este dato sirve para buscar el nodo 
     * que queremos.
     * @return retorna un booleando de si encontro el nodo o no.
     */
    public boolean find(String dato){
        NodesOfMemory tmp =_head;
        while(tmp!=null && tmp.getData().equals(dato))
            tmp=tmp.getNext();
        if(tmp==null)
            return false;
        return true;
    }
    
    /**
     * metodo para obtener el tamaño de memoria restante de la lista.
     * @return 
     */
    public int getSizeLeft(){
        return _MemoryLeft;
    }
}