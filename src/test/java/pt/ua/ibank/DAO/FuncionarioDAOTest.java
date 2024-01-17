package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Funcionario;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;

class FuncionarioDAOTest {

    @Test
    public void testCreateFuncionarioSuccess() {
        int result = FuncionarioDAO.CreateFuncionario("Erling Haaland",
                                                      "Leeds, Inglaterra",
                                                      "haalandmc@gmail.com",
                                                      "983453618", "333444555",
                                                      "manchestercity9", 1);
        assertEquals(CODIGO_SUCESSO, result);
    }

    @Test
    public void testUpdateFuncionarioBasicInfoSuccess() {
        int result = FuncionarioDAO.UpdateFuncionario("Erling Haaland",
                                                      "Manchester, Inglaterra",
                                                      "haalandmc9@gmail.com",
                                                      "983453618", "333444555",
                                                      "haalandmc@gmail.com");
        assertEquals(CODIGO_SUCESSO, result);
    }

    @Test
    public void testUpdateFuncionarioWithPasswordSuccess() {
        int result = FuncionarioDAO.UpdateFuncionario("Erling Haaland",
                                                      "Manchester, Inglaterra",
                                                      "haalandmc9@gmail.com",
                                                      "983453618", "333444555",
                                                      "noruega9mc",
                                                      "haalandmc9@gmail.com");
        assertEquals(CODIGO_SUCESSO, result);
    }

    @Test
    public void testGetFuncionarioByEmail() {
        assertNotNull(FuncionarioDAO.getFuncionarioByEmail("admin@ibank.pt"));
    }

    @Test
    public void testGetFuncionarioList() {
        assertNotNull(FuncionarioDAO.getFuncionarioList());
    }

    @Test
    public void testGetFuncionarioByEmailNonExistent() {
        assertNull(FuncionarioDAO.getFuncionarioByEmail("messi10@gmail.com"));
    }

    @Test
    public void testGetFuncionarioByIDSuccess() {
        int validFuncionarioId = 1;
        Funcionario resultado = FuncionarioDAO.getFuncionarioByID(
                    validFuncionarioId);
        assertNotNull(resultado);
        assertEquals(1, resultado.numFun);
    }

    @Test
    public void testGetFuncionarioByIDNotFound() {
        int invalidFuncionarioId = 9999;
        Funcionario resultado = FuncionarioDAO.getFuncionarioByID(
                    invalidFuncionarioId);
        assertNull(resultado);
    }

    @Test
    public void testGetFuncionarioNomeByIDSuccess() {
        int validFuncionarioId = 1;
        String expectedName = "admin";
        String resultado = FuncionarioDAO.getFuncionarioNomeByID(
               validFuncionarioId);
        assertNotNull(resultado);
        assertEquals(expectedName, resultado);
    }

    @Test
    public void testGetFuncionarioNomeByIDNotFound() {
        int invalidFuncionarioId = 9999;
        String resultado = FuncionarioDAO.getFuncionarioNomeByID(
               invalidFuncionarioId);
        assertNull(resultado);
    }

    @Test
    public void testGetFuncionarioListSuccess() {
        ArrayList<Funcionario> resultado = FuncionarioDAO.getFuncionarioList();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
    }

    @Test
    public void testGetFuncionarioListByAddressSuccess() {
        String validAddress = "Avenida L, nº 111";
        ArrayList<Funcionario> resultado =
                               FuncionarioDAO.getFuncionarioListByAddress(
                                       validAddress);
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
    }

    @Test
    public void testGetFuncionarioListByAddressNotFound() {
        String invalidAddress = "Morada Incerta";
        ArrayList<Funcionario> resultado =
                               FuncionarioDAO.getFuncionarioListByAddress(
                                       invalidAddress);
        assertNull(resultado);
    }

    @Test
    public void testPromoverFuncionarioSuccess() {
        int idValido = 4; //funcionario que não é gerente ainda
        boolean resultado = FuncionarioDAO.promoverFuncionario(idValido);
        assertTrue(resultado);
    }

    @Test
    public void testPromoverFuncionarioGerente() {
        int idValido = 2; //funcionario que já é gerente
        boolean resultado = FuncionarioDAO.promoverFuncionario(idValido);
        assertTrue(resultado);
    }

