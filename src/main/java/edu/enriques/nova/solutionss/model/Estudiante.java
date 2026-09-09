package main.java.edu.enriques.nova.solutionss.model;

public class Estudiante {

    private int idEstudiante;
    private String carnet;
    private String nombres;
    private String apellidos;
    private String grado;
    private String seccion;

    // Constructor vacío
    public Estudiante() {
    }

    // Constructor para crear/insertar un nuevo estudiante (sin ID porque es autoincrementable)
    public Estudiante(String carnet, String nombres, String apellidos, String grado, String seccion) {
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.grado = grado;
        this.seccion = seccion;
    }

    // Constructor completo para recuperar, actualizar o listar desde la base de datos
    public Estudiante(int idEstudiante, String carnet, String nombres, String apellidos, String grado, String seccion) {
        this.idEstudiante = idEstudiante;
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.grado = grado;
        this.seccion = seccion;
    }

    // Getters y Setters
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos + " (" + carnet + ")";
    }
}