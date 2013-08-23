/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.*;
import java.util.*;
import java.util.*;

/**
 *
 * @author William Alexander Morales
 */
public class TablaContracciones {

    Contraccion C = new Contraccion();
    Hashtable<String, Character> tContracciones = new Hashtable<String, Character>();

    /**
     * Método que verifica la existencia de una contracción dada.
     *
     * @param contraccion Contracción que se quiere verificar.
     * @return En caso de que la contracción exista retorna True, de lo
     * contrario retorna False.
     */
    public boolean existeContraccion(String contraccion) {
        return tContracciones.containsKey(contraccion);
    }

    /**
     * Método que retorna el resultado de una contracción.
     *
     * @param contraccion Contracción a la cual se busca su resultado.
     * @return Retorna el resultado de la contracción deseada.
     */
    public Character resultadoContraccion(String contraccion) {
        return tContracciones.get(contraccion);
    }

    /**
     * Método encargado de cargar el contenido del plano de contracciones en la
     * tabla de contracciones.
     *
     * @return En caso de presentarse algún error en el proceso de carga retorna
     * True, de lo contrario retorna False.
     */
    public boolean cargarTabla() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("contracciones.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String datos;
            while ((datos = br.readLine()) != null) {
                StringTokenizer registro = new StringTokenizer(datos, ";");
                StringTokenizer campo;
                campo = new StringTokenizer(registro.nextToken(), ",");
                C.contraccion = campo.nextToken();
                C.resultadoContraccion = campo.nextToken().charAt(0);
                tContracciones.put(C.contraccion, C.resultadoContraccion);
            }

        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                return false;
            }
        }
        return true;
    }

    public String imprimir(String contraccion) {
        String S = "";
        C.contraccion = contraccion;
        C.resultadoContraccion = tContracciones.get(contraccion);
        S = S + C.contraccion + " " + C.resultadoContraccion;
        return S;
    }

    /**
     * Método que consulta todas las llaves que se encuentran cargadas en la
     * tabla.
     *
     * @return Listado de llaves que existen en la tabla.
     */
    public Enumeration listaContracciones() {
        return tContracciones.keys();
    }
}
