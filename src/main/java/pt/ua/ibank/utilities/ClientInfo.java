package pt.ua.ibank.utilities;

import java.util.Objects;
import static pt.ua.ibank.DAO.CardsDAO.getCardByNumber;

import pt.ua.ibank.DAO.ClientDAO;
import pt.ua.ibank.DTO.Card;
import pt.ua.ibank.DTO.Client;

public class ClientInfo {

    public static void updateClientBalance(Client client) {
        Client tmp = ClientDAO.getClientBalance(client.clientNumber);

        if (!Objects.isNull(tmp)) {
            client.balance = tmp.balance;
            client.pendingBalance = tmp.pendingBalance;
        }
    }

    public static void updateClientCardInfo(Client client) {
        String tmp = ClientDAO.getClientDefaultCard(client.clientNumber);

        if (!Objects.isNull(tmp)) {
            client.defaultCard = tmp;
        }
    }

    public static void updateLocalCard(Card card, Client client) {
        Card tmp = getCardByNumber(client.defaultCard);

        if (!Objects.isNull(tmp)) {
            card.cardNumber = tmp.cardNumber;
            card.expireDate = tmp.expireDate;
        }
    }
}
