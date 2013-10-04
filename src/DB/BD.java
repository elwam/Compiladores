/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.*;

/**
 *
 * @author elwam
 */
public class BD {

    public BD() {
    }

    public boolean consulta(String palabra) {
        boolean error = false;
        ResultSet rs = null;
        String p = "N";

        try {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            // Se obtiene una conexión con la base de datos. Hay que
            // cambiar el usuario "root" y la clave "la_clave" por las
            // adecuadas a la base de datos que estemos usando.
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/compiladores", "root", "root");

            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el 
            // ResultSet rs
            rs = s.executeQuery("select * from (select t1.raiz, concat_ws('',t2.raiz,t2.terminacion) as palabra from compiladores.raiz t1 inner join compiladores.clasificacion_raiz t2 on (t1.raiz = t2.raiz)) t1 where t1.palabra ='" + palabra + "'");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.

            while (rs.next()) {
                p = rs.getString("palabra");
            }

            if (p.equals("N")) {
                error = true;
            }

            // Se cierra la conexión con la base de datos.
            conexion.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return error;

    }

    public String clasificacion(String palabra) {

        ResultSet rs = null;
        String p = "N";

        try {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            // Se obtiene una conexión con la base de datos. Hay que
            // cambiar el usuario "root" y la clave "la_clave" por las
            // adecuadas a la base de datos que estemos usando.
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/compiladores", "root", "root");

            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el 
            // ResultSet rs
            //rs = s.executeQuery("select * from (select t1.raiz, case when t1.naturaleza ='V' then 'Verbo' ELSE  'No_se' END naturaleza, concat_ws('',t2.raiz,t2.terminacion) palabra, t2.terminacion from compiladores.raiz t1 inner join compiladores.clasificacion_raiz t2 on (t1.raiz=t2.raiz)) t where t.palabra =" +palabra+ "'");
rs = s.executeQuery("select * from (select t1.raiz, concat_ws('',t2.raiz,t2.terminacion) as palabra, t2.terminacion, case when t1.naturaleza = 'V' then 'Verbo' else 'No se' end naturaleza from compiladores.raiz t1 inner join compiladores.clasificacion_raiz t2 on (t1.raiz = t2.raiz)) t1 where t1.palabra ='" + palabra + "'");
            // Se recorre el ResultSet, mostrando por pantalla los resultados.

            while (rs.next()) {
                p = "raiz: "+rs.getString("raiz")+" terminacion: "+ rs.getString("terminacion")+ " y es un: "+rs.getString("naturaleza");
            }

            //if (p.equals("N")) {

            //}

            // Se cierra la conexión con la base de datos.
            conexion.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
