package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.PagamentoServicosCompras;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Classe com metodos estáticos associados a operações feitas com Pagamentos
 * externos guardados em uma base de dados MySQL
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 12 de Janeiro, 2024
 */
public class PaymentsDAO {

    /**
     * Função usada para pagar um serviço ou compra
     *
     * @param clienteRealiza ID de Cliente Externo que realizou a transferencia
     * @param entidade       Entidade de transferencia com ID Externo
     * @param referencia     ID de Referencia Externa
     * @return recorna codigo de erro, 1 = Sucesso, 2 = Erro
     */
    public static int payService(int clienteRealiza, int entidade,
                                 int referencia) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "UPDATE pagamento_servicos_compras SET pago = ?, cliente = ? WHERE entidade = ? and referencia = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, clienteRealiza);
            stmt.setInt(3, entidade);
            stmt.setInt(4, referencia);
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
     * Função que retorna a lista de Serviços e compras através de entidade e
     * referencia
     *
     * @param ref referencia de serviço e compra usado como referencia
     * @param ent entidade de serviço e compra usado como referencia
     * @return retorna Objeto de ServicosCompras relacionado com refencia e
     *         entidade
     */
    public static PagamentoServicosCompras getServicosCompras(int ref, int ent) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        PagamentoServicosCompras service = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT * FROM  pagamento_servicos_compras WHERE referencia = ? AND entidade = ?;");
            stmt.setInt(1, ref);
            stmt.setInt(2, ent);
            rs = stmt.executeQuery();

            while (rs.next()) {
                service = new PagamentoServicosCompras(rs.getInt("referencia"),
                                                       rs.getInt("entidade"),
                                                       rs.getDouble("valor"),
                                                       rs.getBoolean("pago"),
                                                       rs.getInt("cliente"),
                                                       rs.getInt("cliente_cria"),
                                                       rs.getBoolean("cancelada"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return service;
    }

    /**
     * Função usada para criar um pagamento ligado a um cliente, entidade e
     * referencia com valor a ser pago
     *
     * @param cliente Cliente que pagou a referencia
     * @param ent     Entidade gerada para o pagamento
     * @param ref     Referencia usada para o pagamento
     * @param valor   valor a ser cobrado pelo cliente
     * @return retorna o estado de sucesso, 1 se positivo, 2 se erro
     */
    public static int createPayment(int cliente, int ent, int ref, double valor) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "INSERT INTO pagamento_servicos_compras (entidade, referencia, valor, pago, cliente_cria, cancelada)"
            + "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, ent);
            stmt.setInt(2, ref);
            stmt.setDouble(3, valor);
            stmt.setBoolean(4, false);
            stmt.setInt(5, cliente);
            stmt.setBoolean(6, false);
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
     * Função usada para obter todos os serviços externos relacionados a um ID
     *
     * @param num_cliente ID de Cliente associado a uma lista externa de
     *                    serviços
     * @return Retorna lista associada a um Cliente ID
     */
    public static ArrayList<PagamentoServicosCompras> getAllServicos(
            int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<PagamentoServicosCompras> lservices = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "SELECT * FROM pagamento_servicos_compras WHERE cliente_cria = ? ORDER BY data_cri DESC;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PagamentoServicosCompras tr = new PagamentoServicosCompras(
                                         rs.getInt("referencia"),
                                         rs.getInt("entidade"),
                                         rs.getDouble("valor"),
                                         rs.getBoolean("pago"),
                                         rs.getInt("cliente"),
                                         rs.getInt("cliente_cria"),
                                         rs.getBoolean("cancelada")
                                 );
                lservices.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return lservices.isEmpty() ? null : lservices;
    }

    /**
     * Função usada para cancelar um pagamento externo
     *
     * @param ent Entidade gerada para o pagamento
     * @param ref Referencia usada para o pagamento
     * @return retorna o estado de sucesso, 1 se positivo, 2 se erro
     */
    public static int cancelPayment(int ent, int ref) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE pagamento_servicos_compras SET cancelada = ? WHERE entidade = ? AND referencia = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, ent);
            stmt.setInt(3, ref);

            stmt.execute();

            return CODIGO_SUCESSO;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return CODIGO_ERRO;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
