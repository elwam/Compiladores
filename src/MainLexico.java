
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrador
 */
public class MainLexico {

    public static void main(String args[]) {
        String cadena, cadenaMinuscula;


        Lexico lexico = new Lexico();
        cadena = JOptionPane.showInputDialog("Digite la cadena: ");
        if (!lexico.validarCadena(cadena)) {
            if(!lexico.cargarContracciones()){
                System.out.println("Error cargando tabla de contracciones!!");
            }else{
            cadenaMinuscula = cadena.toLowerCase();
            String arregloPalabras[] = lexico.arregloPalabras(cadenaMinuscula);
            String arregloSilabas[] = new String[arregloPalabras.length];
            String arregloContracciones[] = new String[arregloPalabras.length];
            for (int i = 0; i < arregloPalabras.length; i++) {
                arregloSilabas[i] = lexico.arregloSilabas(arregloPalabras[i]);
            }

            for (int i = 0; i < arregloPalabras.length; i++) {
                arregloContracciones[i] = lexico.contraccion(arregloPalabras[i], arregloSilabas[i]);
            }
            for (int i = 0; i < arregloPalabras.length; i++) {
                System.out.println("Palabra: " + arregloPalabras[i] + " Silabas: " + arregloSilabas[i] + " Resultado:" + arregloContracciones[i]);
            }
            System.out.println("La cadena es: " + cadena);
            System.out.println("La cadena es: " + cadenaMinuscula);
        }
            
        }

    }
}
