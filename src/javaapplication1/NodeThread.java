/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 * Clase NodeThread encargada del hilo para 
 * eliminar los tokens con un tiempo de caducidad.
 * @author betolan
 */
public class NodeThread extends Thread{
    List _time;
    /**
     * Constructor donde recibe una lista a controlar el tiempo de sus nodos.
     * @param pController 
     */
    public  NodeThread(List pController){
        _time = pController;
    }
    /**
     * Metodo tipo void para enviar el hilo y llamar 
     * la funcion que elimina los nodos.
     */
    public void run(){  
        for(int i = 0; i < 4; i++){
            _time.Display();
            _time.nodeExpired();
            // Pausar el hilo n segundos.
            this.Wait(4);  
        }     
    }
    /**
     * Metodo tipo void para detener el hilo un cierto tiempo 
     * para ser ejecutado nuevamente.
     * @param pSeconds segundos para detener.
     */
    private void Wait(int pSeconds){
        try{
            Thread.sleep(pSeconds * 1000);
        }
        catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }  
}