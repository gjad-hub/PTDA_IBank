package pt.ua.ibank.DAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransfersDAOTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void doTransfer() {
    }

    @Test
    public void testDoTransferSuccess() {
        assertEquals(TransfersDAO.codigoSucesso, TransfersDAO.doTransfer(50.0, 3, 1, "Dívida croissant misto prenssado"));
    }

    @Test
    public void testDoTransferError() {
        assertEquals(TransfersDAO.codigoErro, TransfersDAO.doTransfer(-10.0, 999999, 111111, "Pagamento"));
    }
}