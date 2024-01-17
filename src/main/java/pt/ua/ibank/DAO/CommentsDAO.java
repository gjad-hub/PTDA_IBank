package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.ModeratorComment;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Classe com metodos estáticos associados a operações feitas com Comentarios
 * externos guardados em uma base de dados MySQL
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 14 de Janeiro, 2024
 */
public class CommentsDAO {

    /**
     * Função usada para Obter uma lista de comentários através de um ID de um
     * cliente.
     *
     * @param clientID ID de Cliente usado como referencia
     * @return retorna lista de comentarios relacionado a esse Cliente
     */
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
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Função usada para Obter a lista de comentários de um Cliente através de
     * ID
     *
     * @param clientID ID de Cliente usado como referencia
     * @return lista de Comentarios associado a esse perfil
     */
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

            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     *
     * Função usada para adicionar um comentário a um Cliente externo
     *
     * @param employee_id ID de Funcionario usado como referencia
     * @param client_id   ID de Cliente usado como referencia
     * @param comment     Conteudo de Comentario
     * @return codigo de sucesso booleano
     */
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

    /**
     * Função usada para apagar um comentário através de um ID
     *
     * @param id clientID ID de Cliente usado como referencia
     * @return codigo de sucesso booleano
     */
    public static boolean deleteComment(int id) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "DELETE FROM comentario_perfil WHERE id = ?");
            stmt.setInt(1, id);
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
