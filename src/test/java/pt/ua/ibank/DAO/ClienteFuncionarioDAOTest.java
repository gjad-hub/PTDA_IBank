package pt.ua.ibank.DAO;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClienteFuncionarioDAOTest {

    @Test
    void testGetClientsInteractedListByID() {
        ArrayList<Integer> list = ClienteFuncionarioDAO.getClientsInteractedListByID(1);
        assertNotNull(list);
    }

    @Test
    void testGetClientsInteractedListByIDFalse() {
        ArrayList<Integer> list = ClienteFuncionarioDAO.getClientsInteractedListByID(9999);
        assertTrue(list.isEmpty());
    }
}