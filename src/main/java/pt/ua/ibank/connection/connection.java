package pt.ua.ibank.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connection {

    // Vai ficar para outro dia mas, em principio, vou fazer um ficheiro de configs, em vez de ficar aqui.
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1/IbankDB"; // DB no localhost, para test
    private static final String USER = "..."; // user local, por agora
    private static final String PASS = "..."; // password local, por agora

    public static final Connection conn = getConnection();

    private static Connection getConnection() {

        try {
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro ao Connectar: ", ex);
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar: " + e);
            }
        }
    }

    public static void closeConnection(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro fechar conexão: ", ex);
        }
    }

    public static void closeConnection(PreparedStatement stmt, ResultSet rs) {
        closeConnection(stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro fechar conexão: ", ex);
        }
    }
}
