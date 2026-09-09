package main.java.edu.enriques.nova.solutionss.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.edu.enriques.nova.solutionss.dao.UsuarioDao;
import main.java.edu.enriques.nova.solutionss.model.Usuario;
import main.java.edu.enriques.nova.solutionss.util.SceneManager;

public class LoginController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnIniciar;

    @FXML
    private Hyperlink linkCrearCuenta;

    private final UsuarioDao usuarioDao = new UsuarioDao();

    @FXML
    private void handleLogin(ActionEvent event) {
        String userOrEmail = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();
        
        if (userOrEmail.isEmpty() || password.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nova Solutions - Advertencia", "Por favor, completa los campos de usuario y contraseña.");
            return;
        }

        Usuario usuarioLogueado = usuarioDao.login(userOrEmail, password);

        if (usuarioLogueado != null) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Nova Solutions - Éxito", "¡Bienvenido, " + usuarioLogueado.getNombreCompleto() + "!");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Nova Solutions - Error", "Credenciales incorrectas. Verifica tu usuario y contraseña.");
        }
    }

    @FXML
    private void handleRegistrar(ActionEvent event) {
        String nombre = txtNombre.getText().trim();
        String usuarioStr = txtUsuario.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (nombre.isEmpty() || usuarioStr.isEmpty() || email.isEmpty() || password.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nova Solutions - Registro", "Para registrarte, por favor llena todos los campos en el formulario.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(nombre, usuarioStr, email, password, 2);
        boolean registrado = usuarioDao.registrar(nuevoUsuario);

        if (registrado) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Nova Solutions - Registro Exitoso", "¡Usuario " + usuarioStr + " registrado con éxito!");
            limpiarCampos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Nova Solutions - Error", "No se pudo registrar el usuario. Es probable que el nombre de usuario o correo ya estén en uso.");
        }
    }

    @FXML
    private void handleIrARegistro(ActionEvent event) {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Nova Solutions", "¡El botón de crear cuenta ya está conectado correctamente!");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        if (txtNombre != null) txtNombre.clear();
        if (txtUsuario != null) txtUsuario.clear();
        if (txtEmail != null) txtEmail.clear();
        if (txtPassword != null) txtPassword.clear();
    }
}