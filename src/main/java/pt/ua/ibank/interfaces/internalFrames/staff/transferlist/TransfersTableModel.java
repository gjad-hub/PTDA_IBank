package pt.ua.ibank.interfaces.internalFrames.staff.transferlist;

import pt.ua.ibank.interfaces.internalFrames.staff.accounts.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DTO.Transferencias;

/**
 * Implementação tabela de Transferencias a ser usada na interface de staff.
 * ultima modificação: 08 Janeiro 2024
 */
public class TransfersTableModel extends AbstractTableModel {

    private List<String> header = null;
    private List<Transferencias> data = null;

    public TransfersTableModel() {
        //data = new TransferenciaDAO.getTransferList();
        data = new ArrayList<>();
        data.add(new Transferencias(1, 2.0, 1, 3, "razao"));
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

}
