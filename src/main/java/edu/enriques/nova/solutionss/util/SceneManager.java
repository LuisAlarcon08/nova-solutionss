package main.java.edu.enriques.nova.solutionss.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class SceneManager {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchTo(String fxmlFileName, String title, double width, double height) {
        try {
            // Buscamos utilizando la ruta absoluta que apunta directo a la carpeta resources/view/ de abajo
            URL fxmlUrl = SceneManager.class.getResource("../view/" + fxmlFileName);
            
            if (fxmlUrl == null) {
                // Respaldo por si acaso
                fxmlUrl = Thread.currentThread().getContextClassLoader().getResource("view/" + fxmlFileName);
            }

            if (fxmlUrl == null) {
                throw new RuntimeException("No se pudo localizar el archivo FXML: " + fxmlFileName);
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            
            if (primaryStage != null) {
                Scene scene = new Scene(root, width, height);
                primaryStage.setTitle(title);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error crítico al cargar la vista: " + fxmlFileName);
        }
    }
}