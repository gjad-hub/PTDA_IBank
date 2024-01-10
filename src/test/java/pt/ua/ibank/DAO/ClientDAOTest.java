package pt.ua.ibank.DAO;

import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.Cliente;

import static org.junit.Assert.*;

class ClientDAOTest {

    private ClientDAO clientDAO;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        clientDAO = new ClientDAO();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void createClient() {
    }

    @org.junit.jupiter.api.Test
    void getClientByEmail() {
    }

    @org.junit.jupiter.api.Test
    void getClientIdByIBAN() {
    }

    @org.junit.jupiter.api.Test
    void getClientIdByEmail() {
    }

    @org.junit.jupiter.api.Test
    void getClientBalance() {
    }

    @org.junit.jupiter.api.Test
    void updateClient() {
    }

//    @Test
//    public void testCreateClientSuccess() {
//        int result = ClientDAO.CreateClient("João Tomás Silva", "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
//                "joaosilva@gmail.com", "923344551", "119876543", "joao2024silva");
//        assertEquals(ClientDAO.codigoSucesso, result);
//    }
//
//    @Test
//    public void testCreateClientError() { // Erro sql: erro na conexão à base ou outro
//        // Não sei o que fazer aqui
//        int result = ClientDAO.CreateClient("João Tomás Silva", "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
//                "joaosilva@gmail.com", "923344551", "119876543", "joao2024silva");
//        assertEquals(ClientDAO.codigoErro, result);
//    }
//
//    @Test
//    public void testCreateClientErrorEmail() {
//        ClientDAO.CreateClient("Joao Declan Silva", "Lourenco Peixinho Avenue, 124, Aveiro, Portugal",
//                "joaosilva@gmail.com", "935781035", "982674102", "john2024declan");
//        int result = ClientDAO.CreateClient("João Tomás Silva", "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
//                "joaosilva@gmail.com", "923344551", "119876543", "joao2024silva");
//        assertEquals(ClientDAO.codigoErroEmail, result);
//    }
//
//    @Test
//    public void testGetClientByEmailFound() {
//        String testEmail = "richard@ua.pt";
//        Cliente cliente = ClientDAO.getClientByEmail(testEmail);
//        assertNotNull(cliente); // verifica se o cliente realmente existe (se não é nulo)
//        assertEquals(testEmail, cliente.email);
//    }
//
//    @Test
//    public void testGetClientByEmailNotFound() {
//        String testEmail = "emailnaoexistente@gmail.com";
//        Cliente cliente = ClientDAO.getClientByEmail(testEmail);
//        assertNull(cliente);
//    }
//
//    @Test
//    public void testGetClientIdByIBANFound() {
//        String testIban = "PT50002329487360799005510";
//        Integer clientId = ClientDAO.getClientIdByIBAN(testIban);
//        assertNotNull(clientId);
//    }
//
//    @Test
//    public void testGetClientIdByIBANNotFound() {
//        String testIban = "PT50002321234560799000510";
//        Integer clientId = ClientDAO.getClientIdByIBAN(testIban);
//        assertNull(clientId);
//    }
//
//    @Test
//    public void testGetClientIdByEmailFound() { // podemos juntar este teste com o testGetClientByEmailFound()
//        String testEmail = "richard@ua.pt";
//        Integer clientId = ClientDAO.getClientIdByEmail(testEmail);
//        assertNotNull(clientId);
//    }
//
//    @Test
//    public void testGetClientIdByEmailNotFound() { // podemos juntar este teste com o testGetClientByEmailNotFoundN()
//        String testEmail = "naoexistente@gmail.com";
//        Integer clientId = ClientDAO.getClientIdByEmail(testEmail);
//        assertNull(clientId);
//    }
//
//    @Test
//    public void testGetClientBalanceFound() {
//        int testClientId = 3;
//        Cliente cliente = ClientDAO.getClientBalance(testClientId);
//        assertNotNull(cliente.saldo);
//        assertTrue(cliente.saldo >= 0); // assumindo que o saldo não pode ser negativo, se poder aí tem que se remover
//                                  // esta linha
//    }
//
//    @Test
//    public void testGetClientBalanceNotFound() {
//        int testClientId = 9999;
//        Cliente cliente = ClientDAO.getClientBalance(testClientId);
//        assertNull(cliente.saldo);
//    }
//
//    @Test
//    public void testUpdateClientSuccess() {
//        int result = ClientDAO.UpdateClient("João Tomás Silva", "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
//                "joaotomassilva@gmail.com", "923344551", "119876543", "joao2024silva", "joaosilva@gmail.com");
//        assertEquals(ClientDAO.codigoSucesso, result);
//    }
//
//    @Test
//    public void testUpdateClientErrorEmail() {
//        int result = ClientDAO.UpdateClient("João Tomás Silva", "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
//                "richard@ua.pt", "923344551", "119876543", "joao2024silva", "joaotomassilva@gmail.com");
//        assertEquals(ClientDAO.codigoErroEmail, result);
//    }
//
//    @Test
//    public void testUpdateClientNoChange() {
//        int result = ClientDAO.UpdateClient("João Tomás Silva", "Avenida Lourenço Peixinho, 123, Aveiro, Portugal",
//                "joaotomassilva@gmail.com", "923344551", "119876543", "joao2024silva", "joaotomassilva@gmail.com");
//        assertEquals(ClientDAO.codigoSucesso, result);
//    }
}