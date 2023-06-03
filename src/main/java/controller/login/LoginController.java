/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.login;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clases.Rol;
import clases.login;
import com.proyect.emsa.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
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
             ResultSet datosUsuario = modeloObj.ejecutarConsulta("SELECT codigousuario,Nombre,usuario,clave,codigorol,correo,codigoestado FROM USUARIO WHERE usuario= '"+ usuario+"' AND clave= '"+clave+"'");
             while (datosUsuario.next()) {
                 String usuarioCliente = datosUsuario.getString("usuario");
                 String claveCliente = datosUsuario.getString("clave");

                 if (!usuarioCliente.trim().equals(usuario)){
                    valor= false;
                    break;
                 }

                 if (!claveCliente.trim() .equals(clave.trim())){
                     valor = false;
                     break;
                 }

                 loginObj.setNombreCompleto(datosUsuario.getString("nombre"));
                 loginObj.setCorreo(datosUsuario.getString("correo"));
                 loginObj.setCodRol(datosUsuario.getInt("codigorol"));
                 loginObj.setCodigoUsuario( datosUsuario.getInt("codigoestado"));
                 loginObj.setCodigoUsuario(datosUsuario.getInt("codigousuario"));

                 App appObj = new App();
                 appObj.setRoot("Inicio");
                 return;

             }
             if(!valor){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Usuario o contraseña incorrecta.");
                 alert.initStyle(StageStyle.UTILITY);
                 alert.showAndWait();
             }
         }catch (SQLException ex){

         } catch (IOException e) {
             throw new RuntimeException(e);
         }


     }

    @FXML
    void hlCrearCuenta_clic(ActionEvent event) {

    }
    
}
