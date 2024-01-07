package pt.ua.ibank.DAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Cartao;

import static org.junit.jupiter.api.Assertions.*;

class CartaoDAOTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCardByNumber() {
    }

    @Test
    public void testGetCardByNumberFound() { //Cannot read field "cartaoDefault" because "pt.ua.ibank.DTO.Cliente.LocalClient" is null
        String testCardNumber = "4001265529223496";
        Cartao cartao = CartaoDAO.getCardByNumber(testCardNumber);
        assertNotNull(cartao);
        assertEquals(testCardNumber, cartao.numCartao);
    }

    @Test
    public void testGetCardByNumberNotFound() {
        String testCardNumber = "4000917308926013";
        Cartao cartao = CartaoDAO.getCardByNumber(testCardNumber);
        assertNull(cartao);
    }
}