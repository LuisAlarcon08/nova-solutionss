package main.java.edu.enriques.nova.solutionss.dao;

import main.java.edu.enriques.nova.solutionss.config.DataBaseConnection;
import main.java.edu.enriques.nova.solutionss.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDao {

    public boolean insertar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante (carnet, nombres, apellidos, grado, seccion) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DataBaseConnection.getConnectionDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, estudiante.getCarnet());
            ps.setString(2, estudiante.getNombres());
            ps.setString(3, estudiante.getApellidos());
            ps.setString(4, estudiante.getGrado());
            ps.setString(5, estudiante.getSeccion());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Estudiante> listar() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";
        try (Connection con = DataBaseConnection.getConnectionDataBase();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setIdEstudiante(rs.getInt("id_estudiante"));
                e.setCarnet(rs.getString("carnet"));
                e.setNombres(rs.getString("nombres"));
                e.setApellidos(rs.getString("apellidos"));
                e.setGrado(rs.getString("grado"));
                e.setSeccion(rs.getString("seccion"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET carnet = ?, nombres = ?, apellidos = ?, grado = ?, seccion = ? WHERE id_estudiante = ?";
        try (Connection con = DataBaseConnection.getConnectionDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estudiante.getCarnet());
            ps.setString(2, estudiante.getNombres());
            ps.setString(3, estudiante.getApellidos());
            ps.setString(4, estudiante.getGrado());
            ps.setString(5, estudiante.getSeccion());
            ps.setInt(6, estudiante.getIdEstudiante());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idEstudiante) {
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        try (Connection con = DataBaseConnection.getConnectionDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEstudiante);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}