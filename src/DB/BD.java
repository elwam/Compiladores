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
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/db_compiladores", "root", "root");

            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el 
            // ResultSet rs
            // rs = s.executeQuery("select * from (select t1.raiz, concat_ws('',t2.raiz,t2.terminacion) as palabra from compiladores.raiz t1 inner join compiladores.clasificacion_raiz t2 on (t1.raiz = t2.raiz)) t1 where t1.palabra ='" + palabra + "'");

            rs = s.executeQuery("select palabra from(select concat_ws('',t1.ds_raiz,t2.ds_terminacion) as palabra from raiz t1 inner join clasificacion_raiz t2 on (t1.id_raiz = t2.id_raiz)) tabla where tabla.palabra ='" + palabra + "'");

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
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/db_compiladores", "root", "root");

            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el 
            // ResultSet rs
            rs = s.executeQuery("select palabra, ds_raiz, ds_terminacion, ds_naturaleza from(select t1.ds_raiz, case when t2.ds_terminacion = '' then 'Sin terminacion' else t2.ds_terminacion end ds_terminacion, t3.ds_naturaleza, concat_ws('',t1.ds_raiz,t2.ds_terminacion) as palabra from raiz t1 inner join clasificacion_raiz t2 on (t1.id_raiz = t2.id_raiz) inner join naturaleza t3 on (t1.id_naturaleza = t3.id_naturaleza)) tabla where tabla.palabra = '" + palabra + "'");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                p = "raiz--> " + rs.getString("ds_raiz") + ", terminacion--> " + rs.getString("ds_terminacion") + " y es un--> " + rs.getString("ds_naturaleza");
            }

            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public String detalles(String palabra) {

        ResultSet rs = null;
        String p = "N";

        try {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            // Se obtiene una conexión con la base de datos. Hay que
            // cambiar el usuario "root" y la clave "la_clave" por las
            // adecuadas a la base de datos que estemos usando.
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/db_compiladores", "root", "root");

            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el 
            // ResultSet rs
            rs = s.executeQuery("select palabra, ds_genero, ds_numero, ds_naturaleza from(select t4.ds_genero, t5.ds_numero, t3.ds_naturaleza, concat_ws('',t1.ds_raiz,t2.ds_terminacion) as palabra from raiz t1 inner join clasificacion_raiz t2 on (t1.id_raiz = t2.id_raiz) inner join naturaleza t3 on (t1.id_naturaleza = t3.id_naturaleza) inner join genero t4 on (t2.id_genero = t4.id_genero) inner join numero t5 on (t2.id_numero = t5.id_numero)) tabla where tabla.palabra = '" + palabra + "'");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                // p = "raiz: " + rs.getString("ds_raiz") + " terminacion: " + rs.getString("ds_terminacion") + " y es un: " + rs.getString("ds_naturaleza");
                p = rs.getString("palabra") + " " + rs.getString("ds_genero") + " " + rs.getString("ds_numero") + " " + rs.getString("ds_naturaleza");
            }

            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
