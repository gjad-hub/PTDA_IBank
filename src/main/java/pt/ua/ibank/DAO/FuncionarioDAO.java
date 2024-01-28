package pt.ua.ibank.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Funcionario;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO_EMAIL;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Classe com metodos estáticos associados a operações feitas com Funcionários
 * externos guardados em uma base de dados MySQL
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 15 de Dezembro, 2023
 */
public class FuncionarioDAO {

    /**
     * Função para registar um funcionario
     *
     * @param nome     Nome de cliente Externo
     * @param morada   Morada de Cliente Externo
     * @param email    Email de Cliente externo
     * @param telefone Telemovel de Cliente Externo
     * @param nif      NIF ( Numero de Identificação fiscal ) Externo
     * @param password Password, guardada de forma encriptada
     * @param num_fun  Numero de Funcionario Externo
     * @return Codigo de Sucesso, 1 Sucesso, 2 Erro email, 3 Erro SQL
     */
    public static int CreateFuncionario(String nome, String morada, String email,
                                        String telefone, String nif,
                                        String password, Integer num_fun) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement(
            "SELECT count('num_fun') AS valor FROM funcionario where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("valor") > 0) {
                return CODIGO_ERRO_EMAIL;
            }

            stmt = conn.prepareStatement(
            "INSERT INTO funcionario (nome, morada, email, telemovel, nif, password,gerente) "
            + "VALUES (?, ?, ?, ?, ?, ?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, nif);
            stmt.setString(6, password);
            stmt.setInt(7, num_fun);
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
     * Função para retornar objeto Funcioario através de Email
     *
     * @param email String Email usada como Referencia
     * @return Retorna Funcionario relacionado a esse mail
     */
    public static Funcionario getFuncionarioByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario fun = null;
        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM funcionario where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                fun = new Funcionario(rs.getInt("num_fun"),
                                      rs.getString("nome"),
                                      rs.getString("morada"),
                                      rs.getString("email"),
                                      rs.getString("telemovel"),
                                      rs.getString("nif"),
                                      rs.getString("password"),
                                      rs.getInt("gerente"),
                                      rs.getBoolean("demitido")
        );
            }

            return fun;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função para retornar Funcionario através de ID
     *
     * @param id ID usado como referencia
     * @return Funcionario relacionado a esse ID
     */
    public static Funcionario getFuncionarioByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario fun = null;
        try {
            stmt = conn.prepareStatement(
            "SELECT num_fun,nome,morada,email,telemovel,nif,gerente,demitido,data_criacao "
            + "FROM funcionario where num_fun like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                fun = new Funcionario(rs.getInt("num_fun"),
                                      rs.getInt("gerente"),
                                      rs.getString("nome"),
                                      rs.getString("morada"),
                                      rs.getString("email"),
                                      rs.getString("telemovel"),
                                      rs.getString("nif"),
                                      rs.getBoolean("demitido"),
                                      rs.getDate("data_criacao"));
            }

