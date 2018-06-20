/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 *
 * @author rafaelpelacchi
 */
public class ManejoArchivos {
    public static void escribirArchivo(String path, byte[] key) throws IOException {
		File archivo = new File(path);
		archivo.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(archivo);
		fos.write(key);
		fos.flush();
		fos.close();
	}
    
    public static boolean generarLlaves(String path){
        GenerarClaves gc;
        try {
                gc = new GenerarClaves(1024);
                gc.createKeys();
                escribirArchivo(path + getBarra() + "publicKey", gc.getPublicKey().getEncoded());
                escribirArchivo(path + getBarra() + "privateKey", gc.getPrivateKey().getEncoded());

                return true;
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
                return false;
        } catch (IOException e) {
                return false;
        }
    }
    
    public static String obtenerRutaNueva(String dire,String prefijo)
    {

        String largonombre[] = dire.split(getBarra());
        String retorno = dire.substring(0,dire.length() - largonombre[largonombre.length -1].length());
        return retorno + prefijo +"-" + largonombre[largonombre.length-1];
        
    }
    public static String obtenerRutaNueva(String dire)
    {

        return dire.replace("cifrado","descifrado");
        
    }
    
    public static String getBarra(){
        switch(System.getProperty("os.name")){
            case "Linux": return "/";
            case "Windows 8.1" : return "\\\\";
            case "Windows" : return "\\\\";
            default: return "/";
        }
    }
}
