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
 * @author Administrador
 */
public class TablaContracciones {

    Contraccion C = new Contraccion();
    Hashtable<String, Character> tContracciones = new Hashtable<String, Character>();

    public boolean existeContraccion(String contraccion) {
        return tContracciones.containsKey(contraccion);
    }

    public Character resultadoContraccion(String contraccion) {
        return tContracciones.get(contraccion);
    }
    
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
    
    public Enumeration listaContracciones(){
        return tContracciones.keys();
    }
}
