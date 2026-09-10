package main.java.edu.enriques.nova.solutionss.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import main.java.edu.enriques.nova.solutionss.dao.EstudianteDao;
import main.java.edu.enriques.nova.solutionss.model.Estudiante;

public class EstudianteController implements Initializable {

    @FXML private TextField txtCarnet;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtGrado;
    @FXML private TextField txtSeccion;
    @FXML private TextField txtBuscar;

    @FXML private Button btnGuardar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnVolver;

    @FXML private TableView<Estudiante> tblEstudiantes;
    @FXML private TableColumn<Estudiante, Integer> colId;
    @FXML private TableColumn<Estudiante, String> colCarnet;
    @FXML private TableColumn<Estudiante, String> colNombres;
    @FXML private TableColumn<Estudiante, String> colApellidos;
    @FXML private TableColumn<Estudiante, String> colGrado;
    @FXML private TableColumn<Estudiante, String> colSeccion;

    private ObservableList<Estudiante> listaEstudiantes;
    private EstudianteDao estudianteDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaEstudiantes = FXCollections.observableArrayList();
        estudianteDao = new EstudianteDao();

        // Vincular las columnas de la tabla con las propiedades de la clase Estudiante
        colId.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        colCarnet.setCellValueFactory(new PropertyValueFactory<>("carnet"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colGrado.setCellValueFactory(new PropertyValueFactory<>("grado"));
        colSeccion.setCellValueFactory(new PropertyValueFactory<>("seccion"));

        // Cargar los registros iniciales desde la base de datos
        cargarEstudiantes();

        // Evento al hacer clic en una fila de la tabla para autorellenar los campos
        tblEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtCarnet.setText(newSelection.getCarnet());
                txtNombres.setText(newSelection.getNombre());
                txtApellidos.setText(newSelection.getApellido());
                txtGrado.setText(newSelection.getGrado());
                txtSeccion.setText(newSelection.getSeccion());
            }
        });
    }

    @FXML
    private void handleGuardarEstudiante(ActionEvent event) {
        if (txtCarnet.getText().isEmpty() || txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty()) {
            mostrarAlerta("Campos requeridos", "Por favor completa los campos principales (Carnet, Nombre, Apellido).", Alert.AlertType.WARNING);
            return;
        }

        Estudiante estudiante = new Estudiante();
        estudiante.setCarnet(txtCarnet.getText());
        estudiante.setNombre(txtNombres.getText());
        estudiante.setApellido(txtApellidos.getText());
        estudiante.setGrado(txtGrado.getText());
        estudiante.setSeccion(txtSeccion.getText());

        if (estudianteDao.insertar(estudiante)) {
            mostrarAlerta("Éxito", "Estudiante guardado correctamente.", Alert.AlertType.INFORMATION);
            cargarEstudiantes();
            handleLimpiarCampos(null);
        } else {
            mostrarAlerta("Error", "No se pudo guardar el estudiante en la base de datos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleEliminarEstudiante(ActionEvent event) {
        Estudiante seleccionado = tblEstudiantes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Selección requerida", "Por favor, selecciona un estudiante de la tabla para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Estás seguro de que deseas eliminar a " + seleccionado.getNombre() + " " + seleccionado.getApellido() + "?");

        Optional<ButtonType> res = confirmacion.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.OK) {
            if (estudianteDao.eliminar(seleccionado.getIdEstudiante())) {
                mostrarAlerta("Éxito", "Estudiante eliminado correctamente.", Alert.AlertType.INFORMATION);
                cargarEstudiantes();
                handleLimpiarCampos(null);
            } else {
                mostrarAlerta("Error", "No se pudo eliminar el estudiante de la base de datos.", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void handleLimpiarCampos(ActionEvent event) {
        txtCarnet.clear();
        txtNombres.clear();
        txtApellidos.clear();
        txtGrado.clear();
        txtSeccion.clear();
        if (txtBuscar != null) txtBuscar.clear();
        tblEstudiantes.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleVolverMenu(ActionEvent event) {
        // Lógica de navegación para regresar al menú principal
    }

    private void cargarEstudiantes() {
        listaEstudiantes.clear();
        listaEstudiantes.addAll(estudianteDao.listar());
        tblEstudiantes.setItems(listaEstudiantes);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}