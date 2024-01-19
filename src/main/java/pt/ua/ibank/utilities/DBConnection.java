package pt.ua.ibank.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://estga-dev.ua.pt/PTDA_BD_001";
    private static final String USER = "PTDA_001";
    private static final String PASS = "Kiyt684h";

    public static final Connection conn = getConnection();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException | NoClassDefFoundError ex) {
            System.out.println("YO");
        }
    }

    private static Connection getConnection() {

        try {
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | RuntimeException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                                          "Conexão á base de dados não foi possivel!");
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
        } catch (SQLException | NoClassDefFoundError ex) {
            throw new RuntimeException("Erro fechar conexão: ", ex);
        }
    }
}
