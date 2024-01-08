package pt.ua.ibank.interfaces.internalFrames.staff.accounts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.CartaoDAO;
import pt.ua.ibank.DTO.Cliente;

/**
 * Implementação tabela de Transferencias a ser usada na interface de staff.
 * ultima modificação: 04 Janeiro 2024
 */
public class ContasTableModel extends AbstractTableModel {

    private List<String> header = null;
    private List<Cliente> data = null;

    public ContasTableModel() {
        data = new ArrayList<>();
        data.add(new Cliente(1, "a", "a", "a", "2", "asd", "a", 23.2));
        header = new ArrayList<>(Arrays.asList(
                "N.Conta", "N.Cliente", "Nome",
                "Email", "NIF", ""));
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.size();
    }

    @Override
    public String getColumnName(int column) {
        return header.get(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public Cliente getAccount(int rowIndex) {
        return data.get(rowIndex);
    }

    public String getDepositAmountNumberFromID(int id) {
        return String.valueOf(420);
        //return CartaoDAO.getDepositAmountByNumber(number);
    }

    public String getCardAmountNumberFromID(int id) {
        return String.valueOf(69);
        //return CartaoDAO.getCardAmountByNumber(number);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return data.get(rowIndex).numConta;
            }
            case 1 -> {
                return data.get(rowIndex).numCliente;
            }
            case 2 -> {
                return data.get(rowIndex).nome;
            }
            case 3 -> {
                return data.get(rowIndex).email;
            }
            case 4 -> {
                return data.get(rowIndex).nif;
            }
        }
        return null;
    }

}
