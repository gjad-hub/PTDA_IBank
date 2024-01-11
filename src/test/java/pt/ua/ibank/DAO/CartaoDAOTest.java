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
    public void testGetCardByNumberFound() { 
        String testCardNumber = "4001385050156587";
        Cartao cartao = CardsDAO.getCardByNumber(testCardNumber);
        assertNotNull(cartao);
        assertEquals(testCardNumber, cartao.numCartao);
    }

    @Test
    public void testGetCardByNumberNotFound() {
        String testCardNumber = "4000917308926013";
        Cartao cartao = CardsDAO.getCardByNumber(testCardNumber);
        assertNull(cartao);
    }
}
