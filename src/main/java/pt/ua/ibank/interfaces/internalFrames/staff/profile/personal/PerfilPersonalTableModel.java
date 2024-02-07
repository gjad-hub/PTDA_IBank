/*
 * ricar
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.personal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.ClientDAO;
import pt.ua.ibank.DTO.Client;
import static pt.ua.ibank.utilities.Configs.EMAIL_REGEX;
import static pt.ua.ibank.utilities.Configs.NINE_DIGIT_REGEX;
import pt.ua.ibank.utilities.TableElement;
import static pt.ua.ibank.utilities.Configs.NAME_REGEX;

public final class PerfilPersonalTableModel extends AbstractTableModel {

    private final List<TableElement> data;
    public final Client client;

    public PerfilPersonalTableModel(int clientID) {
        client = ClientDAO.getClientByID(clientID);
        data = new ArrayList<>();
        setupData();
    }

    public void setupData() {
        data.add(new TableElement("ID: ", Integer.toString(client.clientNumber)));
        data.add(new TableElement("Nome:", client.name));
        data.add(new TableElement("Telemovel:", client.phoneNumber));
        data.add(new TableElement("Email:", client.email));
        data.add(new TableElement("NIF: ", client.nif));
        data.add(new TableElement("Morada:", client.address));
        data.add(new TableElement(
                "Cartao default: ", client.defaultCard));
        data.add(new TableElement(
                "Saldo(€): ", Double.toString(client.balance)));
        data.add(new TableElement(
                "Saldo Cativo(€): ", Double.toString(client.pendingBalance)));
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    public String getLabel(int row) {
        return data.get(row).pair;
    }

    public boolean verificarDados(String input, int row) {

        Pattern p;
        Matcher m;
        final String key = data.get(row).key;
        switch (key) {
            case "Nome:" -> {
                p = Pattern.compile(NAME_REGEX);
                m = p.matcher(input);
                return m.matches();
            }
            case "Email:" -> {
                p = Pattern.compile(EMAIL_REGEX);
                m = p.matcher(input);
                return m.matches();
            }
            case "NIF:" -> {
                p = Pattern.compile(NINE_DIGIT_REGEX);
                m = p.matcher(input);
                return m.matches();
            }
            case "Telemovel:" -> {
                p = Pattern.compile(NINE_DIGIT_REGEX);
                m = p.matcher(input);
                return m.matches();
            }
        }
        System.out.println("Not Found!");
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            data.get(rowIndex).pair = (String) aValue;
            fireTableRowsUpdated(0, data.size() - 1);
        } catch (java.lang.ClassCastException e) {
            //nothing
        }
    }

    public void setValue(Object aValue, int row) {
        switch (row) {
            case 0 -> {
                client.clientNumber = (int) aValue;
            }
            case 1 -> {
                client.name = (String) aValue;
            }
            case 2 -> {
                client.address = (String) aValue;
            }
            case 3 -> {
                client.email = (String) aValue;
            }
            case 4 -> {
                client.phoneNumber = (String) aValue;
            }
            case 5 -> {
                client.nif = (String) aValue;
            }

        }
        fireTableRowsUpdated(data.size() - 1, data.size() - 1);
        updateClient();
    }

    public void updateClient() {
        ClientDAO.UpdateClient(client.clientNumber, client.name,
                               client.address,
                               client.email, client.phoneNumber,
                               client.nif);
    }

    public void updateDepositDecision(double depositValue) {
        client.pendingBalance -= depositValue;
        fireTableRowsUpdated(data.size() - 1, data.size() - 1);
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return data.get(linha);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0; // only the second column should be clickable
    }

}
