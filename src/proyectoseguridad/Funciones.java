/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

/**
 *
 * @author rafaelpelacchi
 */
public class Funciones {
 
    public boolean sqlInyection(String text) 
    {
        boolean result = false;
        if(text.contains("'")){
            result = true;
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
}
