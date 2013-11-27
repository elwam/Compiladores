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

    public boolean pragmatica(String sustantivo_1, String sustantivo_2, String preposicion) {
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
            rs = s.executeQuery("select sustantivo_1, sustantivo_2, verbo, preposicion from clasificacion_pragmatica where sustantivo_1 = '" + sustantivo_1 + "' and sustantivo_2 ='" + sustantivo_2 + "' and preposicion = '" + preposicion + "'");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                // p = "raiz: " + rs.getString("ds_raiz") + " terminacion: " + rs.getString("ds_terminacion") + " y es un: " + rs.getString("ds_naturaleza");
                p = rs.getString("preposicion");
            }

            if (p.equals("N")) {
                error = true;
            }
            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return error;
    }

    public String pragmatica2(String sustantivo_1, String sustantivo_2) {

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
            rs = s.executeQuery("select sustantivo_1, sustantivo_2, verbo, preposicion from clasificacion_pragmatica where sustantivo_1 = '" + sustantivo_1 + "' and sustantivo_2 = '" + sustantivo_2 + "'");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                // p = "raiz: " + rs.getString("ds_raiz") + " terminacion: " + rs.getString("ds_terminacion") + " y es un: " + rs.getString("ds_naturaleza");
                p = rs.getString("preposicion");
            }

            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public String consultaP(String verbo, String preposicion, String sustantivo2) {
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

            rs = s.executeQuery("select ds_raiz from (select concat_ws('',ds_raiz,ds_terminacion) palabra, ds_raiz from db_compiladores.clasificacion_raiz t1 inner join db_compiladores.raiz t2 on (t1.id_raiz = t2.id_raiz)) t3 where t3.palabra = '" + verbo + "'");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.

            while (rs.next()) {
                p = rs.getString("ds_raiz");
            }

            if (!p.equals("N")) {
                rs = s.executeQuery("select sustantivo_1 from db_compiladores.clasificacion_pragmatica where sustantivo_2 = '" + sustantivo2 + "' and verbo ='" + p + "' and preposicion ='" + preposicion + "'");

                while (rs.next()) {
                    p = rs.getString("sustantivo_1");
                }

                /*if (!p.equals("ERROR1")) {
                 rs = s.executeQuery("select tipo from db_compiladores.clasificacion_sustantivos where sustantivo ='" + p + "'");

                 while (rs.next()) {
                 p = rs.getString("tipo");
                 }


                 } else {
                 p = "ERROR2";
                 }*/


            } else {
                p = "ERROR1";
            }

            // Se cierra la conexión con la base de datos.
            conexion.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;

    }

    public String consultaTipo(String sustantivo) {
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

            rs = s.executeQuery("select tipo from db_compiladores.clasificacion_sustantivos where sustantivo = '" + sustantivo + "'");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.

            while (rs.next()) {
                p = rs.getString("tipo");
            }

            // Se cierra la conexión con la base de datos.
            conexion.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;

    }
}
