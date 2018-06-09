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
    private String password;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
    
    Usuario ()
    {
        this.nombre=null;
        this.password=null;
    
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

    
    public String toString()
    {
        return ("Usuario: " + this.getNombre() +" Password Hasheada: "+ this.getPass());
    }
    
    
}
