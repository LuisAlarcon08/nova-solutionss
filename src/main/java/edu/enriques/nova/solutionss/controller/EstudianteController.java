package main.java.edu.enriques.nova.solutionss.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.java.edu.enriques.nova.solutionss.dao.EstudianteDao;
import main.java.edu.enriques.nova.solutionss.model.Estudiante;
import main.java.edu.enriques.nova.solutionss.util.SceneManager;

import java.util.Optional;

public class EstudianteController {

    @FXML private TextField txtCarnet;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtGrado;
    @FXML private TextField txtSeccion;
    @FXML private TextField txtBuscar;

    @FXML private TableView<Estudiante> tblEstudiantes;
    @FXML private TableColumn<Estudiante, Integer> colId;
    @FXML private TableColumn<Estudiante, String> colCarnet;
    @FXML private TableColumn<Estudiante, String> colNombres;
    @FXML private TableColumn<Estudiante, String> colApellidos;
    @FXML private TableColumn<Estudiante, String> colGrado;
    @FXML private TableColumn<Estudiante, String> colSeccion;

    private final EstudianteDao estudianteDao = new EstudianteDao();
    private final ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    private FilteredList<Estudiante> listaFiltrada;
    private Estudiante estudianteSeleccionado;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        colCarnet.setCellValueFactory(new PropertyValueFactory<>("carnet"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colGrado.setCellValueFactory(new PropertyValueFactory<>("grado"));
        colSeccion.setCellValueFactory(new PropertyValueFactory<>("seccion"));

        cargarEstudiantes();
        configurarFiltroBusqueda();
    }

    @FXML
    private void handleGuardar() {
        if (validarCampos()) {
            Estudiante estudiante = new Estudiante();
            estudiante.setCarnet(txtCarnet.getText().trim());
            estudiante.setNombres(txtNombres.getText().trim());
            estudiante.setApellidos(txtApellidos.getText().trim());
            estudiante.setGrado(txtGrado.getText().trim());
            estudiante.setSeccion(txtSeccion.getText().trim());

            if (estudianteDao.insertar(estudiante)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudiante guardado correctamente.");
                cargarEstudiantes();
                handleLimpiar();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el estudiante.");
            }
        }
    }

    @FXML
    private void handleActualizar() {
        if (estudianteSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Selecciona un estudiante de la tabla para actualizar.");
            return;
        }

        if (validarCampos()) {
            estudianteSeleccionado.setCarnet(txtCarnet.getText().trim());
            estudianteSeleccionado.setNombres(txtNombres.getText().trim());
            estudianteSeleccionado.setApellidos(txtApellidos.getText().trim());
            estudianteSeleccionado.setGrado(txtGrado.getText().trim());
            estudianteSeleccionado.setSeccion(txtSeccion.getText().trim());

            if (estudianteDao.actualizar(estudianteSeleccionado)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudiante actualizado correctamente.");
                cargarEstudiantes();
                handleLimpiar();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el estudiante.");
            }
        }
    }

    @FXML
    private void handleEliminar() {
        if (estudianteSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Selecciona un estudiante de la tabla para eliminar.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Eliminación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de eliminar a " + estudianteSeleccionado.getNombres() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (estudianteDao.eliminar(estudianteSeleccionado.getIdEstudiante())) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudiante eliminado correctamente.");
                cargarEstudiantes();
                handleLimpiar();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el estudiante.");
            }
        }
    }

    @FXML
    private void handleSeleccionarFila(MouseEvent event) {
        estudianteSeleccionado = tblEstudiantes.getSelectionModel().getSelectedItem();
        if (estudianteSeleccionado != null) {
            txtCarnet.setText(estudianteSeleccionado.getCarnet());
            txtNombres.setText(estudianteSeleccionado.getNombres());
            txtApellidos.setText(estudianteSeleccionado.getApellidos());
            txtGrado.setText(estudianteSeleccionado.getGrado());
            txtSeccion.setText(estudianteSeleccionado.getSeccion());
        }
    }

    private void cargarEstudiantes() {
        listaEstudiantes.clear();
        listaEstudiantes.addAll(estudianteDao.listar());
        listaFiltrada = new FilteredList<>(listaEstudiantes, p -> true);
        tblEstudiantes.setItems(listaFiltrada);
    }

    private void configurarFiltroBusqueda() {
        if (txtBuscar != null) {
            txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
                listaFiltrada.setPredicate(estudiante -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return estudiante.getCarnet().toLowerCase().contains(lowerCaseFilter)
                        || estudiante.getNombres().toLowerCase().contains(lowerCaseFilter)
                        || estudiante.getApellidos().toLowerCase().contains(lowerCaseFilter);
                });
            });
        }
    }

    private boolean validarCampos() {
        if (txtCarnet.getText().trim().isEmpty() ||
            txtNombres.getText().trim().isEmpty() ||
            txtApellidos.getText().trim().isEmpty() ||
            txtGrado.getText().trim().isEmpty() ||
            txtSeccion.getText().trim().isEmpty()) {
            
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Completa todos los campos obligatorios.");
            return false;
        }
        return true;
    }

    @FXML
    private void handleLimpiar() {
        txtCarnet.clear();
        txtNombres.clear();
        txtApellidos.clear();
        txtGrado.clear();
        txtSeccion.clear();
        estudianteSeleccionado = null;
        tblEstudiantes.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleVolver() {
        SceneManager.switchTo("dashboard-view.fxml", "Nova Solutions - Panel Principal", 800, 600);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}