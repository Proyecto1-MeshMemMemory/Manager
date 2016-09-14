/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import javax.swing.JOptionPane;

/**
 * Clase Main para llevar a cabo las pruebas
 * @author betolan
 */
public class Main{
    public static void main(String[] args){
        LinkedList lista = new LinkedList();
        
        lista.addNode(1, 40, 11150, 197000, 53253259, 5000, 1000);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5010, 1000);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5020, 1000);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5000, 1000);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5000, 1000);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5020, 1000);
        lista.addNode(2, 40, 11150, 197000, 53253259, 5020, 1000);

        
        
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
    /*
public static void main(String[] args) {
        LinkedList lista = new LinkedList();
        int _opcion = 0;
        do {
            try{
                _opcion = Integer.parseInt(JOptionPane.showInputDialog(null, 
                        "1. Agregar nodo al inicio \n" 
                                + "2. Agregar nodo al final \n" 
                                + "3. Mostrar la lista \n"
                                + "4. Eliminar nodo \n"
                                + "5. Salir \n" 
                                + "Elija una opción", "Opciones", 
                                JOptionPane.INFORMATION_MESSAGE));
                
                switch(_opcion){
                    case 1: 
                        //_elemento = Integer.parseInt(JOptionPane.showInputDialog(null, 
                               // "Ingresar elemento", "Agregar nodo al inicio", 
                             //   JOptionPane.INFORMATION_MESSAGE));
                        lista.agregarAlInicio(lista.setId());
                        break;
                    
                    case 2: 
                        //_elemento = Integer.parseInt(JOptionPane.showInputDialog(null, 
                             //   "Ingresar elemento", "Agregar nodo al final", 
                              //  JOptionPane.INFORMATION_MESSAGE));
                        lista.agregarAlFinal(lista.setId());
                        
                        break;
                
                    case 3: 
                        if(!lista.verificarLista()){
                            lista.mostrarLista();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, 
                            "No hay nodos", "Lista vacía", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                                             
                    case 4:
                        if(!lista.verificarLista()){
                            int _elemento = Integer.parseInt(JOptionPane.showInputDialog(null, 
                                "Ingresar elemento a eliminar", "Eliminar", 
                                JOptionPane.INFORMATION_MESSAGE));
                            lista.eliminar(_elemento);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, 
                            "No hay nodos", "Lista vacía", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                        
                    case 5: 
                        JOptionPane.showMessageDialog(null, 
                            "Finalizado","Finalizado", JOptionPane.INFORMATION_MESSAGE);
                        break;
                        
                    default:
                            JOptionPane.showMessageDialog(null, 
                            "Opción incorrecta","Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Error"+ n.getMessage());
            }

        }while (_opcion != 5);

    }
}*/