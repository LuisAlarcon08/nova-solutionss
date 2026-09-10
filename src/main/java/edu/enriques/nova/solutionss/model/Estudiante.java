package main.java.edu.enriques.nova.solutionss.model;

public class Estudiante {
    private int idEstudiante;
    private String carnet;
    private String nombre;
    private String apellido;
    private String grado;
    private String seccion;

    public Estudiante() {}

    public Estudiante(int idEstudiante, String carnet, String nombre, String apellido, String grado, String seccion) {
        this.idEstudiante = idEstudiante;
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.grado = grado;
        this.seccion = seccion;
    }

    public int getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(int idEstudiante) { this.idEstudiante = idEstudiante; }

    public String getCarnet() { return carnet; }
    public void setCarnet(String carnet) { this.carnet = carnet; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }

    public String getSeccion() { return seccion; }
    public void setSeccion(String seccion) { this.seccion = seccion; }
}