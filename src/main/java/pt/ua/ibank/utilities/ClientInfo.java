package pt.ua.ibank.utilities;

import java.util.Objects;
import static pt.ua.ibank.DAO.CardsDAO.getCardByNumber;

import pt.ua.ibank.DAO.ClientDAO;
import pt.ua.ibank.DTO.Cartao;
import pt.ua.ibank.DTO.Cliente;

public class ClientInfo {

    public static void updateClientBalance(Cliente client) {
        Cliente tmp = ClientDAO.getClientBalance(client.numCliente);

        if (!Objects.isNull(tmp)) {
            client.saldo = tmp.saldo;
            client.saldo_cativo = tmp.saldo_cativo;
        }
    }

    public static void updateClientCardInfo(Cliente client) {
        String tmp = ClientDAO.getClientDefaultCard(client.numCliente);

        if (!Objects.isNull(tmp)) {
            client.cartaoDefault = tmp;
        }
    }

    public static void updateLocalCard(Cartao card, Cliente client) {
        Cartao tmp = getCardByNumber(client.cartaoDefault);

        if (!Objects.isNull(tmp)) {
            card.numCartao = tmp.numCartao;
            card.dataValidade = tmp.dataValidade;
        }
    }
}
