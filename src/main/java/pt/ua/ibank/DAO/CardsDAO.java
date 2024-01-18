package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Cartao;
import static pt.ua.ibank.utilities.CardGenerator.generateCardNumber;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Classe com metodos estáticos associados a operações feitas com Cartões
 * guardados em uma base de dados MySQL
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 25 de Dezembro, 2023
 */
public class CardsDAO {

    /**
     * Função usada para obter um cartão através de um Numero de cartão
     *
     * @param number numero do cartão a ser procurado
     * @return Objeto do Cartão associado, null se não encontrado.
     */
    public static Cartao getCardByNumber(String number) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cartao cartao = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM cartao where num_cartao like ?;");
            stmt.setString(1, number);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cartao = new Cartao(
                rs.getString("num_cartao"),
                rs.getTimestamp("data_validade"),
                rs.getInt("cliente"));
            }

            return cartao;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função usada para obter uma lista de cartões através de um ID
     *
     * @param num_cliente ID do Cliente usado como refencia para obter cartões
     * @return Rertorna lista de Cartões associada ao Cliente
     */
    public static ArrayList<Cartao> getCardListFromUserID(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cartao> lcartao = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "SELECT num_cartao, data_validade, estado FROM cartao WHERE cliente = ?;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cartao tr = new Cartao(rs.getString("num_cartao"),
                                       rs.getTimestamp("data_validade"),
                                       rs.getString("estado"));
                lcartao.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return lcartao.isEmpty() ? null : lcartao;
    }

    /**
     * Função usada para obter quantidade cartões de um cliente
     *
     * @param num_cliente ID do Cliente usado como refencia para obter lista
     *                    de cartões.
     * @return Retorna quantidade de cartões associados a esse cleinte
     */
    public static int getCardAmountByID(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT count(*) as qtd FROM cartao WHERE cliente = ?;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getInt("qtd");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return -1;
    }

    /**
     * Função usada para cancelar um cartão
     *
     * @param card_number
     * @return Codigo de sucesso, 1 se sucesso, 2 se erro
     */
    public static int cancelCard(String card_number) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE cartao SET estado = \"Cancelado\" WHERE num_cartao LIKE ? ");
            stmt.setString(1, card_number);
            stmt.execute();

            return CODIGO_SUCESSO;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return CODIGO_ERRO;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Função usada para tornar um cartão de um cliente como predefinido
     *
     * @param card_number numero do cartão a ser procurado
     * @param num_cliente ID do Cliente usado como refencia para obter lista
     * @return
     */
    public static int makeDefault(String card_number, int num_cliente) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE cliente SET cartao_default = ? WHERE num_cliente = ?");
            stmt.setString(1, card_number);
            stmt.setInt(2, num_cliente);
            stmt.execute();

            return CODIGO_SUCESSO;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return CODIGO_ERRO;
        } finally {
            DBConnection.closeConnection(stmt);
        }

    }

    /**
     * Função usada para criar um cartão
     *
     * @param num_cliente ID do Cliente usado como refencia para obter lista
     * @return retorna codigo de Sucesso
     */
    public static int createCard(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String num_cartao;

        try {
            // Gera um novo cartao até não existir nenhum cartao com este numero
            do {
                num_cartao = generateCardNumber();
                stmt = conn.prepareStatement(
                "SELECT count(num_cartao) AS valor FROM cartao where num_cartao like ?;");
                stmt.setString(1, num_cartao);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("valor") != 0);
            stmt.close();

            stmt = conn.prepareStatement(
            "INSERT INTO cartao (num_cartao, data_validade, estado, cliente) "
            + "VALUES (?, (SELECT DATE_ADD(CURDATE(), INTERVAL +5 YEAR )), \"Ativo\", ?);");
            stmt.setString(1, num_cartao);
            stmt.setInt(2, num_cliente);
            stmt.execute();

            return CODIGO_SUCESSO;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return CODIGO_ERRO;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }
}
