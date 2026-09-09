package main.java.edu.enriques.nova.solutionss.model;

public class Usuario {
    private int id;
    private String nombreCompleto;
    private String usuario;
    private String email;
    private String password;
    private int rolId;

    public Usuario() {}

    public Usuario(String nombreCompleto, String usuario, String email, String password, int rolId) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.rolId = rolId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getRolId() { return rolId; }
    public void setRolId(int rolId) { this.rolId = rolId; }
}