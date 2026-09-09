package main.java.edu.enriques.nova.solutionss.controller;

import main.java.edu.enriques.nova.solutionss.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class RegistroController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtUsuarioRegistro;
    @FXML
    private PasswordField txtPasswordRegistro;
    @FXML
    private TextField txtEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Lógica de inicio si se requiere
    }

    @FXML
    private void handleRegistrar() {
        System.out.println("Usuario registrado con éxito.");
        // Después de registrar, podemos regresar al login o ir al dashboard
        SceneManager.switchTo("login-view.fxml", "Nova Solutions - Iniciar Sesión", 400, 500);
    }

    @FXML
    private void handleVolverLogin() {
        // Regresa a la pantalla de Login
        SceneManager.switchTo("login-view.fxml", "Nova Solutions - Iniciar Sesión", 400, 500);
    }
}