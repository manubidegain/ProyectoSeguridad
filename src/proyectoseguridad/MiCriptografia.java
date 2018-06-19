
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.postgresql.util.Base64;
import static proyectoseguridad.MiCriptografia.getPublic;
import static proyectoseguridad.ProyectoSeguridad.bytesPrueba;

/**
 *
 * @author Juan Manuel
 */
public class MiCriptografia {

    // Metodo que Hashea mi password en SHA1 codificacion UTF-8 , estos parametros se utilizan para la API de iHaveBeenPwned
    public byte[] hashSHA1(String password) throws NoSuchAlgorithmException {
        byte[] aux;
        byte[] pronto;
        try {
            aux = password.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            aux = password.getBytes();
        }
        MessageDigest messageD = MessageDigest.getInstance("SHA-1");
        messageD.update(aux);
        pronto = messageD.digest();
        return pronto;

    }

    public byte[] hashSHA256(String password) throws NoSuchAlgorithmException {
        byte[] aux;
        byte[] pronto;
        try {
            aux = password.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            aux = password.getBytes();
        }
        MessageDigest messageD = MessageDigest.getInstance("SHA-256");
        messageD.update(aux);
        pronto = messageD.digest();
        return pronto;

    }

    // Metodo que pasa mi Hash a un String leible
    public String hashString(byte[] bytes) {
        StringBuilder passS = new StringBuilder();
        for (byte b : bytes) {
            passS.append(String.format("%02x", b));
        }
        return passS.toString();

    }

    // Metodo que me permite obtener el sufijo dentro de mi Hash leible
    public String sufijoHash(String aux) {
        String finalSufijo = aux.substring(5);
        return finalSufijo;
    }

    // Metodo que me permite obtener el prefijo dentro de mi Hash leible
    public String prefijoHash(String aux) {
        String finalPrefijo = aux.substring(0, 5);
        return finalPrefijo;
    }

    public void hashearPassSHA1(Usuario user) throws NoSuchAlgorithmException {
        byte[] hashpass = hashSHA1(user.getPass());
        String hashfinal = hashString(hashpass);
        user.setPassword(hashfinal);

    }
    
    public void hashearPassSHA256(Usuario user) throws NoSuchAlgorithmException {
        byte[] hashpass = hashSHA1(user.getPass());
        String hashfinal = hashString(hashpass);
        user.setPassword(hashfinal);

    }


    private static PrivateKey getPrivate(File file) throws Exception {
        byte[] keyBytes = Files.readAllBytes(file.toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("DSA", "SUN");
        return kf.generatePrivate(spec);
    }

    public static PublicKey getPublic(File file) throws Exception {
        byte[] keyBytes = Files.readAllBytes(file.toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("DSA", "SUN");
        return kf.generatePublic(spec);
    }

    public Boolean autenticacion(Usuario x) throws SQLException, NoSuchAlgorithmException {
        ABM abm = new ABM();
        this.hashearPassSHA256(x);// sha256
        ResultSet rs = abm.encontrarUsuario(x.getNombre(), x.getPass());
        if (rs != null) {
            if (rs.next()) {

                if (rs.getString(1).equals("1")) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public String cifra(String sinCifrar, SecretKeySpec key) throws Exception {

        sr.nextBytes(iv);
        byte[] bytes = sinCifrar.getBytes("UTF-8");
        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aes.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] cifrado = aes.doFinal(bytes);
        String textoCifrado;
        textoCifrado = Base64.encodeBytes(cifrado);
        return textoCifrado;
    }

    SecureRandom sr = new SecureRandom();
    byte[] iv = new byte[16];

    public byte[] cifraArchivo(byte[] sinCifrar, SecretKeySpec key) throws Exception {

        SecureRandom srx = new SecureRandom();
        byte[] ivx = new byte[16];
        sr.nextBytes(ivx);
        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aes.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivx));
        byte[] cifrado = aes.doFinal(sinCifrar);
        byte[] ultimo = new byte[ivx.length + cifrado.length];
        System.arraycopy(ivx, 0, ultimo, 0, 16);
        System.arraycopy(cifrado, 0, ultimo, 16, cifrado.length);
        return ultimo;
    }

    public SecretKeySpec crearLlave(String frase) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(frase.getBytes("UTF-8"));
        SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
        return key;

    }

    public String descifra(String textoCifrado, SecretKeySpec key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");//cambiar a algun algoritmo cbc
        aes.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] cifrado = Base64.decode(textoCifrado);
        byte[] bytes = aes.doFinal(cifrado);
        String sinCifrar = new String(bytes);
        return sinCifrar;

    }

    public byte[] descifraArchivo(byte[] archivoCifrado, SecretKeySpec key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");//cambiar a algun algoritmo cbc
        byte[] ivx = new byte[16];
        byte[] cifrado = new byte[archivoCifrado.length - 16];
        System.arraycopy(archivoCifrado, 0, ivx, 0, 16);
        System.arraycopy(archivoCifrado, 16, cifrado, 0, cifrado.length);
        aes.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivx));
        byte[] bytes = aes.doFinal(cifrado);
        return bytes;

    }

    public static boolean verificar(File archivo, File publicKey) {
        try {

            PublicKey pub = getPublic(publicKey);
            
            /* Obtengo todo el archivo : tiene largo (1 byte) , firma (n bytes dados por el largo) , archivo (todo el resto */
            byte[] buf = new byte[(int) archivo.length()];
            FileInputStream fis = new FileInputStream(archivo);
            fis.read(buf);
            fis.close();
            
            /* Veo el largo de la firma */
            byte[] largo = new byte[1];
            System.arraycopy(buf, 0, largo, 0, 1);
            int largoInt = (int)largo[0];
            
            /* Obtengo la firma */      
            byte[] firmaParaVerificar = new byte[largoInt];
            System.arraycopy(buf, 1, firmaParaVerificar,0 ,largoInt);
            
            /* Creo un objeto firma y lo inicializo con la public key */
            Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
            sig.initVerify(pub);
            
            /* Hago un update al objeto firma con el arcvhio */
            byte[] archivoSolo = new byte[buf.length-(largoInt+1)];
            sig.update(archivoSolo, 0, archivoSolo.length);
            
            /* Verifico */
            boolean verifies = sig.verify(firmaParaVerificar);
 
            System.out.println("signature verifies: " + verifies);
            
            

            return verifies;
        } catch (Exception e) {
            return false;
        }

    }

    
    public static boolean firmar(File archivoAFirmar, File archivoFirmado, File privateKey) {
        try {
            Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
            PrivateKey priv = getPrivate(privateKey);
            dsa.initSign(priv);

            byte[] buf = new byte[(int) archivoAFirmar.length()];
            FileInputStream fis = new FileInputStream(archivoAFirmar);
            fis.read(buf);
            dsa.update(buf);
            fis.close();

            byte[] firma = dsa.sign();

            FileOutputStream sigfos = new FileOutputStream(archivoFirmado);
            byte[] ultima = new byte[buf.length + firma.length + 1];
            byte[] largo = new byte[1];
            largo[0] = (byte) firma.length;

            System.arraycopy(largo, 0, ultima, 0, largo.length);
            System.arraycopy(firma, 0, ultima, 1, firma.length);
            System.arraycopy(buf, 0, ultima, firma.length + 1, buf.length);

            sigfos.write(ultima);

            sigfos.close();
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

}