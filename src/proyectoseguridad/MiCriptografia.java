/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.postgresql.util.Base64;
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
    
     public void hashearPassSHA256 (Usuario user) throws NoSuchAlgorithmException 
    {
        byte[] hashpass = hashSHA256(user.getPass());
        String hashfinal = hashString(hashpass);
        user.setPassword(hashfinal);
    
    }
    
    
    public Boolean autenticacion (Usuario x) throws SQLException, NoSuchAlgorithmException
    {
        ABM abm = new ABM();
        this.hashearPassSHA256(x);
        ResultSet rs = abm.encontrarUsuario(x.getCedula(),x.getPass());
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
    
    
    public String cifra(String sinCifrar, SecretKeySpec key) throws Exception {
	byte[] bytes = sinCifrar.getBytes("UTF-8");
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aes.init(Cipher.ENCRYPT_MODE, key);
	byte[] cifrado = aes.doFinal(bytes);
        String textoCifrado; 
        textoCifrado = Base64.encodeBytes(cifrado);
	return textoCifrado;
}
 
    
    public SecretKeySpec crearLlave (String frase) throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	digest.update(frase.getBytes("UTF-8"));
        SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
        return key;
    
    }
            
            
    public String descifra(String textoCifrado, SecretKeySpec key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        
	Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aes.init(Cipher.DECRYPT_MODE, key);
        byte[] cifrado = Base64.decode(textoCifrado);
	byte[] bytes = aes.doFinal(cifrado);
	String sinCifrar = new String(bytes);
	return sinCifrar;

    }
    
    
    
    
       
    
}
