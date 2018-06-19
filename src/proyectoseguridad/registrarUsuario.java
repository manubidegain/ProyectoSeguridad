
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setText("Ingrese sus datos personales");

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        lblPassword.setText("Password");

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        lblErrorLogin.setForeground(new java.awt.Color(255, 51, 51));
        lblErrorLogin.setText("La cedula ingresada ya se encuentra reistrada");

        lblErrorNombre.setForeground(new java.awt.Color(255, 51, 51));
        lblErrorNombre.setText("El nombre elegido no se encuentra dispoinible");

        lblErrirApellido.setForeground(new java.awt.Color(255, 51, 51));
        lblErrirApellido.setText("El Apellido elegido no se encuentra dispoinible");

        lblErrorPassword.setText("jLabel5");

        txtPass2.setText("jPasswordField1");

        jLabel5.setText("Repetir Password");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(jLabel4)
                .addContainerGap(361, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLogin)
                            .addComponent(txtNombre)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtApellido))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassword)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPassword)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblErrorPassword)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnCrear)
                                    .addGap(36, 36, 36)
                                    .addComponent(jButton1))
                                .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblErrorLogin))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrirApellido)
                            .addComponent(lblErrorNombre))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel4)
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorLogin))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorNombre))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrirApellido))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerificar)
                    .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(lblErrorPassword)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(jButton1))
                .addGap(71, 71, 71))
        );

        jLabel1.getAccessibleContext().setAccessibleName("lblLogin");
        jLabel2.getAccessibleContext().setAccessibleName("lblNombe");
        jLabel3.getAccessibleContext().setAccessibleName("lblApellido");
        txtLogin.getAccessibleContext().setAccessibleName("txtLogin");
        txtNombre.getAccessibleContext().setAccessibleName("txtNombre");
        txtNombre.getAccessibleContext().setAccessibleDescription("");
        txtApellido.getAccessibleContext().setAccessibleName("txtApellido");
        jLabel4.getAccessibleContext().setAccessibleName("txtTitulo");
        btnCrear.getAccessibleContext().setAccessibleName("btnCrear");
        txtPass2.getAccessibleContext().setAccessibleName("txtPass2");

        getAccessibleContext().setAccessibleName("btnCrear");

        pack();
    }// </editor-fold>                        

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {                                         
      
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
            
        }
        
    }                                        

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {                                             
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
    }                                            

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.dispose();
    }                                        

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
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    // End of variables declaration                   

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
