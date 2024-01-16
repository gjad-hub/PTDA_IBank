package pt.ua.ibank.DAO;

import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Transacoes;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransacoesDAOTest {

    @Test
    void getTransacoes() {
        int id = 1;
        ArrayList<Transacoes> ltransacoes = TransacoesDAO.getTransacoes(id);
        assertFalse(ltransacoes.isEmpty());
    }

    @Test
    void getTransacoesFalse() {
        int id = 9999;
        ArrayList<Transacoes> ltransacoes = TransacoesDAO.getTransacoes(id);
        assertNull(ltransacoes);
    }
}