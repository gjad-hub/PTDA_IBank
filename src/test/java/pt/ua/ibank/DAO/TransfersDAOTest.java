package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Transferencias;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;

class TransfersDAOTest {

    @Test
    void doTransfer() {
        int result = TransfersDAO.doTransfer(20, 1, 2, "TESTE");
        assertEquals(CODIGO_SUCESSO, result);
    }

    @Test
    void getTransfersList() {
        ArrayList<Transferencias> ltransfers = TransfersDAO.getTransfersList();
        assertNotNull(ltransfers);
        assertFalse(ltransfers.isEmpty());
    }

    @Test
    void getTransferByID() {
        Transferencias transfer = TransfersDAO.getTransferByID(1);
        assertNotNull(transfer);
    }

    @Test
    void getTransferByIDFalse() {
        Transferencias transfer = TransfersDAO.getTransferByID(9999999);
        assertNull(transfer);
    }

    @Test
    void getClientTransfersList() {
        ArrayList<Transferencias> ltransfers =
                                  TransfersDAO.getClientTransfersList(1);
        assertNotNull(ltransfers);
        assertFalse(ltransfers.isEmpty());
    }

    @Test
    void getClientTransfersListFalse() {
        ArrayList<Transferencias> ltransfers =
                                  TransfersDAO.getClientTransfersList(99999);
        assertNull(ltransfers);
    }

    @Test
    void getTransfersListByAutor() {
        ArrayList<Transferencias> ltransfers =
                                  TransfersDAO.getTransfersListByAutor(1);
        assertNotNull(ltransfers);
        assertFalse(ltransfers.isEmpty());
    }

    @Test
    void getTransfersListByAutorFalse() {
        ArrayList<Transferencias> ltransfers =
                                  TransfersDAO.getTransfersListByAutor(99999);
        assertNull(ltransfers);
    }

    @Test
    void testGetTransfersListByReceptor() {
        ArrayList<Transferencias> transfersList =
                                  TransfersDAO.getTransfersListByReceptor(1);
        assertNotNull(transfersList);
        assertFalse(transfersList.isEmpty());
    }

    @Test
    void testGetTransfersListByReceptorFalse() {
        ArrayList<Transferencias> transfersList =
                                  TransfersDAO.getTransfersListByReceptor(999999);
        assertNull(transfersList);
    }
}
