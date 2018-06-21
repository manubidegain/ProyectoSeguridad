/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

/**
 *
 * @author rafaelpelacchi
 */
public class Funciones {
    
    private static String certificate_HEX_DER_encoded = "";
 
    public static boolean sqlInyection(String text) 
    {
        boolean result = false;
        if(text.contains("'")){
            result = false;
        }        
        return result;
     }
    
//    Metodo que chequea si hay un posible ataque de xss
     public boolean CrossSite(String texto) 
    {
        int apertura = 0;
        int cierre = 0;
        int indiceApertura = 0;
        int indicecierre = 0;
        String textoAuxiliar = texto;
        
        indiceApertura = textoAuxiliar.indexOf("<");
        if(indiceApertura >= 0){ 
            apertura++; 
            textoAuxiliar = textoAuxiliar.replaceFirst("<","_");
        }
        
        indiceApertura = textoAuxiliar.indexOf("<");
        if(indiceApertura >= 0){ 
            apertura++; 
            textoAuxiliar = textoAuxiliar.replaceFirst("<","_");
        }
        
        indicecierre= textoAuxiliar.indexOf(">");
        if(indiceApertura >= 0){ 
            cierre++; 
            textoAuxiliar = texto.replaceFirst(">","_");
        }
        
        indiceApertura = textoAuxiliar.indexOf(">");
        if(indiceApertura >= 0){ 
            cierre++; 
            textoAuxiliar = texto.replaceFirst(">","_");
        }
        
        return apertura + cierre == 4 ;
     }
     
      public static FCITemplate selectFile(CardChannel channel, String fileID) throws CardException, Exception {

        String CLASS = "00";
        String INSTRUCTION = "A4";
        String PARAM1 = "00";
        String PARAM2 = "00";

        String dataIN = fileID;

        byte CLASSbyte = Utils.hexStringToByteArray(CLASS)[0];
        byte INSbyte = Utils.hexStringToByteArray(INSTRUCTION)[0];
        byte P1byte = Utils.hexStringToByteArray(PARAM1)[0];
        byte P2byte = Utils.hexStringToByteArray(PARAM2)[0];

        ResponseAPDU r = Utils.sendCommand(channel, CLASSbyte, INSbyte, P1byte, P2byte, Utils.hexStringToByteArray(dataIN), 0);

        // Si la lectura del archivo es exitosa debo construir el fci template
        if (r.getSW1() == (int) 0x90 && r.getSW2() == (int) 0x00) {

            FCITemplate fcit = new FCITemplate();
            fcit.buildFromBuffer(r.getData(), 0, r.getData().length);
            return fcit;

        } else {

            return null;
        }

    }
      
      public static String readBinary(CardChannel channel, int fileSize) throws CardException, FileNotFoundException, UnsupportedEncodingException {

        // Construyo el Read Binary, lo que cambia en cada read son P1 y P2
        // porque van variando los offset para ir leyendo el binario hasta llegar al tamaño total
        // en cada read leo FF
        String CLASS = "00";
        String INSTRUCTION = "B0";
        String dataIN = "";
        String PARAM1;
        String PARAM2;

        int FF_int = Integer.parseInt("FF", 16);

        int cantBytes = 0;
        int dataOUTLength = 0; //le

        String binaryHexString = "";

        while (cantBytes < fileSize) {

            // Calculo el LE
            // Si la cantidad de Bytes que me quedan por obtener es mayor a
            // FF entonces me traigo FF. Sino me traigo los Bytes que me quedan.
            if (cantBytes + FF_int <= fileSize) {
                dataOUTLength = FF_int;
            } else {
                dataOUTLength = fileSize - cantBytes;
            }

            // Param1 y param2 comienzan en 00 00, voy incrementando FF
            // bytes hasta leer el total del binario.
            String PARAM1_PARAM2 = Utils.byteArrayToHex(Utils.intToByteArray(cantBytes));

            //uso solo p2 porque la cantidad de bytes que voy leyendo es menor a FF
            if (cantBytes <= 255) {
                PARAM1 = "00";
                PARAM2 = PARAM1_PARAM2.substring(0, 2);
            } else {
                PARAM1 = PARAM1_PARAM2.substring(0, 2);
                PARAM2 = PARAM1_PARAM2.substring(2, 4);
            }
            byte CLASSbyte = Utils.hexStringToByteArray(CLASS)[0];
            byte INSbyte = Utils.hexStringToByteArray(INSTRUCTION)[0];
            byte P1byte = Utils.hexStringToByteArray(PARAM1)[0];
            byte P2byte = Utils.hexStringToByteArray(PARAM2)[0];

            ResponseAPDU r = Utils.sendCommand(channel, CLASSbyte, INSbyte, P1byte, P2byte, Utils.hexStringToByteArray(dataIN), dataOUTLength);

            binaryHexString += Utils.byteArrayToHex(r.getData());

            if (r.getSW1() == (int) 0x90 && r.getSW2() == (int) 0x00) {

                cantBytes += dataOUTLength;

            } else {
                // Fallo algun read binary
                return "";
            }

        }
        return binaryHexString;
    }
     
     public static boolean readCertificate(CardChannel channel) throws CardException, Exception {

        FCITemplate fcit = selectFile(channel, "B001");
        certificate_HEX_DER_encoded = readBinary(channel, fcit.getFileSize());

        return true;
    }
     
     
     
     
     public static boolean readCardCertificate(CardChannel channel) throws CertificateException, Exception {

        // Read card certificate
        readCertificate(channel);

        // Create X509 Certificate from previously read card certificate
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream b64eIDCertificate = new ByteArrayInputStream(Utils.hexStringToByteArray(certificate_HEX_DER_encoded));
        X509Certificate eIDCertificate = (X509Certificate) cf.generateCertificate(b64eIDCertificate);

        String certSerialNumber = Utils.formatHexaString(eIDCertificate.getSerialNumber().toString(16));

        System.out.println("Certificate Serial Number");
        System.out.println(certSerialNumber);
        System.out.println("CA Information");
        System.out.println(eIDCertificate.getIssuerDN());
        System.out.println("Certificate creation date");
        System.out.println(eIDCertificate.getNotBefore());
        System.out.println("Certificate expiration date");
        System.out.println(eIDCertificate.getNotAfter());
        System.out.println("Certificate owner information");
        System.out.println(eIDCertificate.getSubjectDN());
        pruebaCedula(eIDCertificate.getSubjectDN().toString());

        return true;
    }
     
     public static String readCedula(CardChannel channel) throws CertificateException, Exception {

        // Read card certificate
        readCertificate(channel);

        // Create X509 Certificate from previously read card certificate
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream b64eIDCertificate = new ByteArrayInputStream(Utils.hexStringToByteArray(certificate_HEX_DER_encoded));
        X509Certificate eIDCertificate = (X509Certificate) cf.generateCertificate(b64eIDCertificate);

        //String certSerialNumber = Utils.formatHexaString(eIDCertificate.getSerialNumber().toString(16));
        

        return pruebaCedula(eIDCertificate.getSubjectDN().toString());
    }
     
     
     public static String pruebaCedula(String hola)
     {
         String hola2[] = hola.split(",");
         String cedula = hola2[1];
         String casi[] = cedula.split("=");
         String porfa = casi[1].substring(3);
         return porfa;
     }
}
