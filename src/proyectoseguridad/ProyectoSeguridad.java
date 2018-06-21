
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
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;


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
    static public CardChannel channel;
    static public TerminalFactory factory;
    static public List<CardTerminal> terminals;
    static public CardTerminal terminal ;
    static public Card card;
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException, ProtocolException, MalformedURLException, IOException, SQLException, Exception {
        // TODO code application logic here
        // show the list of available terminals
        //factory = TerminalFactory.getDefault();
        //terminals = factory.terminals().list();
        // System.out.println("Terminals: " + terminals);
//         get the first terminal
//        terminal = terminals.get(0);
//         establish a connection with the card
//        card = terminal.connect("T=0");
//         System.out.println("card ATR: " +
//         byteArrayToHex(card.getATR().getBytes()));
//        channel = card.getBasicChannel();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        Log Configuration
//        String logPath = System.getProperty("user.dir");
//
//        String logOption = "";
//
//        LogUtils logUtils = LogUtils.getInstance();
//        logUtils.configure(logPath,logOption);
//        
//        System.out.println(Funciones.readCedula(channel));
//        inicio init = new inicio();
//        
//        init.setVisible(true);
//        
        

    }    
}
