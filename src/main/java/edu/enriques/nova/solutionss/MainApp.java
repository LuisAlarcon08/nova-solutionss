package main.java.edu.enriques.nova.solutionss;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.edu.enriques.nova.solutionss.util.SceneManager;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneManager.setPrimaryStage(primaryStage);
        SceneManager.switchTo("login-view.fxml", "Nova Solutions - Iniciar Sesión", 900, 700);
    }

    public static void main(String[] args) {
        launch(args);
    }
}