package pt.ua.ibank.DAO;

import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Transactions;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransacoesDAOTest {

    @Test
    void getTransacoes() {
        int id = 1;
        ArrayList<Transactions> ltransacoes = TransactionsDAO.getTransacoes(id);
        assertFalse(ltransacoes.isEmpty());
    }

    @Test
    void getTransacoesFalse() {
        int id = 9999;
        ArrayList<Transactions> ltransacoes = TransactionsDAO.getTransacoes(id);
        assertNull(ltransacoes);
    }
}