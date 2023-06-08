package controller.usuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import clases.Rol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.usuarioModel;
import clases.usuario;
import com.proyect.emsa.App;
import java.io.IOException;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import clases.SessionSistema;

public class registrarUsuario implements Initializable {

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

    @FXML
    private Label lblRol;

    private usuarioModel usuarioModeloObj = new usuarioModel();

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        if (SessionSistema.getCodigoUsuario() == 0) {
            cboRol.setVisible(false);
            lblRol.setVisible(false);
        } else {
            cargarRoles();
        }

    }

    public void cargarRoles() {
        List<Rol> lista = new ArrayList<>();
        try {
            ResultSet listadoroles = usuarioModeloObj.ejecutarConsulta("SELECT * FROM public.rol");
            while (listadoroles.next()) {
                int codigoRol = listadoroles.getInt("codigorol");
                String nombreRol = listadoroles.getString("nombrerol");
                Rol rolObj = new Rol(codigoRol, nombreRol);
                lista.add(rolObj);
            }

            ObservableList<Rol> items = FXCollections.observableArrayList(lista);
            cboRol.setItems(items);
            cboRol.setValue(new Rol(0, "Seleccione"));

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta SQL: " + ex.getMessage());
        }
    }

    @FXML
    void btnGuardarOnAction(ActionEvent event) {
        usuario usuarioObj = new usuario();

        //Capturamos la informacion del formulario
        usuarioObj.setUsuario(txtUsuario.getText());//Campo usuario
        usuarioObj.setCorreo(txtCorreo.getText());// Campo correo
        usuarioObj.setEstado(1);// Campo estado
        usuarioObj.setPassword(txtpassword.getText());// Campo estado
        //Si esta creando un nuevo usuario desde el login se establece por defecto el rol en 2
        if (SessionSistema.getCodigoUsuario() == 0) {
            usuarioObj.setRol(2);
        }
        if (!(cboRol.getValue().getCodigo() == 0)) {
            usuarioObj.setRol(cboRol.getValue().getCodigo());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un Rol para el usuario.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            return;
        }

        //Le pasamos el obtejo tarea que creamos al principio de este metodo
        boolean rsp = this.usuarioModeloObj.registrar(usuarioObj);

        if (rsp) {
            //Para que muestre una alerta de informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exito");
            alert.setHeaderText(null);
            alert.setContentText("Se registro correctamente.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            limpiarCampos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Algo salio mal.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
    }

    private void limpiarCampos() {
        txtUsuario.setText("");
        txtCorreo.setText("");
        txtpassword.setText("");
        cboRol.getSelectionModel().select(0);
    }

    @FXML
    void btnCancelar_clic(ActionEvent event) throws IOException {
        App appObj = new App();
        //Verificamos que exista una sesion
        if (SessionSistema.getCodigoUsuario() == 0) {
            appObj.setRoot("Login");
        } else {
            appObj.setRoot("Inicio");
        }

    }
}
