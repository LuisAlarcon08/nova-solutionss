package main.java.edu.enriques.nova.solutionss.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.java.edu.enriques.nova.solutionss.util.SceneManager;

public class DashboardController {

    @FXML
    private void handleIrAEstudiantes(ActionEvent event) {
        SceneManager.switchTo("estudiante-view.fxml", "Nova Solutions - Gestión de Estudiantes", 950, 650);
    }

    @FXML
    private void handleCerrarSesion(ActionEvent event) {
        SceneManager.switchTo("login-view.fxml", "Nova Solutions - Iniciar Sesión", 900, 700);
    }
}