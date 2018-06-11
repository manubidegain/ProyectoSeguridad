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
    private String login;

    public Usuario(String nombre, String password, String login, String apellido) {
        this.nombre = nombre;
        this.password = password;
        this.login = login;
        this.apellido = apellido;
    }
    
    Usuario ()
    {
        this.nombre=null;
        this.password=null;
    
    }

    Usuario(String usuario, String password) {    
         this.nombre = usuario;
         this.password = password;
    }
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
    
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String toString()
    {
        return ("Usuario: " + this.getNombre() +" Password Hasheada: "+ this.getPass());
    }
    
    
}
