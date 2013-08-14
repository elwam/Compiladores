/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Maintabla {
     public static void main(String args[]) {
     TablaContracciones tabla = new TablaContracciones();
     if(!tabla.cargarTabla()){
         JOptionPane.showMessageDialog(null, "errorcargandotabla");
     }
     String contraccion = JOptionPane.showInputDialog("Contraccion a buscar:");
     if(tabla.existeContraccion(contraccion)){
         JOptionPane.showMessageDialog(null, tabla.imprimir(contraccion));
     }else
         JOptionPane.showMessageDialog(null, "Contraccion no existe!!");
     }
    
}
