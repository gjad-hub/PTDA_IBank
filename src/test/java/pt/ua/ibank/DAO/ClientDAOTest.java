package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Client;
import static pt.ua.ibank.utilities.Configs.EMAIL_ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;

class ClientDAOTest {

    @Test
    public void testCreateClientSuccess() {
        int result = ClientDAO.CreateClient("João Tomás Silva",
                                            "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
                                            "joaosilva@gmail.com", "923344551",
                                            "119876543", "joao2024silva");
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    public void testCreateClientErrorEmail() {
        int result = ClientDAO.CreateClient("João Tomás Silva",
                                            "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
                                            "joaosilva@gmail.com", "923344551",
                                            "119876543", "joao2024silva");
        assertEquals(EMAIL_ERROR_CODE, result);
    }

    @Test
    public void testGetClientByEmailFound() {
        String testEmail = "marta.rodrigues@email.com";
        Client cliente = ClientDAO.getClientByEmail(testEmail);
        assertNotNull(cliente);
        assertEquals(testEmail, cliente.email);
    }

    @Test
    public void testGetClientByEmailNotFound() {
        String testEmail = "emailnaoexistente@gmail.com";
        Client cliente = ClientDAO.getClientByEmail(testEmail);
        assertNull(cliente);
    }

    @Test
    public void testGetClientIdByIBANFound() {
        String testIban = "PT50007884763541403081684";
        Integer clientId = ClientDAO.getClientIdByIBAN(testIban);
        assertNotNull(clientId);
    }

    @Test
    public void testGetClientIdByIBANNotFound() {
        String testIban = "PT50002321234560799000510";
        Integer clientId = ClientDAO.getClientIdByIBAN(testIban);
        assertNull(clientId);
    }

    @Test
    public void testGetClientIdByEmailFound() {
        String testEmail = "marta.rodrigues@email.com";
        Integer clientId = ClientDAO.getClientIdByEmail(testEmail);
        assertNotNull(clientId);
    }

    @Test
    public void testGetClientIdByEmailNotFound() {
        String testEmail = "naoexistente@gmail.com";
        Integer clientId = ClientDAO.getClientIdByEmail(testEmail);
        assertNull(clientId);
    }

    @Test
    public void testGetClientBalanceFound() {
        int testClientId = 3;
        Client cliente = ClientDAO.getClientBalance(testClientId);
        assertNotNull(cliente);
        assertTrue(cliente.balance >= 0);
    }

    @Test
    public void testGetClientBalanceNotFound() {
        int testClientId = 9999;
        Client cliente = ClientDAO.getClientBalance(testClientId);
        assertNull(cliente);
    }

    @Test
    public void testUpdateClientSuccess() {
        int result = ClientDAO.UpdateClient("Hugo Tomás Silva",
                                            "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
                                            "hugo.oliveira.new@email.com",
                                            "923344551", "119876543",
                                            "Hugo2024silva",
                                            "hugo.oliveira@email.com");
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    public void testUpdateClientErrorEmail() {
        int result = ClientDAO.UpdateClient("João Tomás Silva",
                                            "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
                                            "marta.rodrigues@email.com",
                                            "923344551",
                                            "119876543", "joao2024silva",
                                            "joaotomassilva@gmail.com");
        assertEquals(EMAIL_ERROR_CODE, result);
    }

    @Test
    void getClientByID() {
        int id = 1;
        Client client = ClientDAO.getClientByID(id);
        assertNotNull(client);
    }

    @Test
    void getClientByIDFalse() {
        int id = 999999;
        Client client = ClientDAO.getClientByID(id);
        assertNull(client);
    }

    @Test
    void getClientByNIF() {
        String nif = "111223344";
        Client client = ClientDAO.getClientByNIF(nif);
        assertNotNull(client);
    }

    @Test
    void getClientByNIFFalse() {
        String nif = "999999999";
        Client client = ClientDAO.getClientByNIF(nif);
        assertNull(client);
    }

    @Test
    void getClientListByAddress() {
        String address = "Avenida H, nº 222";
        ArrayList<Client> lclient = ClientDAO.getClientListByAddress(address);
        assertNotNull(lclient);
    }

    @Test
    void getClientListByAddressFalse() {
        String address = "Morada que não existe";
        ArrayList<Client> lclient = ClientDAO.getClientListByAddress(address);
        assertNotNull(lclient);
        assertTrue(lclient.isEmpty());
    }

    @Test
    void getClientList() {
        ArrayList<Client> lclient = ClientDAO.getClientList();
        assertNotNull(lclient);
    }

    @Test
    void getClienteNomeByID() {
        String name = "Sara Silva";
        int id = 3;
        String result = ClientDAO.getClientNameByID(id);
        assertEquals(name, result);
    }

    @Test
    void getClienteNomeByIDFalse() {
        String result = ClientDAO.getClientNameByID(9999);
        assertNull(result);
    }

    @Test
    void getClientDefaultCard() {
        String card = "4001733310706027";
        int id = 5;
        String result = ClientDAO.getClientDefaultCard(id);
        assertEquals(card, result);
    }

    @Test
    void getClientDefaultCardFalse() {
        String result = ClientDAO.getClientDefaultCard(99999);
        assertNull(result);
    }
}
