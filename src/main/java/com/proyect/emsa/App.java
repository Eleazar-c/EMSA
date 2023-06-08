package com.proyect.emsa;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("Login"), 858, 665);
        stage.setScene(scene);
         //stage.setMaximized(true);
        stage.show();
    }

   
    public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public Parent loadFXML(String fxml) {
        try{
        String projectDir = System.getProperty("user.dir");
        URL ProjectRoot = Paths.get(projectDir + "/src/main/java/views/" + fxml + ".fxml").toUri().toURL();
        System.out.println("root" + ProjectRoot.toString());
        FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
        return fxmlLoader.load();
         } catch (IOException ex) {
            ex.printStackTrace();
            URL projectRoot = App.class.getResource(fxml + ".fxml");
             FXMLLoader fxmlLoader = new FXMLLoader(projectRoot);
            return null;
        }

    }

    public static void main(String[] args) {
        launch();
    }

}
