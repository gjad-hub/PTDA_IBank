package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Deposito;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Classe com metodos estáticos associados a operações feitas com Depositos
 * externos guardados em uma base de dados MySQL
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 27 de Dezembro, 2023
 */
public class DepositsDAO {

    /**
     * Função usada para fazer um pedido de Deposito
     *
     * @param valor          valor de deposito pedido
     * @param clienteRealiza clientID ID de Cliente usado como referencia
     * @return retorna codigo de sucesso, 1 Sucesso, 2 Erro email, 3 Erro SQL
     */
    public static int requestDeposit(double valor, int clienteRealiza) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "INSERT INTO deposito (valor, num_cli) "
            + "VALUES (?,?)"
    );

            stmt.setDouble(1, valor);
            stmt.setInt(2, clienteRealiza);
            stmt.execute();
            return CODIGO_SUCESSO;
        } catch (SQLException e) {
            return CODIGO_ERRO;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Função usada para obter uma lista de depositos através de um numero de
     * clientes
     *
     * @param num_cliente clientID ID de Cliente usado como referencia
     * @return retorna lista de depositos associado a esse cliente
     */
    public static ArrayList<Deposito> getDeposits(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Deposito> ldeposito = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "SELECT id_deposito,valor,aprovado,pendente_aprovacao,num_fun,num_cli FROM deposito WHERE num_cli = ? ORDER BY data desc;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Deposito tr;

                boolean isPending = rs.getBoolean("pendente_aprovacao");
                if (isPending) {
                    tr = new Deposito(rs.getInt("id_deposito"),
                                      rs.getDouble("valor"));
                } else {
                    tr = new Deposito(rs.getInt("id_deposito"),
                                      rs.getDouble("valor"),
                                      rs.getBoolean("aprovado"),
                                      rs.getInt("num_fun"),
                                      rs.getInt("num_cli")
            );
                }

                ldeposito.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return ldeposito.isEmpty() ? null : ldeposito;
    }

    /**
     * Função usada para aprovar um Deposito externo
     *
     * @param num_deposito    ID de Deposito usado como referencia
     * @param num_funcionario ID de Cliente usado como referencia
     * @return
     */
    public static boolean aproveDeposit(int num_deposito, int num_funcionario) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareCall(
            "{call aprovar_deposito(?,?)}");
            stmt.setInt(1, num_deposito);
            stmt.setInt(2, num_funcionario);
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
     * Função usada para rejeitar um pedido de Deposito externo
     *
     * @param num_deposito    ID de Deposito usado como referencia
     * @param num_funcionario ID de Cliente usado como referencia
     * @return
     */
    public static boolean denyDeposit(int num_deposito, int num_funcionario) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareCall(
            "{call reprovar_deposito(?,?)}");
            stmt.setInt(1, num_deposito);
            stmt.setInt(2, num_funcionario);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Função para obter o numero de depositos feitos pro um funcionário
     *
     * @param num_cliente clientID ID de Cliente usado como referencia
     * @return retorna numero de depositos associados a esse Cliente
     */
    public static int getDepositCount(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT count(*) AS qtd FROM deposito WHERE num_cli = ? and pendente_aprovacao = 1");
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

}
