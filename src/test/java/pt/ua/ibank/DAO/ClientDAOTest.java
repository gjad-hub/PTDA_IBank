package pt.ua.ibank.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Cliente;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO_EMAIL;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;

class ClientDAOTest {

    @Test
    public void testCreateClientSuccess() {
        int result = ClientDAO.CreateClient("João Tomás Silva",
                                            "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
                                            "joaosilva@gmail.com", "923344551",
                                            "119876543", "joao2024silva");
        assertEquals(CODIGO_SUCESSO, result);
    }

    @Test
    public void testCreateClientErrorEmail() {
        int result = ClientDAO.CreateClient("João Tomás Silva",
                                            "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
                                            "joaosilva@gmail.com", "923344551",
                                            "119876543", "joao2024silva");
        assertEquals(CODIGO_ERRO_EMAIL, result);
    }

    @Test
    public void testGetClientByEmailFound() {
        String testEmail = "marta.rodrigues@email.com";
        Cliente cliente = ClientDAO.getClientByEmail(testEmail);
        assertNotNull(cliente);
        assertEquals(testEmail, cliente.email);
    }

    @Test
    public void testGetClientByEmailNotFound() {
        String testEmail = "emailnaoexistente@gmail.com";
        Cliente cliente = ClientDAO.getClientByEmail(testEmail);
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
    public void testGetClientIdByEmailFound() { // podemos juntar este teste com o testGetClientByEmailFound()
        String testEmail = "marta.rodrigues@email.com";
        Integer clientId = ClientDAO.getClientIdByEmail(testEmail);
        assertNotNull(clientId);
    }

    @Test
    public void testGetClientIdByEmailNotFound() { // podemos juntar este teste com o testGetClientByEmailNotFoundN()
        String testEmail = "naoexistente@gmail.com";
        Integer clientId = ClientDAO.getClientIdByEmail(testEmail);
        assertNull(clientId);
    }

    @Test
    public void testGetClientBalanceFound() {
        int testClientId = 3;
        Cliente cliente = ClientDAO.getClientBalance(testClientId);
        assertNotNull(cliente.saldo);
        assertTrue(cliente.saldo >= 0); // assumindo que o saldo não pode ser negativo, se poder aí tem que se remover
        // esta linha
    }

    @Test
    public void testGetClientBalanceNotFound() {
        int testClientId = 9999;
        Cliente cliente = ClientDAO.getClientBalance(testClientId);
        assertNull(cliente);
    }

    @Test
    public void testUpdateClientSuccess() {
        int result = ClientDAO.UpdateClient("João Tomás Silva",
                                            "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
                                            "joaotomassilva@gmail.com",
                                            "923344551", "119876543",
                                            "joao2024silva",
                                            "joaosilva@gmail.com");
        assertEquals(CODIGO_SUCESSO, result);
    }

    @Test
    public void testUpdateClientErrorEmail() {
        int result = ClientDAO.UpdateClient("João Tomás Silva",
                                            "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
                                            "marta.rodrigues@email.com", "923344551",
                                            "119876543", "joao2024silva",
                                            "joaotomassilva@gmail.com");
        assertEquals(CODIGO_ERRO_EMAIL, result);
    }

    @Test
    public void testUpdateClientNoChange() {
        int result = ClientDAO.UpdateClient("João Tomás Silva",
                                            "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
                                            "joaotomassilva@gmail.com",
                                            "923344551", "119876543",
                                            "joao2024silva",
                                            "joaotomassilva@gmail.com");
        assertEquals(CODIGO_SUCESSO, result);
    }
}
