
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;
import java.io.BufferedReader;
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
        
        api.comprobarVulnerabilidad(content, sufijoHash);
        
        
        /////////////////////////////////////////////////
        Usuario prueba = new Usuario("manu","manumanu");
        //String x = abm.altaUsuario(prueba);
        
        conector.abrirConexion();
        //Conector.sentencia.execute(x);
        //abm.llenarTablaInicialUsuarios(users);
        Conector.cerrarConexion();
        mC.hashearPassSHA1(prueba);
        //System.out.println(prueba);
        
        ResultSet rs = abm.encontrarUsuario(prueba.getNombre());
        System.out.println(rs.getString(1)+rs.getString(2));
        rs.next();
        System.out.println(rs.getString(1)+rs.getString(2));
        
      
        
    }
    
}
