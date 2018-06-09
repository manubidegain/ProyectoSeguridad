/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

/**
 *
 * @author Juan Manuel
 */
import java.sql.*;

public class Conector {

    public static Connection conexion = null;
    public static Statement sentencia = null;
    public static ResultSet resultado = null;

    public static void cerrarConexion() {
        conexion = null;
        if (resultado != null) {
            try {
                resultado.close();
            } catch (Exception ex) {
                System.out.println("Error al cerrar conexión");
            }
        }
        if (sentencia != null) {
            try {
                sentencia.close();
            } catch (Exception ex) {
                System.out.println("Error al cerrar conexión.");
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.out.println("Error al cerrar conexión.");
            }
        }

    }

    public static void abrirConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "a");
            sentencia = conexion.createStatement();
            System.out.println("Se realizo la conexion correctamente");
        } catch (Exception ex) {
            System.out.println("Error al abrir conexión.");
            throw new RuntimeException(ex);
        }
    }
    
}