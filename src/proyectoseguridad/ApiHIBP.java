/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import static proyectoseguridad.ProyectoSeguridad.con;
import static proyectoseguridad.ProyectoSeguridad.mC;
import static proyectoseguridad.ProyectoSeguridad.password;

/**
 *
 * @author Juan Manuel
 */
public class ApiHIBP {
    
    // Metodo que crea mi consulta para la api de iHaveBeenPwned
    public HttpURLConnection crearConsulta (String prefijo) throws ProtocolException, MalformedURLException, IOException
    {
        String consulta = "https://api.pwnedpasswords.com/range/" + prefijo;
        URL url = new URL(consulta);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent","java-Seguridad");
        
        return con;
    }
    
    // Metodo que me carga en un buffer todos los sufijos concordantes con mi prefijo separados por un |
    public StringBuffer bufferSufijos (HttpURLConnection con) throws IOException
            {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
            content.append("|");
        }
        in.close();
        return content;
}
    
    private String comprobarVulnerabilidad(StringBuffer content, String sufijoHash)
    {
        String mensajeResultado = "";
        String[] posibles = content.toString().split("\\|");
        int flag = 0;
        for(String s:posibles)
        {  
            int x = s.length();
            String[] linea = s.split(":");
            String ocurrencias = linea[1];
            String sufijo = linea [0];
            if (sufijo.equals(sufijoHash.toUpperCase()))
            {mensajeResultado = "Se encontro el sufijo con "+ ocurrencias + " ocurrencias";
            flag = 1;
            break;
            }
        }
        if(flag==0)
        {
            mensajeResultado = "Su clave es bastante buena";
        }
        return mensajeResultado;
    
    }
    
    
    public String resultadoVulnerabilidad(String password) throws NoSuchAlgorithmException, MalformedURLException, IOException{
            
        String retorno = "";
        byte[] bytesPrueba;
        MiCriptografia mC = new MiCriptografia();
        bytesPrueba = mC.hashSHA1(password);
        String finalStringSha = mC.hashString(bytesPrueba);
        String sufijoHash = mC.sufijoHash(finalStringSha);
        String prefijoHash = mC.prefijoHash(finalStringSha);
        HttpURLConnection con;
        StringBuffer content;
        
        con = crearConsulta(prefijoHash);
        int status = con.getResponseCode();
        content = bufferSufijos(con);
        con.disconnect();
        retorno = comprobarVulnerabilidad(content, sufijoHash);
        
        return retorno;
    }
    
}
