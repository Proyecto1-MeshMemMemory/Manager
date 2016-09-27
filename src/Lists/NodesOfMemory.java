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
 * clase para crear nodos para que trabajen con las listas
 * @author Ellioth
 */
public class NodesOfMemory  implements Constantes{
    /**
     * atributos de la clase padre nodo
     */
    
    private String _dato;
    private NodesOfMemory _NextNodesOfMemory;
    private int _refCounter;
    private int _space;
    private String _Id;
    /**
     * metodo para ingresar el _dato que 
     * se va a contener en el nodo creado
     * @param pData _dato tipo generico, es el contenido del _dato del nodo.
     * @param pSpace
     * @param pId
     */
    public NodesOfMemory(String pData, int pSpace, String pId){
        _dato= pData;
        _refCounter=UNO;
        _space=pSpace;
        _Id=pId;
    }
    
    /**
     * metodo para incrementar el conteo de referencias de la memoria
     * a la cual apunta el nodo.
     */
    public void increaseRefCounter(){
        _refCounter++;
    }
    
    /**
     * metodo para disminuir el conteo de referencias de la memoria
     * a la cual apunta el nodo.
     */
    public void decreaseRefCounter(){
        _refCounter--;
    }
    
    /**
     * metodo para obtener la cantidad de referencias de esa memoria.
     * @return entero que es la cantidad.
     */
    public int getRefCounter(){
        return _refCounter;
    }
    
    /**
     * devuelve el contenido del nodo
     * @return retorna un _dato generico, hay que hacer cast para obtener el 
     * _dato que se quiere, este es el _dato interno del nodo.
     */
    public String getData(){
        return  _dato;
    }
    
    /**
     * devuelve el nodo siguiente de 
     * el cual estamos trabajando
     * @return _dato de la clase NodesOfMemory, retorna el nodo siguiente del nodo con 
     * quien estamos operando.
     */
    public NodesOfMemory getNext(){
        return _NextNodesOfMemory;
    }
    
    /**
     * establece como parametro de entrada un nodo para colocarlo como siguiente
     * del cual nos encontramos.
     * @param pNodesOfMemory _dato de la clase NodesOfMemory, este va a ser el nuevo siguiente del
     * nodo con el cual operamos.
     */
    public void setNext(NodesOfMemory pNodesOfMemory){
        this._NextNodesOfMemory=pNodesOfMemory;
    }
    
    /**
     * metodo para obtener el lugar inicial de la memoria donde esta 
     * almacenado ese dato.
     * @return entero que es el dato.
     */
    public int getSpaceMemory(){
        return _space;
    }
    
    /**
     * metodo para obtener el tamaño del dato.
     * @return entero que es el dato.
     */
    public int getSize(){
        int returnedNumber=-1;
        try {
            JSONObject temp= new JSONObject(_dato);
            returnedNumber= temp.getInt(SIZE);
        } catch (JSONException ex) {
            cout("Error: extraccion del tamaño del dato del Json de uno de los "
                    + "nodos de memoria.");
        }
        return returnedNumber;
    }
    
    /**
     * metodo para obtener el ID del nodo.
     * @return retorna el entero del id del nodo.
     */
    public String getID(){
        return _Id;
    }
    
}
