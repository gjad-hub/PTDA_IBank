package pt.ua.ibank.DAO;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testCreateClientSuccess() {
        int result = ClientDAO.CreateClient("João Tomás Silva", "Avenida Lourenço Peixinho, 123, Aveiro, Portugal", "joaosilva@gmail.com", "923344551", "119876543", "joao2024silva");
        assertEquals(ClientDAO.codigoSucesso, result);
    }

    @Test
    public void testCreateClientError() { //Erro sql: erro na conexão à base ou outro
        // Não sei o que fazer aqui
        int result = ClientDAO.CreateClient("João Tomás Silva", "Avenida Lourenço Peixinho, 123, Aveiro, Portugal", "joaosilva@gmail.com", "923344551", "119876543", "joao2024silva");
        assertEquals(ClientDAO.codigoErro, result);
    }

    @Test
    public void testCreateClientErrorEmail() {
        ClientDAO.CreateClient("Joao Declan Silva", "Lourenco Peixinho Avenue, 124, Aveiro, Portugal", "joaosilva@gmail.com", "935781035", "982674102","john2024declan");
        int result = ClientDAO.CreateClient("João Tomás Silva", "Avenida Lourenço Peixinho, 123, Aveiro, Portugal", "joaosilva@gmail.com", "923344551", "119876543", "joao2024silva");
        assertEquals(ClientDAO.codigoErroEmail, result);
    }
}