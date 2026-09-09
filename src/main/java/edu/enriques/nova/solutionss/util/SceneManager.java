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
        // Busca directamente dentro del paquete resources/view/ de tu proyecto
        URL fxmlUrl = SceneManager.class.getResource("/resources/view/" + fxmlFileName);

        if (fxmlUrl == null) {
            fxmlUrl = SceneManager.class.getResource("/view/" + fxmlFileName);
        }

        if (fxmlUrl == null) {
            System.err.println(">>> ERROR: No se encontró el archivo FXML: " + fxmlFileName);
            return;
        }

        System.out.println(">>> Cargando FXML desde: " + fxmlUrl.toExternalForm());

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        
        if (primaryStage != null) {
            Scene scene = new Scene(root, width, height);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.err.println(">>> ERROR: primaryStage es NULL.");
        }
    } catch (Exception e) {
        System.err.println(">>> EXCEPCIÓN DETALLADA AL CARGAR " + fxmlFileName + ":");
        e.printStackTrace();
    }

    }

    public static void showLogin() {
        switchTo("login-view.fxml", "Nova Solutions - Iniciar Sesión", 400, 500);
    }

    public static void showDashboard() {
        switchTo("dashboard-view.fxml", "Nova Solutions - Menú Principal", 800, 600);
    }

    public static void showEstudianteManagement() {
        switchTo("estudiante-view.fxml", "Nova Solutions - Gestión de Estudiantes", 900, 600);
    }
}