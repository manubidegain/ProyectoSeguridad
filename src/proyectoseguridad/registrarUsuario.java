
package proyectoseguridad;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class registrarUsuario extends javax.swing.JFrame {
    Funciones dtoFunciones = new Funciones();
    ApiHIBP api = new ApiHIBP();
    static public HttpURLConnection con;
    
    public registrarUsuario() {
        initComponents();
        cargarPagina();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        lblPassword = new javax.swing.JLabel();
        btnVerificar = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        lblErrorLogin = new javax.swing.JLabel();
        lblErrorNombre = new javax.swing.JLabel();
        lblErrirApellido = new javax.swing.JLabel();
        lblErrorPassword = new javax.swing.JLabel();
        txtPass2 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoseguridad/fondo.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Cedula");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 157, -1, -1));
        jLabel1.getAccessibleContext().setAccessibleName("lblLogin");

        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 213, -1, -1));
        jLabel2.getAccessibleContext().setAccessibleName("lblNombe");

        jLabel3.setText("Apellido");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 276, -1, -1));
        jLabel3.getAccessibleContext().setAccessibleName("lblApellido");

        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        getContentPane().add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 152, 334, -1));
        txtLogin.getAccessibleContext().setAccessibleName("txtLogin");

        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 208, 334, -1));
        txtNombre.getAccessibleContext().setAccessibleName("txtNombre");
        txtNombre.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 271, 332, -1));
        txtApellido.getAccessibleContext().setAccessibleName("txtApellido");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setText("Ingrese sus datos personales");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));
        jLabel4.getAccessibleContext().setAccessibleName("txtTitulo");

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 439, -1, -1));
        btnCrear.getAccessibleContext().setAccessibleName("btnCrear");

        lblPassword.setText("Password");
        getContentPane().add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 335, -1, -1));

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVerificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 91, -1));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 330, 283, -1));

        lblErrorLogin.setForeground(new java.awt.Color(255, 51, 51));
        lblErrorLogin.setText("La cedula ingresada ya se encuentra reistrada");
        getContentPane().add(lblErrorLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 157, -1, -1));

        lblErrorNombre.setForeground(new java.awt.Color(255, 51, 51));
        lblErrorNombre.setText("El nombre elegido no se encuentra dispoinible");
        getContentPane().add(lblErrorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 213, -1, -1));

        lblErrirApellido.setForeground(new java.awt.Color(255, 51, 51));
        lblErrirApellido.setText("El Apellido elegido no se encuentra dispoinible");
        getContentPane().add(lblErrirApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 276, -1, -1));

        lblErrorPassword.setText("jLabel5");
        getContentPane().add(lblErrorPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 405, -1, -1));
        getContentPane().add(txtPass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 368, 227, -1));
        txtPass2.getAccessibleContext().setAccessibleName("txtPass2");

        jLabel5.setText("Repetir Password");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 373, -1, -1));

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 439, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoseguridad/fondo.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 540));

        getAccessibleContext().setAccessibleName("btnCrear");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
      
        String nombre= this.txtNombre.getText();
        String login = this.txtLogin.getText();;
        String apellido = this.txtApellido.getText();
        String password = this.txtPassword.getText();
        boolean datosBien = true;
                
        if (dtoFunciones.CrossSite(nombre)){this.lblErrorNombre.setVisible(true);datosBien=false ;}
        if (dtoFunciones.CrossSite(login)){this.lblErrorLogin.setVisible(true);datosBien=false ;}
        if (dtoFunciones.CrossSite(apellido)){this.lblErrirApellido.setVisible(true); datosBien=false;}
        if (this.txtPassword.getText().equals(this.txtPass2.getText())){
            if (dtoFunciones.CrossSite(password)){this.lblErrorPassword.setVisible(true); datosBien=false ;this.lblErrorPassword.setText("Password no disponible");}
        }
        else{
            datosBien=false;
            this.lblErrorPassword.setText("Los campos de password no coinciden");
            this.resetearCamposDePass();
        }
        
        if(datosBien){
            ABM abm = new ABM();
            Usuario newUser = new Usuario(nombre,apellido,login,password);
            try {
                abm.altaUsuario(newUser);
             } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(registrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                Logger.getLogger(registrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarPagina();
            JOptionPane.showMessageDialog(rootPane, "Se Creo el Usuario Correctamente", login, INFORMATION_MESSAGE);            
            this.dispose();
        }
        
    }//GEN-LAST:event_btnCrearActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        if (this.txtPassword.getText().equals(this.txtPass2.getText())){
            String password = this.txtPassword.getText();
            try {
                password = api.resultadoVulnerabilidad(password);
            }catch (Exception ex){
                Logger.getLogger(registrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.lblErrorPassword.setText(password);
            this.lblErrorPassword.setVisible(true);
       }else{
            this.lblErrorPassword.setText("Los campos de password no coinciden");
            this.lblErrorPassword.setVisible(true);
            this.resetearCamposDePass();
       }
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cargarPagina() { 
       visibilidadMensajes(false);
       resetearTextos();
    } 
    
    public void visibilidadMensajes(boolean valor){
       this.lblErrorLogin.setVisible(valor);
       this.lblErrorNombre.setVisible(valor);
       this.lblErrirApellido.setVisible(valor);
       this.lblErrorPassword.setVisible(valor);
    }
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registrarUsuario().setVisible(true);
            }
        });    
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblErrirApellido;
    private javax.swing.JLabel lblErrorLogin;
    private javax.swing.JLabel lblErrorNombre;
    private javax.swing.JLabel lblErrorPassword;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPass2;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

    private void resetearTextos() {
       this.txtApellido.setText("");
       this.txtLogin.setText("");
       this.txtNombre.setText("");
       this.resetearCamposDePass();
    }
    
    private void resetearCamposDePass() {
        this.txtPassword.setText("");
        this.txtPass2.setText("");
    }
}
