/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Juan Manuel
 */
public class ABM {
    
    
    public String altaUsuario(Usuario user) throws NoSuchAlgorithmException {
        
        MiCriptografia mc = new MiCriptografia();
        byte[] hashpass = mc.hashSHA1(user.getPass());
        String hashfinal = mc.hashString(hashpass);
        String sentenciaSQL = new String();
        sentenciaSQL = "INSERT INTO seguridad.usuarios(usuario,password)"; // Cambiar por la nueva BD
        sentenciaSQL = sentenciaSQL + " VALUES ('" + user.getNombre() + "','" + hashfinal + "');";
        System.out.println("Se creo la sentencia.");

        return sentenciaSQL;

    }
    
    static public ResultSet buscarUsuarios() {
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = sentenciaSQL + "SELECT * FROM seguridad.usuarios"; // Cambiar por la BD obligatorio
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        }
        catch (Exception ex) {
            System.out.println("Error");
            return null;
        }
    }
    
    public ResultSet encontrarUsuario(String nombre)
    {
        
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = sentenciaSQL = sentenciaSQL + "SELECT password FROM seguridad.usuarios WHERE usuario='"+nombre+"';";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        }
        catch (Exception ex) {
            System.out.println("Error");
            return null;
        }
        
    }
    public void llenarTablaInicialUsuarios(ArrayList users) 
    {
        ResultSet rs = ABM.buscarUsuarios();
        
        try {
            if (rs != null) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNombre(rs.getString(1));
                    usuario.setPassword(rs.getString(2));
                    users.add(usuario);
                }
            }            
        }
        catch (Exception ex) { System.out.println("Error en llenado."); }
        Conector.cerrarConexion();
    }

}