            return fun;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função para retornar nome através de ID
     *
     * @param id ID de Cliente usado como referencia
     * @return Nome de Funcionario relacionado com esse ID
     */
    public static String getFuncionarioNomeByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.prepareStatement(
            "SELECT nome FROM funcionario where num_fun like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getString("nome");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função que retorna a lista de todos os Funcionarios
     *
     * @return Lista de Todos os Funcionarios
     */
    public static ArrayList<Funcionario> getFuncionarioList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT num_fun, nome, morada, email, telemovel, nif, gerente,demitido,data_criacao FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer numero = rs.getInt("num_fun");
                String nome = rs.getString("nome");
                String morada = rs.getString("morada");
                String email = rs.getString("email");
                String telemovel = rs.getString("telemovel");
                String nif = rs.getString("nif");
                Integer numGerente = rs.getInt("gerente");
                Boolean demitido = rs.getBoolean("demitido");
                Date dataCriada = rs.getDate("data_criacao");

                list.add(
                        new Funcionario(numero, numGerente, nome, morada, email,
                                        telemovel,
                                        nif, demitido,
                                        dataCriada
                        ));

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
     * Função que retorna funcionarios por morada
     *
     * @param address Morada usada como referencia
     * @return Funcionaios relacionados com essa Morada
     */
    public static ArrayList<Funcionario> getFuncionarioListByAddress(
            String address) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT num_fun, nome, morada, email, telemovel, nif, gerente,demitido FROM funcionario where morada like ?");
            stmt.setString(1, address);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer numero = rs.getInt("num_fun");
                String nome = rs.getString("nome");
                String morada = rs.getString("morada");
                String email = rs.getString("email");
                String telemovel = rs.getString("telemovel");
                String nif = rs.getString("nif");
                Integer numGerente = rs.getInt("gerente");
                Boolean demitido = rs.getBoolean("demitido");

                list.add(new Funcionario(numero, nome, morada, email, telemovel,
                                         nif, morada, numGerente, demitido
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
     * Função usada para promover Funcionario
     *
     * @param id ID de Funcionario usado como Referencia
     * @return retorna codigo de sucesso boleano
     */
    public static boolean promoverFuncionario(int id) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE funcionario SET gerente = NULL WHERE num_fun LIKE ? ");
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

    /**
     * Função usada para obter o ID de Funcionario através do Email
     *
     * @param email Email para ser usado como referencia
     * @return retorna ID de Funcionario
     */
    public static Integer getFuncionarioIdByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer num_fun = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT num_fun FROM funcionario where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                num_fun = rs.getInt("num_fun");
            }

            return num_fun;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função usada para obter um objeto Funcionario através de NIF
     *
     * @param id
     * @return
     */
    public static Funcionario getFuncionarioByNIF(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario cl = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM funcionario where nif like ?;");
            stmt.setString(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer numero = rs.getInt("num_fun");
                String nome = rs.getString("nome");
                String morada = rs.getString("morada");
                String email = rs.getString("email");
                String telemovel = rs.getString("telemovel");
                String nif = rs.getString("nif");
                Integer numGerente = rs.getInt("gerente");
                Boolean demitido = rs.getBoolean("demitido");

                return new Funcionario(numero, nome, morada, email, telemovel,
                                       nif, morada, numGerente, demitido
                );
            }
            return cl;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função usada para Atualizar os dados externos de um cliente
     *
     * @param num_funcionario Numero de Funcionario externo
     * @param nome            Nome de cliente Externo
     * @param morada          Morada de Cliente Externo
     * @param email           Email de Cliente externo
     * @param telefone        Telemovel de Cliente Externo
     * @param nif             NIF ( Numero de Identificação fiscal ) Externo
     * @return
     */
    public static boolean UpdateFuncionario(
            int num_funcionario, String nome,
            String morada, String email,
            String telefone, String nif) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE funcionario "
            + "SET nome = ? , morada = ?, email = ?, telemovel = ?,nif = ? "
            + "WHERE num_fun = ?");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, nif);
            stmt.setInt(6, num_funcionario);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     *
     * Função para actualizar dados externos de Funcionario
     *
     * @param nome      Nome de cliente Externo
     * @param morada    Morada de Cliente Externo
     * @param email     Email de Cliente externo
     * @param telemovel Telemovel de Cliente Externo
     * @param nif       NIF ( Numero de Identificação fiscal ) Externo
     * @param old_email
     * @return
     */
    public static int UpdateFuncionario(String nome, String morada, String email,
                                        String telemovel, String nif,
                                        String old_email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement(
            "SELECT count(num_fun) AS valor FROM funcionario where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("valor") > 0 && !email.equals(old_email)) {
                return CODIGO_ERRO_EMAIL;
            }

            stmt = conn.prepareStatement(
            "UPDATE funcionario SET nome = ? , morada = ?, email = ?, telemovel = ? WHERE email = ?");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telemovel);
            stmt.setString(5, old_email);
            stmt.execute();
            return CODIGO_SUCESSO;
        } catch (SQLException e) {
            return CODIGO_ERRO;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Função para actualizar informações externas de funcionario
     *
     * @param nome      Nome de cliente Externo
     * @param morada    Morada de Cliente Externo
     * @param email     Email de Cliente externo
     * @param telefone  Telemovel de Cliente Externo
     * @param nif       NIF ( Numero de Identificação fiscal ) Externo
     * @param password  Password, guardada de forma encriptada
     * @param old_email Email antigo, caso queira mudar o email currente
     * @return retorna Codigo de Sucesso, 1 Sucesso, 2 Erro Email, 3 Erro SQL
     */
    public static int UpdateFuncionario(String nome, String morada, String email,
                                        String telefone, String nif,
                                        String password, String old_email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement(
            "SELECT count(num_fun) AS valor FROM funcionario where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("valor") > 0 && !email.equals(old_email)) {
                return CODIGO_ERRO_EMAIL;
            }

            stmt = conn.prepareStatement(
            "UPDATE funcionario SET nome = ? , morada = ?, email = ?, telemovel = ?, password = ? WHERE email = ?");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, password);
            stmt.setString(6, old_email);
            stmt.execute();
            return CODIGO_SUCESSO;
        } catch (SQLException e) {
            return CODIGO_ERRO;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Função que retorna o numero total de contas criadas
     *
     * @return numero total de contas criadas
     */
    public static Integer getNumContasCriadasMes() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer numContasCriadas = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT COUNT(*) AS total_contas FROM cliente;");
            rs = stmt.executeQuery();
            if (rs.next()) {
                numContasCriadas = rs.getInt("total_contas");
            }
            return numContasCriadas;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função que retorna numero de depositos externos por aprovar
     *
     * @return numero de depositos por aprovar
     */
    public static Integer getNumDepositosPorAprovar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer numDepositosPorAprovar = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT COUNT(*) AS depositos_pendentes FROM deposito WHERE pendente_aprovacao = 1;");
            rs = stmt.executeQuery();
            if (rs.next()) {
                numDepositosPorAprovar = rs.getInt("depositos_pendentes");
            }
            return numDepositosPorAprovar;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Retorna Funcionário externo com melhor performance
     *
     * @return retorna Nome de funcionario com melhor performance
     */
    public static String getFuncionarioComMaisDepositosAprovados() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nomeFuncionarioMaisDepositos = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT num_fun, COUNT(*) AS total_aprovados FROM deposito WHERE aprovado = 1 GROUP BY num_fun ORDER BY total_aprovados DESC LIMIT 1;");
            rs = stmt.executeQuery();
            if (rs.next()) {
                int numFun = rs.getInt("num_fun");
                nomeFuncionarioMaisDepositos =
                getNomeFuncionarioByNumber(numFun);
            }
            return nomeFuncionarioMaisDepositos;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Retorna os numeros de depositos aprovados de um Funcionario externo.
     *
     * @param id ID de Funcionario usado como referencuia
     * @return retorna depositos autorizados do funcionario associado
     */
    public static String getFuncionarioNumDepositosAprovados(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT count(*) as numero from deposito where num_fun = ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("numero");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função que retorna o Nome de um Funcionario através do nome
     *
     * @param numFun ID de Funcionario usado como referencuia
     * @return Retorna Nome de Funcionario associado
     */
    private static String getNomeFuncionarioByNumber(int numFun) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT nome FROM funcionario WHERE num_fun = ?");
            stmt.setInt(1, numFun);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nome");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Função que retorna o nome do ultimo cliente registado
     *
     * @return retorna nome de ultimo cliente registado
     */
    public static String getNomeUltimaContaCriada() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nomeUltimaConta = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT nome FROM cliente ORDER BY num_cliente DESC LIMIT 1;");
            rs = stmt.executeQuery();

            if (rs.next()) {
                nomeUltimaConta = rs.getString("nome");
            }

            return nomeUltimaConta;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Função que retorna a data do ultimo pedido de deposito
     *
     * @return retorna a data do ultimo pedido formatada como String
     */
    public static String getDataUltimoPedidoDeposito() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Timestamp dataUltimoPedido = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT data FROM deposito ORDER BY data DESC LIMIT 1;");
            rs = stmt.executeQuery();

            if (rs.next()) {
                dataUltimoPedido = rs.getTimestamp("data");
            }

            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy HH:mm");

            return dataFormat.format(dataUltimoPedido);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Função que retorna o ID do funcionario com mais Aprovações
     *
     * @return retorna ID de funcionário com melhor desempenho
     */
    public static Integer getNumAprovacoesFuncionarioTop() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int totalAprovacoes = 0;

        try {
            stmt = conn.prepareStatement(
            "SELECT num_fun, COUNT(*) AS total_aprovacoes FROM deposito WHERE aprovado = 1 GROUP BY num_fun ORDER BY total_aprovacoes DESC LIMIT 1;");
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalAprovacoes = rs.getInt("total_aprovacoes");
            }

            return totalAprovacoes;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return totalAprovacoes;
    }

    /**
     * Função que demite um funcionário externo e proibe-o de entrar na conta
     *
     * @param numFuncionario ID de Funcionario usado como referencuia
     * @return retorna estado de sucesso booleano
     */
    public static boolean demitirFuncionario(int numFuncionario) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE funcionario SET demitido = TRUE WHERE num_fun = ?");
            stmt.setInt(1, numFuncionario);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Função que retorna se um Funcionario com ID externo foi demitido
     *
     * @param id ID de Funcionario usado como referencuia
     * @return retorna estado de sucesso booleano
     */
    public static boolean getFuncionarioDemitidoByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.prepareStatement(
            "SELECT demitido FROM funcionario where num_fun like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("demitido");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return false;
    }

}
