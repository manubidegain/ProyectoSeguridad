/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoseguridad;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaelpelacchi
 */
public class login extends javax.swing.JFrame {
    Funciones dtoFunciones = new Funciones();
    /**
     * Creates new form IngresoUsuario
     */
    public login() {
        initComponents();
        cargarPagina();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblErrorPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        btnIngresar1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(919, 533));
        getContentPane().setLayout(null);

        txtUsuario.setSize(new java.awt.Dimension(270, 26));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(330, 360, 270, 26);

        jLabel1.setText("Usuario");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(265, 363, 48, 16);

        jLabel2.setText("Password");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(260, 410, 59, 16);

        lblErrorPassword.setForeground(new java.awt.Color(255, 51, 51));
        lblErrorPassword.setText("Usuario o Password incorrecto");
        getContentPane().add(lblErrorPassword);
        lblErrorPassword.setBounds(371, 495, 191, 16);

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(txtPassword);
        txtPassword.setBounds(330, 410, 270, 26);

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresar);
        btnIngresar.setBounds(412, 454, 95, 29);

        btnIngresar1.setText("Sincronizar Con SmartCardReader");
        btnIngresar1.setActionCommand("sincornizar");
        btnIngresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresar1);
        btnIngresar1.setBounds(328, 303, 270, 29);
        btnIngresar1.getAccessibleContext().setAccessibleName("sincronizar");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoseguridad/Imagen.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(300, 60, 343, 209);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoseguridad/fondo.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 920, 540);

        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 100, 100);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
            String cedula = this.txtUsuario.getText();
            String password = this.txtPassword.getText();
            if(!error(cedula,password)){
                this.lblErrorPassword.setVisible(true);
            }
            else{
                boolean existeUsuario = false;
                try {
                    existeUsuario = existe(cedula,password);
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(!existeUsuario){
                    this.lblErrorPassword.setVisible(true);
                }
                else{
                    cargarPagina();
                    this.setVisible(false);
                    menu m = new menu();
                    m.setCedulaUsuario(cedula);
                    m.setVisible(true);
                }
            }
        
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnIngresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar1ActionPerformed
        // TODO add your handling code here:
        
        boolean existeUsuario = false;
        String cedula;
        try {
            cedula = Funciones.readCedula(ProyectoSeguridad.channel);
            existeUsuario = existeSmart(cedula);
        if(!existeUsuario){
            this.lblErrorPassword.setVisible(true);
        }
        else{
            cargarPagina();
            this.setVisible(false);
            menu m = new menu();
            m.setCedulaUsuario(cedula);
            m.setVisible(true);
        }
        } catch (Exception ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnIngresar1ActionPerformed
    
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
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnIngresar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblErrorPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    private void cargarPagina() {
        this.txtPassword.setText("");
        this.txtUsuario.setText("");
        this.lblErrorPassword.setVisible(false);
    }

    private boolean existe(String cedula,String password) throws SQLException, NoSuchAlgorithmException {
        boolean resultado = false;
        MiCriptografia mc = new MiCriptografia();
        resultado = mc.autenticacion(new Usuario(cedula,password));
        return resultado;
    }
    
    private boolean existeSmart(String cedula) throws SQLException, NoSuchAlgorithmException {
        boolean resultado = false;
        MiCriptografia mc = new MiCriptografia();
        resultado = mc.autenticacionSmart(cedula);
        return resultado;
    }

    private boolean error(String usuario, String password) {
        return !dtoFunciones.CrossSite(usuario) && !dtoFunciones.CrossSite(password) && !dtoFunciones.sqlInyection(usuario) && !dtoFunciones.sqlInyection(password);
    }
}
