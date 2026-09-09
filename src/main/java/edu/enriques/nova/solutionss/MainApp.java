package main.java.edu.enriques.nova.solutionss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Buscamos el fxml directamente en la carpeta resources/view/ usando la ruta absoluta estándar
            URL fxmlUrl = getClass().getResource("/resources/view/login-view.fxml");
            
            if (fxmlUrl == null) {
                throw new RuntimeException("No se encontró /resources/view/login-view.fxml");
            }

            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root, 600, 400);
            
            primaryStage.setTitle("Nova Solutions - Iniciar Sesión");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}