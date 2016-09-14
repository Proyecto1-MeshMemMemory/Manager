/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 * Clase Lista, la estructura de datos para el control de tokens
 * @author betolan
 */
public class List{
    // Puntero que indica el inicio de la lista o conocida tambien
    // como cabeza de la lista.
    private ListNode _inicio;
    // Variable para registrar el tamaño de la lista.
    private int _size;
    /**
     * Constructor con un tamanio e inicio determinado.
     */
    public void Lista(){
        _inicio = null;
        _size = 0;
    }
    /**
     * Metodo tipo booleano que consulta si la lista esta vacia.
     * @return true si el primer nodo (inicio), no apunta a otro nodo.
     */
    public boolean Status(){
        return _inicio == null;
    }
    /**
     * Metodo tipo int que consulta cuantos tokens (nodos) tiene la lista.
     * @return numero entero entre donde n es el numero de elementos
     * que contenga la lista.
     */
    public int getSize(){
        return _size;
    }
    /**
     * Metodo tipo void que agrega un nuevo nodo al final de la lista.
     * @param pValue a agregar.
     */
    public void addFinal(long pValue){
        // Define un nuevo nodo.
        ListNode nuevo = new ListNode();
        // Agrega al valor al nodo.
        nuevo.setValue(pValue);
        // Consulta si la lista esta vacia.
        if (Status()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            _inicio = nuevo;
        // Caso contrario recorre la lista hasta llegar al ultimo nodo
        // y agrega el nuevo.
        } 
        else{
            // Crea ua copia de la lista.
            ListNode aux = _inicio;
            // Recorre la lista hasta llegar al ultimo nodo.
            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            // Agrega el nuevo nodo al final de la lista.
            aux.setNext(nuevo);
        }
        _size++;
    }
    /**
     * Metodo tipo void que agrega un nuevo nodo al inicio de la lista.
     * @param pValue a agregar.
     */   
    public void addBegin(long pValue){
        // Define un nuevo nodo.
        ListNode nuevo = new ListNode();
        // Agrega al valor al nodo.
        nuevo.setValue(pValue);
        // Consulta si la lista esta vacia.
        if (Status()){
            // Inicializa la lista agregando como inicio al nuevo nodo.
            _inicio = nuevo;
        // Caso contrario va agregando los nodos al inicio de la lista.
        } 
        else{
            // Une el nuevo nodo con la lista existente.
            nuevo.setNext(_inicio);
            // Renombra al nuevo nodo como el inicio de la lista.
            _inicio = nuevo;
        }
        _size++;
    }
    /**
     * Metodo tipo void que inserta un nuevo nodo despues en una posición determinada.
     * @param pPos en la cual se va a insertar el nuevo nodo.
     * @param pValue valor del nuevo nodo de la lista.
     */
    public void addWherever(int pPos, int pValue){
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y <= que el numero de elementos del la lista.
        if(pPos >= 0 && pPos <= _size){
            ListNode nuevo = new ListNode();
            nuevo.setValue(pValue);
            // Consulta si el nuevo nodo a ingresar va al inicio de la lista.
            if(pPos == 0){
                // Inserta el nuevo nodo al inicio de la lista.
                nuevo.setNext(_inicio);
                _inicio = nuevo;
            }
            else{
                // Si el nodo a inserta va al final de la lista.
                if(pPos == _size){
                    ListNode aux = _inicio;
                    // Recorre la lista hasta llegar al ultimo nodo.
                    while(aux.getNext() != null){
                        aux = aux.getNext();
                    }
                    // Inserta el nuevo nodo despues de del ultimo.
                    aux.setNext(nuevo);              
                }
                else{
                    // Si el nodo a insertar va en el medio de la lista.
                    ListNode aux = _inicio;
                    // Recorre la lista hasta llegar al nodo anterior
                    // a la posicion en la cual se insertara el nuevo nodo.
                    for (int i = 0; i < (pPos-1); i++){
                        aux = aux.getNext();
                    }
                    // Guarda el nodo siguiente al nodo en la posición
                    // en la cual va a insertar el nevo nodo.
                    ListNode siguiente = aux.getNext();
                    // Inserta el nuevo nodo en la posición indicada.
                    aux.setNext(nuevo);
                    // Une el nuevo nodo con el resto de la lista.
                    nuevo.setNext(siguiente);
                }
            }
            _size++;
        }
    }
    /**
     * Metodo tipo long que obtiene el valor de un nodo en una determinada posición.
     * @param posicion del nodo que se desea obtener su valor.
     * @return un numero entero entre [0,n-1] n = numero de nodos de la lista.
     * @throws Exception
     */
    public long getValue(int posicion) throws Exception{
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y < que el numero de elementos del la lista.
        if(posicion >= 0 && posicion < _size){
            // Consulta si la posicion es el inicio de la lista.
            if(posicion == 0){
                // Retorna el valor del inicio de la lista.
                return _inicio.getValue();
            }
            else{
                // Crea una copia de la lista.
                ListNode aux = _inicio;
                // Recorre la lista hasta la posición ingresada.
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getNext();
                }
                // Retorna el valor del nodo.
                return aux.getValue();
            }
        // Crea una excepción de Posicion inexistente en la lista.
        } 
        else{
            throw new Exception("Posicion inexistente en la lista.");
        }
    }
    /**
     * Metodo tipo void que elimina un nodo que se encuentre en la lista ubicado
     * por su posición.
     * @param posicion en la cual se encuentra el nodo a eliminar.
     */
    public void Delete(int posicion){
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y < que el numero de elementos del la lista.
        if(posicion >= 0 && posicion < _size){
            // Consulta si el nodo a eliminar es el primero
            if(posicion == 0){
                // Elimina el primer nodo apuntando al siguinte.
                _inicio = _inicio.getNext();
            }
            // En caso que el nodo a eliminar este por el medio 
            // o sea el ultimo
            else{
                // Crea una copia de la lista.
                ListNode aux = _inicio;
                // Recorre la lista hasta lleger al nodo anterior al eliminar.
                for(int i = 0; i < posicion-1; i++){
                    aux = aux.getNext();
                }
                // Guarda el nodo siguiente al nodo a eliminar.
                ListNode siguiente = aux.getNext();
                // Elimina la referencia del nodo apuntando al nodo siguiente.
                aux.setNext(siguiente.getNext());
            }
            _size--;
        }
    }
    /**
     * Metodo tipo void que elimina la lista.
     */
    public void Empty(){
        // Elimina el valor y la referencia a los demas nodos.
        _inicio = null;
        // Reinicia el contador de tamaño de la lista a 0.
        _size = 0;
    }
    /**
     * Metodo tipo void que muestra en pantalla los elementos de la lista.
     */
    public void Display(){
        // Verifica si la lista contiene elementos.
        if (!Status()) {
            // Crea una copia de la lista.
            ListNode aux = _inicio;
            // Posicion de los elementos de la lista.
            int i = 0;
            // Recorre la lista hasta el final.
            while(aux != null){
                // Imprime en pantalla el valor del nodo.
                System.out.print(i + ".[ " + aux.getValue() + " ]" + " ->  ");
                // Avanza al siguiente nodo.
                aux = aux.getNext();
                i++;
            }
        }
    }
    /**
     * Metodo tipo void que recorre la lista elminando nodos 
     * con un tiempo limite.
     */
    public void nodeExpired(){
        // Verifica si la lista contiene elementos.
        if (!Status()) {
            // Crea una copia de la lista.
            ListNode aux = _inicio;
            // Posicion de los elementos de la lista.
            int i = 0;
            // Recorre la lista hasta el final.
            while((_size > 0) && (aux != null)){
                // Verifica si el tiempo de existencia del nodo 
                // ha superado el deseado.
                if(System.currentTimeMillis()-aux.getValue() >= 4){
                    // Elimina el nodo caducado
                    this.Delete(i);
                }
                else{
                    // Avanza al siguiente nodo.
                    aux = aux.getNext();;
                    i++; 
                }   
            }
        }
    }
}