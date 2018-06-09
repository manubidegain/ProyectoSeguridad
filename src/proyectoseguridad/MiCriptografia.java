/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static proyectoseguridad.ProyectoSeguridad.bytesPrueba;

/**
 *
 * @author Juan Manuel
 */
public class MiCriptografia {
    
    // Metodo que Hashea mi password en SHA1 codificacion UTF-8 , estos parametros se utilizan para la API de iHaveBeenPwned
    public byte[] hashSHA1 (String password) throws NoSuchAlgorithmException
    {
        byte[] aux;
        byte[] pronto;
        try {
             aux = password.getBytes( "UTF-8" );
            } 
        catch (UnsupportedEncodingException e) {
                aux = password.getBytes();
            }
        MessageDigest messageD = MessageDigest.getInstance("SHA-1");
        messageD.update(aux);
        pronto = messageD.digest();
        return pronto;
    
        
        
    }
    
    public byte[] hashSHA256 (String password) throws NoSuchAlgorithmException
    {
        byte[] aux;
        byte[] pronto;
        try {
             aux = password.getBytes( "UTF-8" );
            } 
        catch (UnsupportedEncodingException e) {
                aux = password.getBytes();
            }
        MessageDigest messageD = MessageDigest.getInstance("SHA-256");
        messageD.update(aux);
        pronto = messageD.digest();
        return pronto;
    
        
        
    }
    
    
    
    // Metodo que pasa mi Hash a un String leible
    public String hashString (byte[] bytes)
    {
        StringBuilder passS = new StringBuilder();
        for (byte b : bytes) {
                passS.append(String.format("%02x", b));
        }
        return passS.toString();
    
    }
    // Metodo que me permite obtener el sufijo dentro de mi Hash leible
    public String sufijoHash (String aux) 
    {
        String finalSufijo = aux.substring(5);
        return finalSufijo;
    }
    // Metodo que me permite obtener el prefijo dentro de mi Hash leible
    public String prefijoHash (String aux) 
    {
        String finalPrefijo = aux.substring(0, 5);
        return finalPrefijo;
    }
    
    public void hashearPassSHA1 (Usuario user) throws NoSuchAlgorithmException 
    {
        byte[] hashpass = hashSHA1(user.getPass());
        String hashfinal = hashString(hashpass);
        user.setPassword(hashfinal);
    
    }
    
    
    public Boolean autenticacion (Usuario x) throws SQLException, NoSuchAlgorithmException
    {
        ABM abm = new ABM();
        this.hashearPassSHA1(x);
        ResultSet rs = abm.encontrarUsuario(x.getNombre(),x.getPass());
        if(rs != null)
        {
            if(rs.next()){
        
                if(rs.getString(1).equals("1") )
                {
                    return true;
                }
                else return false;
            
            }
            else return false;
        
        }
        
        else return false;
        
        
        
    }
    
    
       
    
}
