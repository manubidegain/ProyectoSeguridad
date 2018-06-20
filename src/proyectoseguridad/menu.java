/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 *
 * @author Computer-1
 */
public class menu extends javax.swing.JFrame {

    File archivoACifrar;
    File archivoADescifrar;
    File archivoAFirmar;
    File archivoAVerificar;
    File clavePrivada;
    File clavePublica;
    String cedulaUsuario;
    String passwordUsuario;
    /**
     * Creates new form menu
     */
    public menu() {
        initComponents();
        errorLab.setVisible(false);
        txtClave.setText("");
        this.cedulaUsuario = cedulaUsuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCifrar = new javax.swing.JButton();
        btnDescifrar = new javax.swing.JButton();
        btnFirmar = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        errorLab = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCifrar.setText("Cifrar Archivo");
        btnCifrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCifrarActionPerformed(evt);
            }
        });

        btnDescifrar.setText("Descifrar Archivo");
        btnDescifrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescifrarActionPerformed(evt);
            }
        });

        btnFirmar.setText("Firmar Archivo");
        btnFirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirmarActionPerformed(evt);
            }
        });

        btnVerificar.setText("Verificar Firma");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingrese Clave de Cifrado");

        errorLab.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDescifrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCifrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLab)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtClave)
                        .addComponent(btnVerificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCifrar)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDescifrar)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLab)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirmar)
                    .addComponent(btnVerificar))
                .addContainerGap(178, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCifrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCifrarActionPerformed
        if (txtClave.getText().equals("")){
            errorLab.setText("Debe ingresar la clave para continuar");
            errorLab.setVisible(true);
        }
        else{
            JFileChooser ventanaCifrar = new JFileChooser();
            int result = ventanaCifrar.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                char buf[] = null;
                //FileReader archivoCargado;
                try{
                    this.archivoACifrar = ventanaCifrar.getSelectedFile();
                }
                catch(Exception ex){
                }
            }
        }
        
        String path = ManejoArchivos.obtenerRutaNueva(this.archivoACifrar.getAbsolutePath(),"cifrado");
        File archivoCifrado = new File (path);
        MiCriptografia mc = new MiCriptografia();
        try {
            SecretKeySpec key = mc.crearLlave(txtClave.getText());
            
            boolean cifrado = mc.cifraArchivo(archivoACifrar,archivoCifrado,key);
        if (cifrado){
            JOptionPane.showMessageDialog(rootPane, "El archivo fue cifrado correctamente");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "No fue posible cifrar el archivo");
        }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }//GEN-LAST:event_btnCifrarActionPerformed

    private void btnDescifrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescifrarActionPerformed
        if (txtClave.getText().equals("")){
            errorLab.setText("Debe ingresar la clave para continuar");
            errorLab.setVisible(true);
        }
        else{
            JFileChooser ventanaDescifrar = new JFileChooser();
            int result = ventanaDescifrar.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                char buf[] = null;
                //FileReader archivoCargado;
                try{
                    this.archivoADescifrar = ventanaDescifrar.getSelectedFile();
                }
                catch(Exception ex){
                }
            }
        }
        
        String path = ManejoArchivos.obtenerRutaNueva(this.archivoADescifrar.getAbsolutePath());
        File archivoDescifrado = new File (path);
        MiCriptografia mc = new MiCriptografia();
        try {
            SecretKeySpec key = mc.crearLlave(txtClave.getText());
            
            boolean descifrado = mc.descifraArchivo(archivoADescifrar,archivoDescifrado,key);
        if (descifrado){
            JOptionPane.showMessageDialog(rootPane, "El archivo fue Descifrado correctamente");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "No fue posible descifrar el archivo");
        }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDescifrarActionPerformed

    private void btnFirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirmarActionPerformed
        JFileChooser ventanaFirmar = new JFileChooser();
        int result = ventanaFirmar.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            char buf[] = null;
            //FileReader archivoCargado;
            try{
                this.archivoAFirmar = ventanaFirmar.getSelectedFile();
                JOptionPane.showMessageDialog(rootPane, "Se cargó el archivo correctamente, a continuacion seleccione el archivo que contiene la clave privada");
            }
            catch(Exception ex){
            }
        }
        JFileChooser ventanaClavePrivada = new JFileChooser();
        int result2 = ventanaClavePrivada.showOpenDialog(null);
        if(result2 == JFileChooser.APPROVE_OPTION){
            char buf[] = null;
            //FileReader archivoCargado;
            try{
                this.clavePrivada = ventanaClavePrivada.getSelectedFile();
            }
            catch(Exception ex){
            }
        }
        ManejoArchivos ma = new ManejoArchivos();
        //MiCriptografia mc = new MiCriptografia();
        String path = ma.obtenerRutaNueva(this.archivoAFirmar.getAbsolutePath(),"firmado");
        File archivoFirmado = new File (path);
       
        boolean firmado = MiCriptografia.firmar(archivoAFirmar,archivoFirmado,clavePrivada);
        if (firmado){
            JOptionPane.showMessageDialog(rootPane, "El archivo fue firmado correctamente");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "No fue posible firmar el archivo");
        }
    }//GEN-LAST:event_btnFirmarActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        JFileChooser ventanaVerificar = new JFileChooser();
        int result = ventanaVerificar.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            char buf[] = null;
            //FileReader archivoCargado;
            try{
                this.archivoAVerificar = ventanaVerificar.getSelectedFile();
            }
            catch(Exception ex){
            }
        }
        JOptionPane.showMessageDialog(rootPane, "Se cargó el archivo correctamente, a continuacion seleccione el archivo que contiene la clave publica");
        JFileChooser ventanaClavePublica = new JFileChooser();
        int result2 = ventanaClavePublica.showOpenDialog(null);
        if(result2 == JFileChooser.APPROVE_OPTION){
            char buf[] = null;
            //FileReader archivoCargado;
            try{
                this.clavePublica = ventanaClavePublica.getSelectedFile();
            }
            catch(Exception ex){
            }
        }
       
        boolean firmado = MiCriptografia.verificar(archivoAVerificar,clavePublica);
        if (firmado){
            JOptionPane.showMessageDialog(rootPane, "La firma corresponde al dueno de la PublicKey");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "La firma no corresponde con la PublicKey");
        }
    }//GEN-LAST:event_btnVerificarActionPerformed

    public void setCedulaUsuario(String cedulaUsuario){
        this.cedulaUsuario =cedulaUsuario;
    }        
    /**
     * @param args the command line arguments
     */

        public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCifrar;
    private javax.swing.JButton btnDescifrar;
    private javax.swing.JButton btnFirmar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JLabel errorLab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txtClave;
    // End of variables declaration//GEN-END:variables
}
