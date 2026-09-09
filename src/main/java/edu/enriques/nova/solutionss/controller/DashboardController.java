package main.java.edu.enriques.nova.solutionss.controller;

import main.java.edu.enriques.nova.solutionss.util.SceneManager;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class DashboardController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuración inicial del menú principal
    }

    @FXML
    private void handleCerrarSesion() {
        // Cierra sesión y regresa limpiamente al Login
        SceneManager.switchTo("login-view.fxml", "Nova Solutions - Iniciar Sesión", 400, 500);
    }
}