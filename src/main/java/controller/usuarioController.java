/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.io.IOException;
import java.net.URL;
import java.sql.ClientInfoStatus;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import clases.Rol;
import com.proyect.emsa.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.usuarioModel;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Eleazar
 */
public class usuarioController implements Initializable{
    
      @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;
    
      @FXML
    private ComboBox<Rol> cboRol;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtpassword;

    /**
     * Initializes the controller class.
     */
    private usuarioModel modelo = new usuarioModel();

    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        cargarRoles();
    }


    public void cargarRoles(){
        List<Rol> lista = new ArrayList<>();
        try {
            ResultSet listadoroles = modelo.ejecutarConsulta("SELECT * FROM public.rol");
            while (listadoroles.next()) {
                int codigoRol = listadoroles.getInt("codigorol");
                String nombreRol = listadoroles.getString("nombrerol");
                Rol rolObj = new Rol(codigoRol, nombreRol);
                lista.add(rolObj);
            }

            ObservableList<Rol> items = FXCollections.observableArrayList(lista);
            cboRol.setItems(items);
            cboRol.setValue(new Rol(0, "Seleccione"));

        }catch (SQLException ex){
            System.out.println("Error al ejecutar la consulta SQL: " + ex.getMessage());
        }
    }
   
}
