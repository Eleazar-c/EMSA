/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.eventos;

import clases.SessionSistema;
import clases.evento;
import com.proyect.emsa.App;

import java.io.IOException;
import java.net.URL;
import clases.eventoSeleccionado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.conexion;

/**
 * FXML Controller class
 *
 * @author Eleazar
 */
public class ListaEventoController implements Initializable {

    @FXML
    private GridPane ListaEventos;

    @FXML
    private Button btnRegresarInicio;

    /**
     * Initializes the controller class.
     */
    private conexion connPosgres;

    private App appObj = new App();

    public ListaEventoController() {
        this.connPosgres = new conexion();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        // Establecer el ancho de cada columna al 50% de la pantalla
        vbox.setStyle("-fx-column-count: 2; -fx-column-gap: 10px; -fx-pref-width: 50%; -fx-margin-top: 50px;");
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        for (evento event : ListEvents()) {
            // Crear una tarjeta para cada usuario
            VBox card = createCard(event);

            // Agregar la tarjeta al VBox
            vbox.getChildren().add(card);
        }

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        ListaEventos.setMargin(scrollPane, new Insets(60, 0, 0, 0));
        ListaEventos.add(scrollPane, 0, 1);
    }

    public ArrayList<evento> ListEvents() {

        ArrayList<evento> listEvents = new ArrayList<>();

        try (Statement sql = connPosgres.getConection().createStatement()) {
            String query = "select * from evento";
            ResultSet resultado = sql.executeQuery(query);

            while (resultado.next()) {
                evento events = new evento();
                events.setCodigEvento(resultado.getInt("codigoevento"));
                events.setNombreEvento(resultado.getString("nombreEvento"));
                events.setSinopsis(resultado.getString("descripcion"));
                events.setFechainicio(resultado.getTimestamp("fechainicio").toLocalDateTime());
                events.setFechaFinal(resultado.getTimestamp("fechafinal").toLocalDateTime());
                events.setLinkImg(resultado.getString("img"));
                //events.setVisible(resultado.getBoolean("Visible"));
                events.setFechaInicioVisible(resultado.getDate("fechavisible"));
                events.setFechaFinalVisible(resultado.getDate("fechavisiblefinal"));
                events.setResponsable(resultado.getInt("codigoresponsable"));

                listEvents.add(events);
            }

        } catch (SQLException ex) {
            System.out.println("hubo un error" + ex.toString());
        }
        return listEvents;
    }

    //Creamos la tarjeta para mostrar el evento
    private VBox createCard(evento event) {
        // Crear la tarjeta del evento
        VBox card = new VBox();
        card.setSpacing(10);
        card.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        // Agregar los elementos de la tarjeta: nombre y foto (suponiendo que tienes propiedades 'name' y 'photo' en la clase User)
        Label nameLabel = new Label(event.getNombreEvento());
        final Integer CodigoEvento = event.getCodigEvento();

        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        String pathResource = System.getProperty("user.dir") + event.getLinkImg();
        ImageView photoImageView = new ImageView(new Image("file:" + pathResource));
        photoImageView.setFitWidth(200);
        photoImageView.setFitHeight(200);

        Label descriptionText = new Label(event.getSinopsis());
        descriptionText.setFont(Font.font("System", FontWeight.NORMAL, 12));
        descriptionText.setPadding(new Insets(5));
        descriptionText.setWrapText(true);

        HBox container = new HBox();
        container.setSpacing(10);

        container.getChildren().addAll(photoImageView, descriptionText);

        // Crear la etiqueta de fecha
        Text dateLabel = new Text("Fecha: " + event.getFechainicio());
        dateLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        // Crear la etiqueta de hora
        Text timeLabel = new Text("Hora: " + event.getFechaFinal());
        timeLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        HBox fechas = new HBox();
        fechas.setSpacing(50);

        fechas.getChildren().addAll(dateLabel, timeLabel);

        Button seeDetail = new Button("Detalle");
        seeDetail.setFont(Font.font("System", FontWeight.BOLD, 12));
        VBox.setMargin(seeDetail, new Insets(10));

        seeDetail.setOnAction(event1 -> {

            try {
               
                eventoSeleccionado.setCodigoEvento(CodigoEvento);

                appObj.setRoot("DetalleEventocompra");
            } catch (IOException ex) {
                Logger.getLogger(ListaEventoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        // Agregar los elementos al HBox de la tarjeta
        card.setAlignment(Pos.CENTER);
        card.getChildren().addAll(nameLabel, container, fechas, seeDetail);

        return card;
    }

    @FXML
    void btnRegresarInicio_clic(ActionEvent event) throws IOException {
        //Verificamos que exista una sesion
        SessionSistema session = new SessionSistema();
        if(session.getCodigoUsuario() == 0) {
            appObj.setRoot("Login");
        }else{
            appObj.setRoot("Inicio");
        }


    }

    public void setValor(int valor) {
        // Haz algo con el valor recibido
    }

}
