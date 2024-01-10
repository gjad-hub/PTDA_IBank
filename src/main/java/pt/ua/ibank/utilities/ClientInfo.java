package pt.ua.ibank.utilities;

import java.util.Objects;

import pt.ua.ibank.DAO.ClientDAO;
import pt.ua.ibank.DTO.Cliente;

public class ClientInfo {
    public static void updateClientBalance(Cliente client) {
        Cliente tmp = ClientDAO.getClientBalance(client.numCliente);

        if (!Objects.isNull(tmp)) {
            client.saldo = tmp.saldo;
            client.saldo_cativo = tmp.saldo_cativo;
        }
    }
}
