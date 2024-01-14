/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 *
 * @author ricar
 */
public class ClienteFuncionarioDAO {

    public static ArrayList<Integer> getClientsInteractedListByID(
            int funcionarioID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select num_cli from funcionario_cliente where num_fun = ?;");
            stmt.setInt(1, funcionarioID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("num_cli"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }
}
