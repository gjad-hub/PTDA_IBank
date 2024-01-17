package pt.ua.ibank.DAO;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClienteFuncionarioDAOTest {

    @Test
    void getClientsInteractedListByID() {
        ArrayList<Integer> list = ClienteFuncionarioDAO.getClientsInteractedListByID(1);
        assertNotNull(list);
    }
}