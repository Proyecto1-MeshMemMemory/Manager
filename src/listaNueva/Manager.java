/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaNueva;

/**
 *
 * @author Luis08
 */
import javax.swing.JOptionPane;


public class Manager {
    public static void main(String[] args) {
        // TODO code application logic here

        Lista lista = new Lista();
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
                        /*_elemento = Integer.parseInt(JOptionPane.showInputDialog(null, 
                                "Ingresar elemento", "Agregar nodo al inicio", 
                                JOptionPane.INFORMATION_MESSAGE));*/
                        lista.agregarAlInicio(lista.setId());
                        break;
                    
                    case 2: 
                        /*_elemento = Integer.parseInt(JOptionPane.showInputDialog(null, 
                                "Ingresar elemento", "Agregar nodo al final", 
                                JOptionPane.INFORMATION_MESSAGE));*/
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
}
