
import Archivos.TablaContracciones;
import java.util.Enumeration;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrador
 */
public class Lexico {

    TablaContracciones tabla = new TablaContracciones();

    boolean validarCadena(String cadena) {
        String vocalesTilde = "áéíóú";
        boolean error = false;
        if (cadena.length() == 0) {
            System.out.println("La cadena esta vacia!!!");
            error = true;
        } else {
            for (int i = 0; i < cadena.length(); i++) {
                if (!Character.isLetter(cadena.charAt(i)) && !Character.isSpace(cadena.charAt(i))) {
                    System.out.println("El caracter: " + cadena.charAt(i) + " no es un caracter válido.");
                    error = true;
                }

                if (vocalesTilde.indexOf(cadena.charAt(i)) != -1) {
                    System.out.println("El caracter: " + cadena.charAt(i) + " no es un caracter válido.");
                    error = true;
                }


            }
        }
        return error;
    }

    String[] arregloPalabras(String cadena) {
        String arreglo[] = cadena.split(" ");
        return arreglo;
    }

    String arregloSilabas(String cadena) {
        String vocales = "aeiou";
        String tmp = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (vocales.indexOf(cadena.charAt(i)) == -1) {
                tmp = tmp + "c";
            } else {
                tmp = tmp + "v";
            }
        }
        return tmp;
    }

    String contraccion(String palabra, String estadosP) {

        String letraAnt;

        boolean hayContraccion = false;
        String Resultado = "";
        for (int i = 0; i < palabra.length(); i++) {
            if (i != palabra.length() - 1) {

                if (estadosP.charAt(i) == estadosP.charAt(i + 1)) {
                    if (estadosP.charAt(i) == estadosP.charAt(i + 2)) {
                        hayContraccion = true;
                        String contraccion = Character.toString(palabra.charAt(i)) + Character.toString(palabra.charAt(i + 1));
                        contraccion = tabla.resultadoContraccion(contraccion) + Character.toString(palabra.charAt(i + 2));
                        Resultado += tabla.resultadoContraccion(contraccion);
                        i++;
                    } else {
                        hayContraccion = true;
                        String contraccion = Character.toString(palabra.charAt(i)) + Character.toString(palabra.charAt(i + 1));
                        Resultado += tabla.resultadoContraccion(contraccion);
                    }

                } else {
                    if (hayContraccion) {
                        hayContraccion = false;
                        if (palabra.charAt(i) == palabra.charAt(i - 1)) {
                            Resultado += "";
                        }
                    } else {
                        Resultado += palabra.charAt(i);
                    }
                }
            } else {
                if (!hayContraccion) {
                    Resultado += palabra.charAt(i);
                }
            }

        }

        return Resultado;
    }

    boolean cargarContracciones() {
        return tabla.cargarTabla();
    }

    String Contraccion(String llave) {
        return tabla.imprimir(llave);
    }

    Enumeration listaContra() {
        return tabla.listaContracciones();
    }
}
