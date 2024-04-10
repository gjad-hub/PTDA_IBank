package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Transactions;

class TransacoesDAOTest {

    @Test
    void getTransacoes() {
        int id = 1;
        ArrayList<Transactions> ltransacoes =
                                TransactionsDAO.getTransactionsByClientID(id);
        assertFalse(ltransacoes.isEmpty());
    }

    @Test
    void getTransacoesFalse() {
        int id = 9999;
        ArrayList<Transactions> ltransacoes =
                                TransactionsDAO.getTransactionsByClientID(id);
        assertNull(ltransacoes);
    }
}
