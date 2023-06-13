/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.eventos;

import clases.Parametro;
import clases.asientos;
import clases.eventoSeleccionado;
import clases.seccion;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.proyect.emsa.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.conexion;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Eleazar
 */
public class SeleccionAsientosController implements Initializable {

    @FXML
    public GridPane GridPadre;
    
    @FXML
    private Button btnRegresar;
    
    private final GridPane contenedor = new GridPane();

    private final ArrayList<asientos> todaListaAsinto = new ArrayList<asientos>();
    //private final ArrayList<String> seleccionados = new ArrayList<>();
    private List<Button> seleccionados = new ArrayList<>();
    private ArrayList<String> CodigoSelecionAsiento = new ArrayList<>();
    private Map<Button, Background> colorOriginal = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    private final int fontSize = 8;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Parametro<Integer> params = eventoSeleccionado.getParametroObj();

        GridPane grid1R = printButtonsR(5, 4, 0, "'A','B','C','D','E','F','G'", "R");
        contenedor.add(grid1R, 0, 0);

        GridPane grid1L = printButtonsL(6, 7, 0, "'A','B','C','D','E','F','G'", "L");
        contenedor.add(grid1L, 3, 0);
        GridPane grid2R = printButtonsCenterR(0, 0, "'AA','BB','CC','DD','EE','FF','GG'", "R");
        contenedor.add(grid2R, 1, 0);
        GridPane grid2L = printButtonsCenterL(10, 10, "'AA','BB','CC','DD','EE','FF','GG'", "L");
        contenedor.add(grid2L, 2, 0);
        GridPane grid3R = printButtonsCenterR(0, 0, "'H','I','J','K','L','M','N','O'", "R");
        contenedor.add(grid3R, 0, 1);
        GridPane grid3L = printButtonsCenterL(10, 10, "'H','I','J','K','L','M','N','O'", "L");
        contenedor.add(grid3L, 3, 1);
        GridPane grid4R = printButtonsCenterR(0, 0, "'HH','II','JJ','KK','LL','MM','NN'", "R");
        contenedor.add(grid4R, 1, 1);
        GridPane grid4L = printButtonsCenterL(10, 10, "'HH','II','JJ','KK','LL','MM','NN'", "L");
        contenedor.add(grid4L, 2, 1);
        vbox.getChildren().add(contenedor);

       
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        GridPadre.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPadre.setMargin(scrollPane, new Insets(90, 0, 0, 0));
        GridPadre.add(scrollPane, 0, 0);

    }

    private GridPane printButtonsR(int columnIndex, int tmpColumnIndex, int rowIndex, String columns, String lado) {
        ArrayList<asientos> list = this.ListAsientos(columns, lado);
        todaListaAsinto.addAll(list);
        GridPane newGrid = new GridPane();
        for (asientos asiento : list) {
            String text = asiento.getFila() + asiento.getColumna() + asiento.getLado();
            Button btn = new Button(text);
            btn.setId(text + "|" + asiento.getCodigo());
            btn.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
            btn.setOnAction(event -> {
                this.eventAsiento(btn, asiento);
            });
            Background bg = asiento.estaActivo() ? colorAsiento(asiento.getCodigoSeccion()) : new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null));
            btn.setBackground(bg);
            btn.setTextFill(Color.WHITE);
            btn.setWrapText(false);
            newGrid.add(btn, columnIndex, rowIndex);
            columnIndex++;
            if (columnIndex == 11) {
                columnIndex = tmpColumnIndex != 0 ? tmpColumnIndex-- : tmpColumnIndex;
                rowIndex++;
            }
        }
        return newGrid;
    }

    private GridPane printButtonsL(int columnIndex, int tmpColumnIndex, int rowIndex, String columns, String lado) {
        ArrayList<asientos> list = this.ListAsientos(columns, lado);
        todaListaAsinto.addAll(list);
        GridPane newGrid = new GridPane();
        for (asientos asiento : list) {
            String text = asiento.getFila() + asiento.getColumna() + asiento.getLado();
            Button btn = new Button(text);

            btn.setId(text + "|" + asiento.getCodigo());
            btn.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
            btn.setOnAction(event -> {
                this.eventAsiento(btn, asiento);
            });
            Background bg = asiento.estaActivo() ? colorAsiento(asiento.getCodigoSeccion()) : new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null));
            btn.setBackground(bg);
            btn.setTextFill(Color.WHITE);
            newGrid.add(btn, columnIndex, rowIndex);
            columnIndex--;
            if (columnIndex == 0) {
                columnIndex = tmpColumnIndex != 11 ? tmpColumnIndex++ : tmpColumnIndex;
                rowIndex++;
            }
        }
        return newGrid;
    }

    private GridPane printButtonsCenterR(int columnIndex, int rowIndex, String columns, String lado) {
        ArrayList<asientos> list = this.ListAsientos(columns, lado);
        todaListaAsinto.addAll(list);
        GridPane newGrid = new GridPane();
        for (asientos asiento : list) {
            String text = asiento.getFila() + asiento.getColumna() + asiento.getLado();
            Button btn = new Button(text);
            btn.setId(text + "|" + asiento.getCodigo());
            btn.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
            btn.setOnAction(event -> {
                this.eventAsiento(btn, asiento);
            });
            Background bg = asiento.estaActivo() ? colorAsiento(asiento.getCodigoSeccion()) : new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null));
            btn.setBackground(bg);
            btn.setTextFill(Color.WHITE);
            newGrid.add(btn, columnIndex, rowIndex);
            columnIndex++;
            if (columnIndex == 10) {
                columnIndex = 0;
                rowIndex++;
            }
        }
        return newGrid;
    }

    private GridPane printButtonsCenterL(int columnIndex, int rowIndex, String columns, String lado) {
        ArrayList<asientos> list = this.ListAsientos(columns, lado);
        todaListaAsinto.addAll(list);
        GridPane newGrid = new GridPane();
        for (asientos asiento : list) {
            String text = asiento.getFila() + asiento.getColumna() + asiento.getLado();
            Button btn = new Button(text);
            btn.setId(text + "|" + asiento.getCodigo());
            btn.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
            btn.setOnAction(event -> {
                this.eventAsiento(btn, asiento);
            });
            Background bg = asiento.estaActivo() ? colorAsiento(asiento.getCodigoSeccion()) : new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null));
            btn.setBackground(bg);
            btn.setTextFill(Color.WHITE);
            newGrid.add(btn, columnIndex, rowIndex);
            columnIndex--;
            if (columnIndex == 0) {
                columnIndex = 10;
                rowIndex++;
            }
        }
        return newGrid;
    }

    public ArrayList<asientos> ListAsientos(String columns, String lado) {

        ArrayList<asientos> listAsientos = new ArrayList<>();
        try (Connection conn = conexion.getConection()) {
            StringBuilder query = new StringBuilder();
            query.append("select * from asiento as a ");
            query.append("inner join seccion as s ");
            query.append("on a.codigoseccion = s.codigoseccion ");
            query.append(String.format("where fila in (%s) and lado = ? order by fila, lado", columns));

            PreparedStatement sql = conn.prepareStatement(query.toString());
            sql.setString(1, lado);

            ResultSet resultado = sql.executeQuery();

            while (resultado.next()) {
                asientos asientos = new asientos();
                seccion section = new seccion();
                asientos.setCodigo(resultado.getInt("codigoasiento"));
                asientos.setFila(resultado.getString("fila"));
                asientos.setColumna(resultado.getInt("columna"));
                asientos.setLado(resultado.getString("lado"));
                asientos.setCodigoSeccion(resultado.getInt("codigoseccion"));
                asientos.setActivo(resultado.getBoolean("estado"));
                section.setCodigoSeccion(resultado.getInt("codigoseccion"));
                section.setNombreSeccion(resultado.getString("Nombre"));
                asientos.setSeccion(section);

                listAsientos.add(asientos);
            }

        } catch (SQLException ex) {
            System.out.println("hubo un error" + ex.toString());
        }

        return listAsientos;
    }

    private void eventAsiento(Button button, asientos asiento) {
        /*
        Button button = (Button) event.getSource();
        Background newBackground =  new Background(new BackgroundFill(Color.GREEN, null, null));
        button.setBackground(newBackground);
         */

        if (seleccionados.contains(button)) {
            seleccionados.remove(button);
            Background originalBackground = colorOriginal.get(button); // Obtener el color original del botón
            button.setBackground(originalBackground);
        } else if (seleccionados.size() < eventoSeleccionado.getTotalvoletos()) {
            seleccionados.add(button);
            Background originalBackground = button.getBackground(); // Obtener el color original del botón
            colorOriginal.put(button, originalBackground); // Guardar el color original en el mapa
            button.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(10), null)));
        } else {
            Button primerBoton = seleccionados.get(0);
            Background primerBotonBackground = colorOriginal.get(primerBoton);
            primerBoton.setBackground(colorAsiento(asiento.getCodigoSeccion()));
            //primerBoton.setBackground(colorAsiento(asiento.getCodigoSeccion()));
            seleccionados.remove(0);
            seleccionados.add(button);
            button.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(10), null)));
            button.setOnMouseClicked(event -> {
                seleccionados.remove(button);
                Background originalBackground = colorOriginal.get(button); // Obtener el color original del botón
                button.setBackground(originalBackground); // Restaurar el color original del botón deseleccionado
            });
            primerBoton.setOnMouseClicked(event -> {
                seleccionados.remove(primerBoton);
                primerBoton.setBackground(primerBotonBackground); // Restaurar el color original del botón deseleccionado
            });
        }
    }

  
    private Background colorAsiento(int idSeccion) {
        Background model = null;
        switch (idSeccion) {
            case 1:
                model = new Background(new BackgroundFill(Color.web("#ea483f"), new CornerRadii(10), null));
                break;
            case 2:
                model = new Background(new BackgroundFill(Color.web("#ecb95c"), new CornerRadii(10), null));
                break;
            case 3:
                model = new Background(new BackgroundFill(Color.web("#7ae709"), new CornerRadii(10), null));
                break;
            case 4:
                model = new Background(new BackgroundFill(Color.web("#1a55f9"), new CornerRadii(10), null));
                break;
            default:
                model = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null));
        }

        return model;
    }

    
    @FXML
    void btnRegresarClic(ActionEvent event) throws IOException {
         App appObj = new App();
         appObj.setRoot("DetalleEventocompra");
    }
}
