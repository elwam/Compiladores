package Ayudas;


import java.io.*;
import java.util.*;
import javax.swing.*;

public class Empleados {

    public static void main(String[] args) {
        TablaEmpleado T = new TablaEmpleado();
        if (!T.bajarArchivo("empleados.txt")) {
            System.exit(1);
        }
        Empleado E = new Empleado();
        boolean salir = true;
        int opc;
        String S;
        Object[] Opcion = {"1. Adicion Empleado", "2. Edicion Empleado", "3. Consulta Empleado", "4. Listar Empleados", "5. Cierre"};
        do {
            S = (String) JOptionPane.showInputDialog(null, "Opciones: ", " Parqueadero Doris", JOptionPane.QUESTION_MESSAGE, null, Opcion, Opcion[0]);
            opc = Character.digit(S.charAt(0), 10);
            switch (opc) {
                case 1:
                    E.cedula = JOptionPane.showInputDialog(null, "Cedula");
                    E.nombre = JOptionPane.showInputDialog(null, "Nombre");
                    E.cargo = JOptionPane.showInputDialog(null, "Cargo");
                    E.sueldo = JOptionPane.showInputDialog(null, "Sueldo");
                    if (T.adicion(E)) {
                        System.out.println("Datos guardados con exito.");
                    } else {
                        System.out.println("No se pudo almacenar los datos");
                    }
                    break;
                case 2:
                    E.cedula = JOptionPane.showInputDialog(null, "Cedula");
                    if (T.existe(E.cedula)) {
                        T.editar(E.cedula);
                    } else {
                        System.out.println("No Existe Empleado");
                    }
                    break;
                case 3:
                    E.cedula = JOptionPane.showInputDialog(null, "Cedula");
                    if (T.existe(E.cedula)) {
                        JOptionPane.showMessageDialog(null, T.ver(E.cedula));
                    } else {
                        System.out.println("No Existe Empleado");
                    }
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, T.todos());
            }
        } while (opc != 5);
        T.grabar("empleados.txt");
    }
}

class Empleado {

    String cedula;
    String nombre;
    String sueldo;
    String cargo;
}

class TablaEmpleado {

    Empleado E = new Empleado();
    Hashtable<String, String> Nombres = new Hashtable<String, String>();
    Hashtable<String, String> Cargos = new Hashtable<String, String>();
    Hashtable<String, String> Sueldos = new Hashtable<String, String>();

    boolean bajarArchivo(String nomarch) {
        String Lee;
        File Fi = new File(nomarch);
        if (!Fi.exists()) {
            return false;
        } else {
            try {
                BufferedReader Entrada = new BufferedReader(new FileReader(Fi));
                Lee = Entrada.readLine();
                Entrada.close();
            } catch (Exception e) {
                return false;
            }
            StringTokenizer registro = new StringTokenizer(Lee, ";");
            StringTokenizer campo;
            while (registro.hasMoreTokens()) {
                campo = new StringTokenizer(registro.nextToken(), ",");
                E.cedula = campo.nextToken();
                E.nombre = campo.nextToken();
                E.cargo = campo.nextToken();
                E.sueldo = campo.nextToken();
                Nombres.put(E.cedula, E.nombre);
                Cargos.put(E.cedula, E.cargo);
                Sueldos.put(E.cedula, E.sueldo);
            }
        }
        return true;
    }

    boolean existe(String ced) {
        return Nombres.containsKey(ced);
    }

    boolean adicion(Empleado A) {
        if (existe(A.cedula)) {
            return false;
        }
        Nombres.put(A.cedula, A.nombre);
        Cargos.put(A.cedula, A.cargo);
        Sueldos.put(A.cedula, A.sueldo);
        return true;
    }

    void editar(String ced) {
        E.cedula = ced;
        E.nombre = Nombres.get(ced);
        E.cargo = Cargos.get(ced);
        E.sueldo = Sueldos.get(ced);
        E.nombre = JOptionPane.showInputDialog(null, "Nombre", E.nombre);
        E.cargo = JOptionPane.showInputDialog(null, "Cargo", E.cargo);
        E.sueldo = JOptionPane.showInputDialog(null, "Sueldo", E.sueldo);
        Nombres.put(E.cedula, E.nombre);
        Cargos.put(E.cedula, E.cargo);
        Sueldos.put(E.cedula, E.sueldo);
    }

    String ver(String ced) {
        String S = " =============== \n";
        E.cedula = ced;
        E.nombre = Nombres.get(ced);
        E.cargo = Cargos.get(ced);
        E.sueldo = Sueldos.get(ced);
        S = S + "Cedula:" + E.cedula + "\n";
        S = S + "Nombre:" + E.nombre + "\n";
        S = S + "Cargo:" + E.cargo + "\n";
        S = S + "Sueldo:" + E.sueldo + "\n";
        S = S + " =============== \n";
        return S;
    }

    String todos() {
        String T = " ";
        Enumeration lista = Nombres.keys();
        while (lista.hasMoreElements()) {
            E.cedula = (String) lista.nextElement();
            T = T + ver(E.cedula);
        }
        return T;
    }

    String registro(String ced) {
        String S = "";
        E.cedula = ced;
        E.nombre = Nombres.get(ced);
        E.cargo = Cargos.get(ced);
        E.sueldo = Sueldos.get(ced);
        S = S + E.cedula + ",";
        S = S + E.nombre + ",";
        S = S + E.cargo + ",";
        S = S + E.sueldo + ";";
        return S;
    }

    void grabar(String nomarch) {
        String T = "";
        Enumeration lista = Nombres.keys();
        while (lista.hasMoreElements()) {
            E.cedula = (String) lista.nextElement();
            T = T + registro(E.cedula);
        }
        try {
            FileWriter out = new FileWriter(nomarch);
            for (int k = 0; k < T.length(); k++) {
                out.write((byte) T.charAt(k));
            }
            out.close();
        } catch (Exception e) {
        }
    }
}