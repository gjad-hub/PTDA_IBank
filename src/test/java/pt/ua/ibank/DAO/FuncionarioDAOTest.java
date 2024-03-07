package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Employee;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;

class EmployeeDAOTest {

    @Test
    public void testCreateEmployeeSuccess() {
        int result = EmployeeDAO.createEmployee("Erling Haaland",
                                                "Leeds, England",
                                                "haalandmc@gmail.com",
                                                "983453618", "333444555",
                                                "manchestercity9", 1);
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    public void testUpdateEmployeeWithPasswordSuccess() {
        int result = EmployeeDAO.updateEmployee("Erling Haaland",
                                                "Manchester, England",
                                                "haalandmc9@gmail.com",
                                                "983453618", "333444555",
                                                "norway9mc",
                                                "haalandmc9@gmail.com");
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    public void testGetEmployeeByEmail() {
        assertNotNull(EmployeeDAO.getEmployeeByEmail("admin@ibank.pt"));
    }

    @Test
    public void testGetEmployeeList() {
        assertNotNull(EmployeeDAO.getEmployeeList());
    }

    @Test
    public void testGetEmployeeByEmailNonExistent() {
        assertNull(EmployeeDAO.getEmployeeByEmail("messi10@gmail.com"));
    }

    @Test
    public void testGetEmployeeByIDSuccess() {
        int validEmployeeId = 1;
        Employee result = EmployeeDAO.getEmployeeByID(validEmployeeId);
        assertNotNull(result);
        assertEquals(1, result.empNum);
    }

    @Test
    public void testGetEmployeeByIDNotFound() {
        int invalidEmployeeId = 9999;
        Employee result = EmployeeDAO.getEmployeeByID(invalidEmployeeId);
        assertNull(result);
    }

    @Test
    public void testGetEmployeeNameByIDSuccess() {
        int validEmployeeId = 1;
        String expectedName = "admin";
        String result = EmployeeDAO.getEmployeeNameByID(validEmployeeId);
        assertNotNull(result);
        assertEquals(expectedName, result);
    }

    @Test
    public void testGetEmployeeNameByIDNotFound() {
        int invalidEmployeeId = 9999;
        String result = EmployeeDAO.getEmployeeNameByID(invalidEmployeeId);
        assertNull(result);
    }

    @Test
    public void testGetEmployeeListSuccess() {
        ArrayList<Employee> result = EmployeeDAO.getEmployeeList();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetEmployeeListByAddressSuccess() {
        String validAddress = "Avenida L, nº 111";
        ArrayList<Employee> result =
                            EmployeeDAO.getEmployeeListByAddress(validAddress);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testPromoteEmployeeSuccess() {
        int validId = 4; // Employee who is not a manager yet
        boolean result = EmployeeDAO.promoteEmployee(validId);
        assertTrue(result);
    }

    @Test
    public void testPromoteEmployeeManager() {
        int validId = 2; // Employee who is already a manager
        boolean result = EmployeeDAO.promoteEmployee(validId);
        assertTrue(result);
    }

    @Test
    public void testGetEmployeeByNIFSuccess() {
        String existingNIF = "123456789";
        Employee employee = EmployeeDAO.getEmployeeByNIF(existingNIF);
        assertNotNull(employee);
        assertEquals(1, employee.empNum);
    }

    @Test
    public void testGetEmployeeByNIFNotFound() {
        String nonExistingNIF = "899899899";
        Employee employee = EmployeeDAO.getEmployeeByNIF(nonExistingNIF);
        assertNull(employee);
    }

    @Test
    public void testGetNumContasCriadasMesSuccess() {
        Integer numAccountsCreated =
                EmployeeDAO.getTotalNumberOfCreatedAccounts();
        assertNotNull(numAccountsCreated);
        assertTrue(numAccountsCreated >= 0);
    }

    @Test
    public void testGetNumDepositosPorAprovarSuccess() {
        Integer numDepositsToApprove = EmployeeDAO.getPendingDepositsCount();
        assertNotNull(numDepositsToApprove);
        assertTrue(numDepositsToApprove >= 0);
    }

    @Test
    public void testGetFuncionarioComMaisDepositosAprovadosSuccess() {
        String employeeName =
               EmployeeDAO.getEmployeeWithMostApprovedDeposits();
        assertNotNull(employeeName);
        assertEquals("admin", employeeName);
    }

    @Test
    public void testGetFuncionarioNumDepositosAprovadosSuccess() {
        String numDeposits =
               EmployeeDAO.getEmployeeNameByID(2);
        assertNotNull(numDeposits);
        assertEquals(0, Integer.parseInt(numDeposits));
    }

    @Test
    public void testGetNomeUltimaContaCriadaSuccess() {
        String lastAccountName = EmployeeDAO.getLastNameOfLastCreatedAccount();
        assertNotNull(lastAccountName);
        assertEquals("João Tomás Silva", lastAccountName);
    }

    @Test
    public void testGetDataUltimoPedidoDepositoSuccess() {
        String lastDepositRequestDate =
               EmployeeDAO.getLastDepositRequestDate();
        assertNotNull(lastDepositRequestDate);
    }

    @Test
    public void testGetDataUltimoPedidoDepositoNoDeposits() {
        String lastDepositRequestDate =
               EmployeeDAO.getLastDepositRequestDate();
        assertNotNull(lastDepositRequestDate);
    }

    @Test
    public void testGetNumAprovacoesFuncionarioTopSuccess() {
        int totalApprovals = EmployeeDAO.getPendingDepositsCount();
        assertTrue(totalApprovals > 0);
    }

    @Test
    public void testDemitirFuncionarioSuccess() {
        boolean result = EmployeeDAO.dismissEmployee(2);
        assertTrue(result);
    }

    @Test
    public void testDemitirFuncionarioNonExistent() {
        boolean result = EmployeeDAO.dismissEmployee(9999);
        assertFalse(result);
    }

    @Test
    public void testIsEmployeeDismissedByIDActive() {
        boolean result = EmployeeDAO.isEmployeeDismissedByID(1);
        assertFalse(result);
    }

    @Test
    public void testIsEmployeeDismissedByIDDismissed() {
        boolean result = EmployeeDAO.isEmployeeDismissedByID(2);
        assertTrue(result);
    }

    @Test
    public void testIsEmployeeDismissedByIDNonExistent() {
        boolean result = EmployeeDAO.isEmployeeDismissedByID(9999);
        assertFalse(result);
    }
}
