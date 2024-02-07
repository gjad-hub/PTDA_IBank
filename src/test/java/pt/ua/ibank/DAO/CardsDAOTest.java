package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Card;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;

class CardsDAOTest {

    @Test
    public void testGetCardByNumberFound() {
        String testCardNumber = "4001120953203520";
        Card cartao = CardsDAO.getCardByNumber(testCardNumber);
        assertNotNull(cartao);
        assertEquals(testCardNumber, cartao.cardNumber);
    }

    @Test
    public void testGetCardByNumberNotFound() {
        String testCardNumber = "4000917308926013";
        Card cartao = CardsDAO.getCardByNumber(testCardNumber);
        assertNull(cartao);
    }

    @Test
    void getCardListFromUserID() {
        int id = 3;
        ArrayList<Card> lcartao = CardsDAO.getCardListFromUserID(id);
        assertFalse(lcartao.isEmpty());
    }

    @Test
    void getCardListFromUserIDNull() {
        int id = 99;
        ArrayList<Card> lcartao = CardsDAO.getCardListFromUserID(id);
        assertNull(lcartao);
    }

    @Test
    void getCardAmountByID() {
        int id = 1;
        int num_cards = 4;
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
        assertEquals(SUCCESS_CODE, test_card);
    }

    @Test
    void makeDefault() {
        String card = "4001120953203520";
        int test_card = CardsDAO.makeDefault(card, 1);
        assertEquals(SUCCESS_CODE, test_card);
    }

    @Test
    public void testCreateCardSuccess() {
        int validClientID = 1;
        int resultado = CardsDAO.createCard(validClientID);
        assertEquals(SUCCESS_CODE, resultado);
    }

    @Test
    public void testCreateCardInvalidClient() {
        int invalidClientId = 9999;
        int result = CardsDAO.createCard(invalidClientId);
        assertEquals(ERROR_CODE, result);
    }
}
