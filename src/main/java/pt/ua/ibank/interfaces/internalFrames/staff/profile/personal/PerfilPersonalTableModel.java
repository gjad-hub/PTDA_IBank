/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.personal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.ClientDAO;
import pt.ua.ibank.DTO.Cliente;
import static pt.ua.ibank.utilities.Configs.EMAIL_REGEX;
import static pt.ua.ibank.utilities.Configs.NINE_DIGIT_REGEX;
import static pt.ua.ibank.utilities.Configs.NOME_REGEX;
import pt.ua.ibank.utilities.TableElement;

/**
 *
 * @author ricar
 */
public final class PerfilPersonalTableModel extends AbstractTableModel {

    private final List<TableElement> data;
    public final Cliente client;

    public PerfilPersonalTableModel(int clientID) {
        client = ClientDAO.getClientByID(clientID);
        data = new ArrayList<>();
        setupData();
    }

    public void setupData() {
        data.add(new TableElement("ID: ", Integer.toString(client.numCliente)));
        data.add(new TableElement("Nome:", client.nome));
        data.add(new TableElement("Telemovel:", client.telemovel));
        data.add(new TableElement("Email:", client.email));
        data.add(new TableElement("NIF: ", client.nif));
        data.add(new TableElement("Morada:", client.morada));
        data.add(new TableElement(
                "Cartao default: ", client.cartaoDefault));
        data.add(new TableElement(
                "Saldo(€): ", Double.toString(client.saldo)));
        data.add(new TableElement(
                "Saldo Cativo(€): ", Double.toString(client.saldo_cativo)));
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
                p = Pattern.compile(NOME_REGEX);
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
                client.numCliente = (int) aValue;
            }
            case 1 -> {
                client.nome = (String) aValue;
            }
            case 2 -> {
                client.morada = (String) aValue;
            }
            case 3 -> {
                client.email = (String) aValue;
            }
            case 4 -> {
                client.telemovel = (String) aValue;
            }
            case 5 -> {
                client.nif = (String) aValue;
            }

        }
        fireTableRowsUpdated(data.size() - 1, data.size() - 1);
        updateClient();
    }

    public void updateClient() {
        ClientDAO.UpdateClient(client.numCliente, client.nome,
                               client.morada,
                               client.email, client.telemovel,
                               client.nif);
    }

    public void updateDepositDecision(double depositValue) {
        client.saldo_cativo -= depositValue;
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
