package pt.ua.ibank.interfaces.internalFrames.staff.transferlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.TransfersDAO;
import pt.ua.ibank.DTO.Transferencias;

/**
 * Implementação tabela de Transferencias a ser usada na interface de staff.
 * ultima modificação: 08 Janeiro 2024
 */
public class TransfersTableModel extends AbstractTableModel {

    private List<String> header = null;
    private List<Transferencias> data = null;

    public TransfersTableModel() {
        data = TransfersDAO.getTransfersList();
        header = new ArrayList<>(Arrays.asList(
        "ID", "Valor", "Autor", "Receptor", "motivo", ""));
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

    public Transferencias getTransfer(int row) {
        return data.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return data.get(rowIndex).getTransferenciaID();
            }
            case 1 -> {
                return data.get(rowIndex).getvalorEmEuros();
            }
            case 2 -> {
                return data.get(rowIndex).getclienteRealizaID();
            }
            case 3 -> {
                return data.get(rowIndex).getclienteRecebeID();
            }
            case 4 -> {
                return data.get(rowIndex).getDescricao();
            }
        }
        return null;
    }

    public void resetSearchFilters() {
        data.clear();
        if ((data = TransfersDAO.getTransfersList()) != null) {
            fireTableRowsDeleted(0, data.size() - 1);
        }
    }

    public void searchForClient(String value, String searchType) {
        switch (searchType) {
            case "ID" -> {
                data.clear();
                Transferencias result;
                if ((result = TransfersDAO.getTransferByID(
                     Integer.parseInt(value))) != null) {
                    data.add(result);
                    fireTableRowsDeleted(0, data.size() - 1);
                }
            }
            case "Autor" -> {
                data.clear();
                if ((data = TransfersDAO.getTransfersListByAutor(
                     Integer.parseInt(value))) != null) {
                    fireTableRowsDeleted(0, data.size() - 1);
                }
            }
            case "Receptor" -> {
                data.clear();
                if ((data = TransfersDAO.getTransfersListByReceptor(
                     Integer.parseInt(value))) != null) {
                    fireTableRowsDeleted(0, data.size() - 1);
                }
            }
            case "Descrição" -> {
                data.clear();
                if ((data = TransfersDAO.getTransfersListByDescricao(value)) != null) {
                    fireTableRowsDeleted(0, data.size() - 1);
                }
            }
            case "Cliente" -> {
                data.clear();
                if ((data = TransfersDAO.getClientTransfersList(
                     Integer.parseInt(value))) != null) {
                    fireTableRowsDeleted(0, data.size() - 1);
                }
            }
        }
    }

}
