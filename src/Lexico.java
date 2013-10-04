
import Archivos.TablaContracciones;
import DB.BD;
import java.sql.ResultSet;
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

    String salida = "";
    TablaContracciones tabla = new TablaContracciones();
    ///
    BD mundo = new BD();

    public String getSalidas() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    /**
     * Metodo que valida el vocabulario de la cadena enviada, la recorre y
     * asigna a la variable salida los posibles errores durante el reccorido.
     *
     * @param cadena Cadena en la que se quiere ferificar el vocabulario
     * @return Retorna True si durante el recorrido de la cadena se ha
     * presentado algún error de vocabulario, de lo contrario False.
     */
    boolean vocabulario(String cadena) {
        String vocalesTilde = "áéíóú";
        boolean error = false;
        if (cadena.length() == 0) {
            salida += "Por favor digite una cadena para proceder con la compilación.";
            error = true;
        } else {
            for (int i = 0; i < cadena.length(); i++) {
                if (!Character.isLetter(cadena.charAt(i)) && !Character.isSpace(cadena.charAt(i))) {
                    salida += "El carácter: " + cadena.charAt(i) + " no es un carácter válido." + "\n";
                    error = true;
                }
                if (vocalesTilde.indexOf(cadena.charAt(i)) != -1) {
                    salida += "El carácter: " + cadena.charAt(i) + " no es un carácter válido." + "\n";
                    error = true;
                }
            }
        }
        if (!error) {
            salida += "No se han presentado errores en el proceso de validación del vocabulario." + "\n";
        }
        return error;
    }

    /**
     * Método que convierte la cadena enviada en un arreglo de palabras, se debe
     * tener en cuenta que utiliza los espacios para delimitar las palabras.
     *
     * @param cadena Cadena que quiere ser convertida a un arreglo de palabras.
     * @return Retorna un arreglo de palabras.
     */
    String[] arregloPalabras(String cadena) {
        String arreglo[] = cadena.split(" ");
        return arreglo;
    }

    /**
     * Método que identifica que letras son Vocales o Consonates en una palabra.
     *
     * @param palabra Palabra en la cual se quiere evaluar que letras son
     * Consonates o Vocales
     * @return Retorna una palabra donde se identifica morfologicamente que son
     * Consonates y que son Vocales, Ejemplo: caminar ==> cvcvcvc
     */
    String arregloEstadosPalabra(String palabra) {
        String vocales = "aeiou";
        String tmp = "";
        for (int i = 0; i < palabra.length(); i++) {
            if (vocales.indexOf(palabra.charAt(i)) == -1) {
                tmp = tmp + "c";
            } else {
                tmp = tmp + "v";
            }
        }
        return tmp;
    }

    /**
     * Método encargado de validar si las contracciones de una palabra son
     * contracciones válidas.
     *
     * @param arregloPalabras Arreglo que contiene todas las palabras que se
     * desean verificar.
     * @param arregloEstadosPalabra Arreglo que contiene el estado de todas la
     * palabra para realizar la validación
     * @return En caso que durante el proceso de validación de contracciones
     * resulte alguna invalidad se retornará True, de lo contrario False.
     */
    boolean contraccion(String arregloPalabras[], String arregloEstadosPalabra[]) {
        boolean error = false;
        boolean hayContraccion = false;

        for (int e = 0; e < arregloPalabras.length; e++) {
            String palabra = arregloPalabras[e];
            String estadosPalabra = arregloEstadosPalabra[e];
            for (int i = 0; i < palabra.length(); i++) {
                //Se validan los casos en contracciones tipo ccc ó vvv
                if (i != palabra.length() - 1) {
                    if (estadosPalabra.charAt(i) == estadosPalabra.charAt(i + 1)) {
                        if (estadosPalabra.charAt(i) == estadosPalabra.charAt(i + 2)) {
                            //Aqui se detecto una contracción ccc ó vvv
                            hayContraccion = true;
                            String contraccion = Character.toString(palabra.charAt(i)) + Character.toString(palabra.charAt(i + 1));
                            if (tabla.existeContraccion(contraccion)) {                                // Se verifica la existencia de la contracción detectada.
                                contraccion = tabla.resultadoContraccion(contraccion) + Character.toString(palabra.charAt(i + 2));
                                if (!tabla.existeContraccion(contraccion)) {
                                    error = true;
                                    salida += "La contracción: " + contraccion + " no es una contracción válida" + "\n";
                                }
                            } else {
                                error = true;
                                salida += "La contracción: " + contraccion + " no es una contracción válida" + "\n";
                            }
                            i++;
                        } else {
                            hayContraccion = true;
                            String contraccion = Character.toString(palabra.charAt(i)) + Character.toString(palabra.charAt(i + 1));
                            if (tabla.existeContraccion(contraccion)) {
                            } else {
                                error = true;
                                salida += "La contracción: " + contraccion + " no es una contracción válida" + "\n";
                            }
                        }
                    } else {
                        hayContraccion = false;
                    }
                }
            }
        }
        if (!error) {
            salida += "No se han presentado errores en el proceso de validación mofologica de la palabara >> Contracciones" + "\n";
        }
        return error;
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

    //
    public boolean validar(String arregloPalabras[]) {
        boolean error = false;

        for (int i = 0; i < arregloPalabras.length; i++) {
            
            
                if (mundo.consulta(arregloPalabras[i])) {
                    error = true;
                    salida += "La palabra: " + arregloPalabras[i] + " no es una palabra del mundo." + "\n";
                }
            
        }


        if (!error) {
            salida += "No se han presentado errores en el proceso de validación mofologica de la palabara >> Mundo Lexico" + "\n";
        }
        return error;

    }
    
       public void st(String arregloPalabras[]) {
        

        for (int i = 0; i < arregloPalabras.length; i++) {
            
            salida += "la palabra: "+arregloPalabras[i]+" se compone de: "+mundo.clasificacion(arregloPalabras[i]);
            
            
        }


        
    }
    
}
