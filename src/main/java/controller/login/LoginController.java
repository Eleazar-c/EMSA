/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.login;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clases.Rol;
import clases.login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.modelo;
import clases.SessionSistema;

/**
 * FXML Controller class
 *
 * @author Eleazar
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnIngresar;

    @FXML
    private Hyperlink hlCrearCuenta;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtclave;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    void btnIngresar_clic(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String clave = txtclave.getText();
        boolean valor = true;

         login loginObj = new login();

         loginObj.setUsuario(usuario);
         loginObj.setClave(clave);

         SessionSistema sessionObj = new SessionSistema();
         modelo modeloObj = new modelo();
         try {
             ResultSet datosUsuario = modeloObj.ejecutarConsulta("SELECT codigousuario,Nombre,usuario,clave,codigorol,correo,estado FROM USUARIO WHERE usuario= "+ usuario+" AND clave= "+clave);
             while (datosUsuario.next()) {
                 String usuarioCliente = datosUsuario.getString("usuario");
                 String claveCliente = datosUsuario.getString("clave");

                 if (usuarioCliente!= usuario){
                    valor= false;
                    break;
                 }

                 if (claveCliente == clave){
                     valor = false;
                     break;
                 }

                 String nombreCompleto = datosUsuario.getString("nombre");
                 String correo = datosUsuario.getString("correo");
                 String codRol = datosUsuario.getString("codigorol");
                 String codigoEstado = datosUsuario.getString("codigoestado");
                 int CodigoUsuario = datosUsuario.getInt("codigousuario");

             }
             if(valor){
             }
         }catch (SQLException ex){

         }



    }

    @FXML
    void hlCrearCuenta_clic(ActionEvent event) {

    }
    
}
