/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author William Morales Varela.
 */
public class MySql {

    public MySql() {
         // Se mete todo en un try por los posibles errores de MySQL
        try {
             // Se registra el Driver de MySQL
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             // Se obtiene una conexión con la base de datos.
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ingresoequipos", "root", "root");
            
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            //Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            
            ResultSet rs = s.executeQuery ("select * from marcas");
            while (rs.next())
            {
                System.out.println (rs.getInt (1) + " " + rs.getString (2));
            }
            // Se cierra la conexión con la base de datos.
            conexion.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) 
    {
        new MySql();
    }
}
