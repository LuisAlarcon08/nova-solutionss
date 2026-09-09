package main.java.edu.enriques.nova.solutionss.dao;

import main.java.edu.enriques.nova.solutionss.config.DataBaseConnection;
import main.java.edu.enriques.nova.solutionss.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    public Usuario login(String userOrEmail, String password) {
        String sql = "SELECT * FROM usuarios WHERE (usuario = ? OR email = ?) AND password = ?";
        try (Connection conn = DataBaseConnection.getConnectionDataBase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, userOrEmail);
            pstmt.setString(2, userOrEmail);
            pstmt.setString(3, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombreCompleto(rs.getString("nombre_completo"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRolId(rs.getInt("rol_id"));
                    return usuario;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registrar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre_completo, usuario, email, password, rol_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnectionDataBase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getNombreCompleto());
            pstmt.setString(2, usuario.getUsuario());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getPassword());
            pstmt.setInt(5, usuario.getRolId());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}