package main.java.edu.enriques.nova.solutionss.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

    @FXML private Button btnGestionEstudiantes;
    @FXML private Button btnCerrarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización del Dashboard
    }

    @FXML
    private void handleGestionEstudiantes(ActionEvent event) {
        try {
            // Intentar cargar la vista probando las rutas posibles dentro del classpath
            URL fxmlLocation = getClass().getResource("/view/estudiante-view.fxml");
            
            if (fxmlLocation == null) {
                fxmlLocation = getClass().getResource("/resources/view/estudiante-view.fxml");
            }
            
            if (fxmlLocation == null) {
                fxmlLocation = getClass().getResource("/main/java/edu/enriques/nova/solutionss/view/estudiante-view.fxml");
            }

            if (fxmlLocation == null) {
                mostrarAlerta("Error de Ruta", "No se encontró el archivo estudiante-view.fxml en los recursos.", Alert.AlertType.ERROR);
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Nova Solutions - Gestión de Estudiantes");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al Abrir Ventana", "Detalle del error en la vista FXML: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleCerrarSesion(ActionEvent event) {
        try {
            URL fxmlLocation = getClass().getResource("/view/login-view.fxml");
            if (fxmlLocation == null) {
                fxmlLocation = getClass().getResource("/resources/view/login-view.fxml");
            }

            if (fxmlLocation == null) {
                mostrarAlerta("Error de Ruta", "No se encontró la vista de Login.", Alert.AlertType.ERROR);
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Nova Solutions - Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cerrar sesión: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}