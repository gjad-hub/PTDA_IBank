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
 * Classe com metodos estáticos associados a operações feitas com Clientes e
 * Funcionarios externos guardados em uma base de dados MySQL
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 21 de Dezembro, 2023
 */
public class ClienteFuncionarioDAO {

    /**
     * Função usada para obter informações externas sobre interações de um
     * Funcionario
     *
     * @param funcionarioID ID de Funcionario usado como referencia
     * @return Lista de clientes que interagiram com esse Funcionario
     */
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
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }
}
