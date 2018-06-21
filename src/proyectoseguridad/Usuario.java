/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Juan Manuel
 */
public class Usuario {
    
    private String nombre;
    private String apellido;
    private String password;
    private String cedula;

    public Usuario(String nombre, String apellido, String login, String password) {
        this.nombre = nombre;
        this.password = password;
        this.cedula = login;
        this.apellido = apellido;
    }
    
    Usuario(String cedula, String password) {    
         this.cedula = cedula;
         this.password = password;
    }
    Usuario(){}
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getPass() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString()
    {
        return (this.getNombre());
    }
    
    
}
