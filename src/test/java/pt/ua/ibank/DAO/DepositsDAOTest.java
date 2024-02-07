package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Deposit;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;

class DepositsDAOTest {

    @Test
    void testRequestDeposit() {
        int id = 1;
        int result = DepositsDAO.requestDeposit(200, id);
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    void testRequestDepositFalse() {
        int id = 99999;
        int result = DepositsDAO.requestDeposit(200, id);
        assertEquals(ERROR_CODE, result);
    }

    @Test
    void getDeposits() {
        int id = 1;
        ArrayList<Deposit> ldeposits = DepositsDAO.getDeposits(id);
        assertNotNull(ldeposits);
    }

    @Test
    void getDepositsFalse() {
        int id = 9999;
        ArrayList<Deposit> ldeposits = DepositsDAO.getDeposits(id);
        assertNull(ldeposits);
    }

    @Test
    void aproveDeposit() {
        int id_deposit = 6;
        int id = 1;
        boolean result = DepositsDAO.aproveDeposit(id_deposit, id);
        assertTrue(result);
    }

    @Test
    void aproveDepositFalse() {
        int id_deposit = 999999;
        int id = 1;
        boolean result = DepositsDAO.aproveDeposit(id_deposit, id);
        assertFalse(result);
    }

    @Test
    void denyDeposit() {
        int id_deposit = 7;
        int id = 1;
        boolean result = DepositsDAO.denyDeposit(id_deposit, id);
        assertTrue(result);
    }

    @Test
    void denyDepositFalse() {
        int id_deposit = 99999;
        int id = 1;
        boolean result = DepositsDAO.denyDeposit(id_deposit, id);
        assertTrue(result);
    }

    @Test
    void getDepositCount() {
        int id = 4;
        int result = DepositsDAO.getDepositCount(id);
        assertEquals(1, result);
    }

    @Test
    void getDepositCountFalse() {
        int id = 99999;
        int result = DepositsDAO.getDepositCount(id);
        assertEquals(0, result);
    }
}
