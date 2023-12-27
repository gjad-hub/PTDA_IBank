package pt.ua.ibank.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://aws.connect.psdb.cloud/ibankdb?sslMode=VERIFY_IDENTITY"; 
    private static final String USER = "ha0ujyebgtdfvmayuzn1"; 
    private static final String PASS = "pscale_pw_8GbO0eJIOncOHuWNu928gQp7FVqzn1kpib3G81l8Uli";
    
//    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://.../IbankDB";
//    private static final String USER = "...";
//    private static final String PASS = "...";

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
        if (conn != null) {
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
