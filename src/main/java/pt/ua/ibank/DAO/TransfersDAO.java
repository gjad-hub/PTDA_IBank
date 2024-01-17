package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Transferencias;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Classe com metodos estáticos associados a operações feitas com Pagamentos
 * externos guardados em uma base de dados MySQL
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 15 de Janeiro, 2024
 */
public class TransfersDAO {

    /**
     * Função para
     *
     * @param valor          valor da transferencia
     * @param clienteRealiza ID de Cliente que realiza a Transferencia
     * @param clienteRecebe  ID de Clience que recebe a Transferencia
     * @param motivo         conteudo / Descrição de transferencia feita
     * @return retorna codigo de sucesso, 1 se bem sucedido, 2 caso não
     */
    public static int doTransfer(double valor, int clienteRealiza,
                                 int clienteRecebe, String motivo) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareCall("{call fazer_transferencia(?,?,?,?)}");
            stmt.setInt(1, clienteRealiza);
            stmt.setInt(2, clienteRecebe);
            stmt.setDouble(3, valor);
            stmt.setString(4, motivo);
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
     * Função usada para obter lista de transferencias externa
     *
     * @return Lista de tranferencias
     */
    public static ArrayList<Transferencias> getTransfersList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transferencias> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                ));
            }

            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função que retorna lista de tranferencias através de ID
     *
     * @param id ID de Cliente para ser usado como referencia
     * @return
     */
    public static Transferencias getTransferByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia where id_transferencia like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função que retorna lista de Transferencias através de um ID
     *
     * @param id ID de Cliente para ser usado como referencia
     * @return retorna lista de transações feitas por um gerentes
     */
    public static ArrayList<Transferencias> getClientTransfersList(
            int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transferencias> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia where cliente_realiza like ? or cliente_recebe like ?;");
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                ));
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
     * Função que retorna a lista de transferencias feitas por um Cliente través
     * de ID
     *
     * @param id ID de Cliente para ser usado como referencia
     * @return retorna lista de transferencias feitas por um Cliente
     */
    public static ArrayList<Transferencias> getTransfersListByAutor(
            int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transferencias> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia where cliente_realiza like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                ));
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
     * Função que retorna a lista de transferencias recebidas por um Cliente
     * através de ID
     *
     * @param id ID de Cliente para ser usado como referencia
     * @return retorna lista de transferencias que um Cliente recebeu
     */
    public static ArrayList<Transferencias> getTransfersListByReceptor(
            int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transferencias> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia where cliente_recebe like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                ));
            }

            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }
}
