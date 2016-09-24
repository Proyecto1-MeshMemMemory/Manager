/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lists;
import Logic.Constantes;

/**
 * Lsita cirular doble donde se guardaran los super nodos que estos 
 * a su vez contienen los master y slave y aparte un mapa de que cosas hay
 * en la memoria de la lista circular.
 * @author betolan
 */
public class CircularLinkedList implements Constantes{
    
    private SuperNodeMemory  _inicio, _fin;
    private int _sizeLista; 
    private int _totalSpace;
    //Constructor
    public CircularLinkedList(){
        _inicio = _fin = null;
        _sizeLista=CERO;
    }   
    
    /**
     * metodo para agregar un nodo cuando se le ingresa un Mini nodo ya creado.
     * @param pNode 
     * @return  
     */
    public int addNode(MiniNode pNode){
        SuperNodeMemory aux = _inicio;
        boolean _nodeFinded = false;
        
        if(_inicio==null){
            _inicio = new SuperNodeMemory(pNode, CERO);
            _inicio.setNext(_inicio);
            _inicio.setPrevious(_inicio);
            _fin=_inicio;
            _sizeLista++;
            _totalSpace+=pNode.getTotalBytes();
            return MASTER;
        }
        int pos=CERO;
        while(aux!=_fin){
            if((aux.getSlave()==null) && (aux.getTotalBytes()==pNode.getTotalBytes())){
                aux.setSlave(pNode);
                _totalSpace+=pNode.getTotalBytes();
                _nodeFinded = true;
                break;
            }
            aux=aux.getNext();
            ++pos;
        }
        if(aux.getSlave()==null && (aux.getTotalBytes()==pNode.getTotalBytes()) 
                && (_nodeFinded == false)){
            aux.setSlave(pNode);
            _totalSpace+=pNode.getTotalBytes();
            return SLAVE;
        }
        else if(_nodeFinded == false){
            SuperNodeMemory temp= new SuperNodeMemory(pNode,pos);
            temp.setNext(_inicio);
            temp.setPrevious(_fin);
            _fin=temp;
            _inicio.setPrevious(_fin);
            _totalSpace+=pNode.getTotalBytes();
            _sizeLista++;
            return MASTER;
        }
        if(_nodeFinded)
            return SLAVE;
        return -UNO;
    }
    
    /**
     * Metodo para eliminar un mini nodo en la lista de memoria, se recibe 
     * un id del mini nodo que se quiera eliminar.
     * @param pId 
     */
    public void eliminarMiniNode(int pId){
        if(_inicio==null){
            return;
        }
        if(_inicio.getSlave()==null){
            if(_inicio.getMaster().getID()==pId){
                _inicio=_fin=null;
                return;
            }
        }
        else if(_inicio.getSlave()!=null){
            if(_inicio.getMaster().getID()==pId){
                _inicio.setSlaveToMaster();
                return;
            }
            else if(_inicio.getSlave().getID()==pId){
                _inicio.setSlave(null);
                return;
            }
        }
        SuperNodeMemory temp=_inicio.getNext();
        while(temp!=_fin){
            if(temp.getMaster().getID()==pId){
                break;
            }
            else if( temp.getSlave()!=null && temp.getSlave().getID()==pId){
                break;
            }
            temp= temp.getNext();
        }
        if(temp==_fin){
            if(_fin.getSlave()==null){
                if(_fin.getMaster().getID()==pId){
                    _fin=_fin.getPrevious();
                    _inicio.setPrevious(_fin);
                    return;
                }
            }
            else if(_fin.getSlave()!=null){
                if(_fin.getMaster().getID()==pId){
                    _fin.setSlaveToMaster();
                    return;
                }
                else if(_fin.getSlave().getID()==pId){
                    _inicio.setSlave(null);
                    return;
                }
            }
        }
        if(temp.getSlave()==null){
            if(temp.getMaster().getID()==pId){
                SuperNodeMemory last=temp.getPrevious();
                last.setNext(temp.getNext());
                temp.getNext().setPrevious(last);
            }
        }
        else if(temp.getSlave()!=null){
            if(temp.getMaster().getID()==pId){
                temp.setSlaveToMaster();
            }
            else if(temp.getSlave().getID()==pId){
                temp.setSlave(null);
            }
        }
    }
    
    /**
     * metodo para obtener la cabeza de la lista
     * @return retorna un dato ripo SuperNodeMemory
     */
    public SuperNodeMemory getHead(){
        return _inicio;
    }
    
    /**
     * metodo para obtener la cola de la lista.
     * @return 
     */
    public SuperNodeMemory getTail(){
        return _fin;
    }
}