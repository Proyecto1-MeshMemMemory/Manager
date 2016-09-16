/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
/**
 * Clase Main para llevar a cabo las pruebas
 * @author betolan
 */
public class Main{
    public static void main(String[] args){
        LinkedList lista = new LinkedList();
        
        lista.addNode(1, 40, 11150, 197000, 53253259, 5000);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5010);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5020);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5000);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5000);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5020);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5020);
 
        lista.mostrarLista();        
    }
}
  /*  public static void main(String[] args) throws Exception{
        List lista = new List();
        
        System.out.println("<<-- Ejemplo de lista simple -->>\n");
        
        // Agregar al final de la lista
        lista.addFinal(System.currentTimeMillis());
        System.out.println("0");
        lista.addFinal(System.currentTimeMillis());
        System.out.println("1");
        lista.addFinal(System.currentTimeMillis());   
        System.out.println("2");
        lista.addFinal(System.currentTimeMillis());
        System.out.println("3");
        
        NodeThread first = new NodeThread(lista);
        first.start();
        System.out.println("4");
        
        Thread.sleep(9*1000);
        // Agregar in inicio de la lista
        lista.addBegin(System.currentTimeMillis());
        System.out.println("5");
        lista.addBegin(System.currentTimeMillis());
        System.out.println("6");
        
        //System.out.println("\n<<-- Lista -->>");
        //lista.listar();
        
        System.out.println("\n\n<<-- Tamaño -->");
        System.out.println(lista.getSize());
    }   
}
*/