    //AAAA
    @Test
    public void testGetFuncionarioIdByEmailSuccess() {
        String emailExistente = "admin@ibank.pt";
        Integer resultado = FuncionarioDAO.getFuncionarioIdByEmail(
                emailExistente);
        assertNotNull(resultado);
        assertEquals(1, resultado);
    }

    @Test
    public void testGetFuncionarioIdByEmailNotFound() {
        String emailInexistente = "messi10@gmail.com";
        Integer resultado = FuncionarioDAO.getFuncionarioIdByEmail(
                emailInexistente);
        assertNull(resultado);
    }

    @Test
    public void testGetFuncionarioByNIFSuccess() {
        String nifExistente = "123456789";
        Funcionario funcionario = FuncionarioDAO.getFuncionarioByNIF(
                    nifExistente);
        assertNotNull(funcionario);
        assertEquals(1, funcionario.numFun);
    }

    @Test
    public void testGetFuncionarioByNIFNotFound() {
        String nifInexistente = "899899899";
        Funcionario funcionario = FuncionarioDAO.getFuncionarioByNIF(
                    nifInexistente);
        assertNull(funcionario);
    }

    @Test
    public void testGetNumContasCriadasMesSuccess() {
        Integer numContasCriadas = FuncionarioDAO.getNumContasCriadasMes();
        assertNotNull(numContasCriadas);
        assertTrue(numContasCriadas >= 0);
    }

    @Test
    public void testGetNumDepositosPorAprovarSuccess() {
        Integer numDepositosPorAprovar =
                FuncionarioDAO.getNumDepositosPorAprovar();
        assertNotNull(numDepositosPorAprovar);
        assertTrue(numDepositosPorAprovar >= 0);
    }

    @Test
    public void testGetNomeFuncionarioComMaisDepositosAprovadosSuccess() {
        String nomeFuncionario =
               FuncionarioDAO.getFuncionarioComMaisDepositosAprovados();
        assertNotNull(nomeFuncionario);
        assertEquals("admin", nomeFuncionario);
    }

    @Test
    public void testGetFuncionarioNumDepositosAprovadosSuccess() {
        String numDepositos =
               FuncionarioDAO.getFuncionarioNumDepositosAprovados(2);
        assertNotNull(numDepositos);
        assertEquals(0, Integer.parseInt(numDepositos));
    }

    @Test
    public void testGetNomeUltimaContaCriadaSuccess() {
        String nomeUltimaConta = FuncionarioDAO.getNomeUltimaContaCriada();
        assertNotNull(nomeUltimaConta);
        assertEquals("Nuno Almeida", nomeUltimaConta);
    }

    @Test
    public void testGetDataUltimoPedidoDepositoSuccess() {
        String dataUltimoPedido = FuncionarioDAO.getDataUltimoPedidoDeposito();
        assertNotNull(dataUltimoPedido);
    }

    @Test
    public void testGetDataUltimoPedidoDepositoNoDeposits() {
        String dataUltimoPedido = FuncionarioDAO.getDataUltimoPedidoDeposito();
        assertNotNull(dataUltimoPedido);
    }

    @Test
    public void testGetNumAprovacoesFuncionarioTopSuccess() {
        int totalAprovacoes = FuncionarioDAO.getNumAprovacoesFuncionarioTop();
        assertTrue(totalAprovacoes > 0);
    }

    @Test
    public void testDemitirFuncionarioSuccess() {
        boolean resultado = FuncionarioDAO.demitirFuncionario(2);
        assertTrue(resultado);
    }

    @Test
    public void testDemitirFuncionarioNonExistent() {
        boolean resultado = FuncionarioDAO.demitirFuncionario(9999);
        assertFalse(resultado);
    }

    @Test
    public void testGetFuncionarioDemitidoByIDActive() { // funcionário que não foi demitido
        boolean resultado = FuncionarioDAO.getFuncionarioDemitidoByID(1);
        assertFalse(resultado);
    }

    @Test
    public void testGetFuncionarioDemitidoByIDDemitido() {
        boolean resultado = FuncionarioDAO.getFuncionarioDemitidoByID(2);
        assertTrue(resultado);
    }

    @Test
    public void testGetFuncionarioDemitidoByIDNonExistent() {
        boolean result = FuncionarioDAO.getFuncionarioDemitidoByID(9999);
        assertFalse(result);
    }
}
