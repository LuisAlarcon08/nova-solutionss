package main.java.edu.enriques.nova.solutionss.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public static Connection getConnectionDataBase() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + Credentials.HOST + ":" + Credentials.PORT + "/" + Credentials.DB + "?useSSL=false&serverTimezone=UTC";
            conexion = DriverManager.getConnection(url, Credentials.USER, Credentials.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}