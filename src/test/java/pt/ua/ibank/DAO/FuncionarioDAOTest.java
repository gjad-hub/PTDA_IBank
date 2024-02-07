package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Employee;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;

class FuncionarioDAOTest {

    @Test
    public void testCreateFuncionarioSuccess() {
        int result = EmployeeDAO.CreateFuncionario("Erling Haaland",
                                                      "Leeds, Inglaterra",
                                                      "haalandmc@gmail.com",
                                                      "983453618", "333444555",
                                                      "manchestercity9", 1);
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    public void testUpdateFuncionarioBasicInfoSuccess() {
        int result = EmployeeDAO.UpdateFuncionario("Erling Haaland",
                                                      "Manchester, Inglaterra",
                                                      "haalandmc9@gmail.com",
                                                      "983453618", "333444555",
                                                      "haalandmc@gmail.com");
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    public void testUpdateFuncionarioWithPasswordSuccess() {
        int result = EmployeeDAO.UpdateFuncionario("Erling Haaland",
                                                      "Manchester, Inglaterra",
                                                      "haalandmc9@gmail.com",
                                                      "983453618", "333444555",
                                                      "noruega9mc",
                                                      "haalandmc9@gmail.com");
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    public void testGetFuncionarioByEmail() {
        assertNotNull(EmployeeDAO.getFuncionarioByEmail("admin@ibank.pt"));
    }

    @Test
    public void testGetFuncionarioList() {
        assertNotNull(EmployeeDAO.getFuncionarioList());
    }

    @Test
    public void testGetFuncionarioByEmailNonExistent() {
        assertNull(EmployeeDAO.getFuncionarioByEmail("messi10@gmail.com"));
    }

    @Test
    public void testGetFuncionarioByIDSuccess() {
        int validFuncionarioId = 1;
        Employee resultado = EmployeeDAO.getFuncionarioByID(
                    validFuncionarioId);
        assertNotNull(resultado);
        assertEquals(1, resultado.numFun);
    }

    @Test
    public void testGetFuncionarioByIDNotFound() {
        int invalidFuncionarioId = 9999;
        Employee resultado = EmployeeDAO.getFuncionarioByID(
                    invalidFuncionarioId);
        assertNull(resultado);
    }

    @Test
    public void testGetFuncionarioNomeByIDSuccess() {
        int validFuncionarioId = 1;
        String expectedName = "admin";
        String resultado = EmployeeDAO.getFuncionarioNomeByID(
               validFuncionarioId);
        assertNotNull(resultado);
        assertEquals(expectedName, resultado);
    }

    @Test
    public void testGetFuncionarioNomeByIDNotFound() {
        int invalidFuncionarioId = 9999;
        String resultado = EmployeeDAO.getFuncionarioNomeByID(
               invalidFuncionarioId);
        assertNull(resultado);
    }

    @Test
    public void testGetFuncionarioListSuccess() {
        ArrayList<Employee> resultado = EmployeeDAO.getFuncionarioList();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
    }

    @Test
    public void testGetFuncionarioListByAddressSuccess() {
        String validAddress = "Avenida L, nº 111";
        ArrayList<Employee> resultado =
                               EmployeeDAO.getFuncionarioListByAddress(
                                       validAddress);
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
    }

    @Test
    public void testGetFuncionarioListByAddressNotFound() {
        String invalidAddress = "Morada Incerta";
        ArrayList<Employee> resultado =
                               EmployeeDAO.getFuncionarioListByAddress(
                                       invalidAddress);
        assertNull(resultado);
    }

    @Test
    public void testPromoverFuncionarioSuccess() {
        int idValido = 4; //funcionario que não é gerente ainda
        boolean resultado = EmployeeDAO.promoverFuncionario(idValido);
        assertTrue(resultado);
    }

    @Test
    public void testPromoverFuncionarioGerente() {
        int idValido = 2; //funcionario que já é gerente
        boolean resultado = EmployeeDAO.promoverFuncionario(idValido);
        assertTrue(resultado);
    }

    @Test
    public void testGetFuncionarioIdByEmailSuccess() {
        String emailExistente = "admin@ibank.pt";
        Integer resultado = EmployeeDAO.getFuncionarioIdByEmail(
                emailExistente);
        assertNotNull(resultado);
        assertEquals(1, resultado);
    }

    @Test
    public void testGetFuncionarioIdByEmailNotFound() {
        String emailInexistente = "messi10@gmail.com";
        Integer resultado = EmployeeDAO.getFuncionarioIdByEmail(
                emailInexistente);
        assertNull(resultado);
    }

    @Test
    public void testGetFuncionarioByNIFSuccess() {
        String nifExistente = "123456789";
        Employee funcionario = EmployeeDAO.getFuncionarioByNIF(
                    nifExistente);
        assertNotNull(funcionario);
        assertEquals(1, funcionario.numFun);
    }

    @Test
    public void testGetFuncionarioByNIFNotFound() {
        String nifInexistente = "899899899";
        Employee funcionario = EmployeeDAO.getFuncionarioByNIF(
                    nifInexistente);
        assertNull(funcionario);
    }

    @Test
    public void testGetNumContasCriadasMesSuccess() {
        Integer numContasCriadas = EmployeeDAO.getNumContasCriadasMes();
        assertNotNull(numContasCriadas);
        assertTrue(numContasCriadas >= 0);
    }

    @Test
    public void testGetNumDepositosPorAprovarSuccess() {
        Integer numDepositosPorAprovar =
                EmployeeDAO.getNumDepositosPorAprovar();
        assertNotNull(numDepositosPorAprovar);
        assertTrue(numDepositosPorAprovar >= 0);
    }

    @Test
    public void testGetNomeFuncionarioComMaisDepositosAprovadosSuccess() {
        String nomeFuncionario =
               EmployeeDAO.getFuncionarioComMaisDepositosAprovados();
        assertNotNull(nomeFuncionario);
        assertEquals("admin", nomeFuncionario);
    }

    @Test
    public void testGetFuncionarioNumDepositosAprovadosSuccess() {
        String numDepositos =
               EmployeeDAO.getFuncionarioNumDepositosAprovados(2);
        assertNotNull(numDepositos);
        assertEquals(0, Integer.parseInt(numDepositos));
    }

    @Test
    public void testGetNomeUltimaContaCriadaSuccess() {
        String nomeUltimaConta = EmployeeDAO.getNomeUltimaContaCriada();
        assertNotNull(nomeUltimaConta);
        assertEquals("João Tomás Silva", nomeUltimaConta);
    }

    @Test
    public void testGetDataUltimoPedidoDepositoSuccess() {
        String dataUltimoPedido = EmployeeDAO.getDataUltimoPedidoDeposito();
        assertNotNull(dataUltimoPedido);
    }

    @Test
    public void testGetDataUltimoPedidoDepositoNoDeposits() {
        String dataUltimoPedido = EmployeeDAO.getDataUltimoPedidoDeposito();
        assertNotNull(dataUltimoPedido);
    }

    @Test
    public void testGetNumAprovacoesFuncionarioTopSuccess() {
        int totalAprovacoes = EmployeeDAO.getNumAprovacoesFuncionarioTop();
        assertTrue(totalAprovacoes > 0);
    }

    @Test
    public void testDemitirFuncionarioSuccess() {
        boolean resultado = EmployeeDAO.demitirFuncionario(2);
        assertTrue(resultado);
    }

    @Test
    public void testDemitirFuncionarioNonExistent() {
        boolean resultado = EmployeeDAO.demitirFuncionario(9999);
        assertFalse(resultado);
    }

    @Test
    public void testGetFuncionarioDemitidoByIDActive() { 
        boolean resultado = EmployeeDAO.getFuncionarioDemitidoByID(1);
        assertFalse(resultado);
    }

    @Test
    public void testGetFuncionarioDemitidoByIDDemitido() {
        boolean resultado = EmployeeDAO.getFuncionarioDemitidoByID(2);
        assertTrue(resultado);
    }

    @Test
    public void testGetFuncionarioDemitidoByIDNonExistent() {
        boolean result = EmployeeDAO.getFuncionarioDemitidoByID(9999);
        assertFalse(result);
    }
}
