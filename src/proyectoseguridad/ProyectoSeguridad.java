
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.postgresql.util.Base64;


/**
 *
 * @author Juan Manuel
 */
public class ProyectoSeguridad {

    /**
     * @param args the command line arguments
     */
    
    static public String password = "prueba123";
    static public byte[] bytesPrueba;
    static public HttpURLConnection con;
    static public StringBuffer content;
    static public ArrayList<Usuario> users = new ArrayList<Usuario>();
    static public Conector conector = new Conector();
    static public ABM abm = new ABM();
    static public MiCriptografia mC = new MiCriptografia();
    static public ApiHIBP api = new ApiHIBP();
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException, ProtocolException, MalformedURLException, IOException, SQLException {
        // TODO code application logic here
        
        
        bytesPrueba = mC.hashSHA1(password);
        
        
        String finalStringSha = mC.hashString(bytesPrueba);
        String sufijoHash = mC.sufijoHash(finalStringSha);
        String prefijoHash = mC.prefijoHash(finalStringSha);
        
        con = api.crearConsulta(prefijoHash);
        int status = con.getResponseCode();

        content = api.bufferSufijos(con);
        con.disconnect();
        
        
       // Este metodo lo cambie a privado 
       // api.comprobarVulnerabilidad(content, sufijoHash);
        
        
        /////////////////////////////////////////////////
//        Usuario prueba = new Usuario("Rafael","Contrasena Rafael","Login","Apellido");
//
//        String x = abm.altaUsuario(prueba);
//        
        //String x = abm.altaUsuario(prueba);

//        conector.abrirConexion();
        //Conector.sentencia.execute(x);
        //abm.llenarTablaInicialUsuarios(users);
//        Conector.cerrarConexion();
        //System.out.println(prueba);
        
//        Usuario prueba2 = new Usuario("Rafael","Contrasena Rafael","Login","Apellido");
//        
//        System.out.println(mC.autenticacion(prueba2));
        
        
//          String claveEncripacion = "claveTest";
//          String textoParaEncriptar = "Estoy encriptando este texto";
//          File archivo = new File(textoParaEncriptar);
//          FileReader algo = new FileReader(archivo);
//          
//          BufferedReader br = new BufferedReader(algo);
//          String aux = null;
//          while( (aux = br.readLine()) != null){
//          
//          }
//      
        String texto = "Texto que voy a cifrar";
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            System.out.println(plainTextBytes);
            byte[] buf = cipher.doFinal(plainTextBytes);
            System.out.println(buf);
            String base64Bytes = Base64.encodeBytes(buf); 
            System.out.println(base64Bytes);
            
            
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] base64Bytes2 = Base64.decode(base64Bytes);
            System.out.println(base64Bytes2);
            byte[] buf2 = cipher.doFinal(base64Bytes2);
            System.out.println(buf2);
            String plainTextBytes2 = new String(buf2);
            System.out.println(plainTextBytes2);
            
            // byte[] message = Base64.decode(base64Bytes.getBytes("utf-8"));
//            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
//            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
// 
//            Cipher decipher = Cipher.getInstance("DESede");
//            decipher.init(Cipher.DECRYPT_MODE, key);
// 
//            byte[] plainText = decipher.doFinal(message);
// 
//            base64EncryptedString = new String(plainText, "UTF-8");
                int a =3;
        } catch (Exception ex) {
        }

    }
    
}
