package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Cartao;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;

class CardsDAOTest {

    @Test
    public void testGetCardByNumberFound() {
        String testCardNumber = "4001120953203520";
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

    @Test
    void getCardListFromUserID() {
        int id = 3;
        ArrayList<Cartao> lcartao = CardsDAO.getCardListFromUserID(id);
        assertFalse(lcartao.isEmpty());
    }

    @Test
    void getCardListFromUserIDNull() {
        int id = 99;
        ArrayList<Cartao> lcartao = CardsDAO.getCardListFromUserID(id);
        assertNull(lcartao);
    }

    @Test
    void getCardAmountByID() {
        int id = 1;
        int num_cards = 9;
        int test_card = CardsDAO.getCardAmountByID(id);
        assertEquals(num_cards, test_card);
    }

    @Test
    void getCardAmountByIDFalse() {
        int id = 9999;
        int test_card = CardsDAO.getCardAmountByID(id);
        assertEquals(0, test_card);
    }

    @Test
    void cancelCard() {
        String card = "4001120953203520";
        int test_card = CardsDAO.cancelCard(card);
        assertEquals(CODIGO_SUCESSO, test_card);
    }

    @Test
    void makeDefault() {
        String card = "4001120953203520";
        int test_card = CardsDAO.makeDefault(card, 1);
        assertEquals(CODIGO_SUCESSO, test_card);
    }

    @Test
    public void testCreateCardSuccess() {
        int validClientID = 1;
        int resultado = CardsDAO.createCard(validClientID);
        assertEquals(CODIGO_SUCESSO, resultado);
    }

    @Test
    public void testCreateCardInvalidClient() {
        int invalidClientId = 9999;
        int result = CardsDAO.createCard(invalidClientId);
        assertEquals(CODIGO_ERRO, result);
    }
}
