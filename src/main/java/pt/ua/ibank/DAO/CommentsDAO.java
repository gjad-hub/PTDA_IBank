/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.ModeratorComment;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 *
 * @author ricar
 */
public class CommentsDAO {

    public static ArrayList<ModeratorComment> getCommentListByID(
            int clientID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ModeratorComment> list = new ArrayList<>();

        try {

            stmt = conn.prepareStatement(
            "select id,funcionario.nome as nome,descricao,data"
            + " from comentario_perfil inner join funcionario on funcionario.num_fun = comentario_perfil.id_empregado"
            + " where id_cliente = ?;");
            stmt.setInt(1, clientID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new ModeratorComment(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getTimestamp("data")));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    public static ArrayList<ModeratorComment> getModeratorCommentListByID(
            int clientID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ModeratorComment> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select id,funcionario.nome as nome,descricao,data"
            + " from comentario_perfil inner join funcionario on funcionario.num_fun = comentario_perfil.id_empregado"
            + " where id_cliente = ?;");
            stmt.setInt(1, clientID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new ModeratorComment(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getTimestamp("data")));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    public static boolean addNewComment(int employee_id, int client_id,
                                        String comment) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "INSERT INTO comentario_perfil"
            + "(id_empregado,id_cliente,descricao) VALUES"
            + "(?,?,?);"
    );

            stmt.setInt(1, employee_id);
            stmt.setInt(2, client_id);
            stmt.setString(3, comment);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
