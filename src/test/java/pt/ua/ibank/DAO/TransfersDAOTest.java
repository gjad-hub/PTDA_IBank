package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Transfers;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;

class TransfersDAOTest {

    @Test
    void doTransfer() {
        int result = TransfersDAO.doTransfer(20, 1, 2, "TESTE");
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    void getTransfersList() {
        ArrayList<Transfers> ltransfers = TransfersDAO.getTransfersList();
        assertNotNull(ltransfers);
        assertFalse(ltransfers.isEmpty());
    }

    @Test
    void getTransferByID() {
        Transfers transfer = TransfersDAO.getTransferByID(1);
        assertNotNull(transfer);
    }

    @Test
    void getTransferByIDFalse() {
        Transfers transfer = TransfersDAO.getTransferByID(9999999);
        assertNull(transfer);
    }

    @Test
    void getClientTransfersList() {
        ArrayList<Transfers> ltransfers =
                             TransfersDAO.getClientTransfersList(1);
        assertNotNull(ltransfers);
        assertFalse(ltransfers.isEmpty());
    }

    @Test
    void getClientTransfersListFalse() {
        ArrayList<Transfers> ltransfers =
                             TransfersDAO.getClientTransfersList(99999);
        assertNull(ltransfers);
    }

    @Test
    void getTransfersListByAutor() {
        ArrayList<Transfers> ltransfers =
                             TransfersDAO.getTransfersListByAuthor(1);
        assertNotNull(ltransfers);
        assertFalse(ltransfers.isEmpty());
    }

    @Test
    void getTransfersListByAutorFalse() {
        ArrayList<Transfers> ltransfers =
                             TransfersDAO.getTransfersListByAuthor(99999);
        assertNull(ltransfers);
    }

    @Test
    void testGetTransfersListByReceptor() {
        ArrayList<Transfers> transfersList =
                             TransfersDAO.getTransfersListByRecipient(1);
        assertNotNull(transfersList);
        assertFalse(transfersList.isEmpty());
    }

    @Test
    void testGetTransfersListByReceptorFalse() {
        ArrayList<Transfers> transfersList =
                             TransfersDAO.getTransfersListByRecipient(999999);
        assertNull(transfersList);
    }
}
