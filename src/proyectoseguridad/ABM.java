/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static proyectoseguridad.ProyectoSeguridad.conector;

/**
 *
 * @author Juan Manuel
 */
public class ABM {
    
    
    public String altaUsuario(Usuario user) throws NoSuchAlgorithmException, SQLException {
        
        MiCriptografia mc = new MiCriptografia();
        byte[] hashpass = mc.hashSHA256(user.getPass());
        String hashfinal = mc.hashString(hashpass);
        String sentenciaSQL = new String();
        sentenciaSQL = "INSERT INTO seguridad.usuarios(cedula,password,nombre,apellido)"; // Cambiar por la nueva BD
        sentenciaSQL = sentenciaSQL + " VALUES ('"+ user.getCedula() + "','" + hashfinal + "','" + user.getNombre() + "','" + user.getApellido() +"');";
        conector.abrirConexion();
        Conector.sentencia.execute(sentenciaSQL);
        conector.cerrarConexion();
        return sentenciaSQL;

    }
    
    static public ResultSet buscarUsuarios() {
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = sentenciaSQL + "SELECT * FROM seguridad.usuarios";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        }
        catch (Exception ex) {
            System.out.println("Error");
            return null;
        }
    }
    
    public ResultSet encontrarUsuario(String cedula,String contrasena)
    {
        
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = sentenciaSQL = sentenciaSQL + "SELECT COUNT(*) FROM seguridad.usuarios WHERE cedula='"+cedula+"' AND password = '"+contrasena+"';";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        }
        catch (Exception ex) {
            System.out.println("Error");
            return null;
        }
        
    }
    
    public ResultSet encontrarUsuarioCedula(String cedula)
    {
        
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = sentenciaSQL = sentenciaSQL + "SELECT COUNT(*) FROM seguridad.usuarios WHERE cedula='"+cedula+"';";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        }
        catch (Exception ex) {
            System.out.println("Error");
            return null;
        }
        
    }
    static public void llenarTablaInicialUsuarios(ArrayList users) 
    {
        ResultSet rs = ABM.buscarUsuarios();
        
        try {
            if (rs != null) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNombre(rs.getString(1));
                    usuario.setPassword(rs.getString(2));
                    usuario.setCedula(rs.getString(3));
                    users.add(usuario);
                }
            }            
        }
        catch (Exception ex) { System.out.println("Error en llenado."); }
        Conector.cerrarConexion();
    }

}
