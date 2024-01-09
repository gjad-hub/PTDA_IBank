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

    public static ArrayList<ModeratorComment> getCommentListByNumber(
            String clientNumber) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ModeratorComment> list = new ArrayList<>();

        try {

            stmt = conn.prepareStatement(
                    "select funcionario.nome,id_cliente,descricao,data_feita"
                    + " from comentario_perfil inner join funcionario"
                    + " where id_cliente = ?;");
            stmt.setString(1, clientNumber);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new ModeratorComment(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDate("data_feita")));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    public static boolean addNewComment(int client_id, String comment) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
                    "INSERT INTO comentario_perfil"
                    + "(id_Empregado,id_cliente,descricao) VALUES"
                    + "(?,?,?);"
            );

            //stmt.setInt(1, 1);
            stmt.setInt(2, client_id);
            stmt.setString(3, comment);

            return stmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    public static boolean deleteComment(int id) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
                    "delete from comentario_perfil where id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }
}
