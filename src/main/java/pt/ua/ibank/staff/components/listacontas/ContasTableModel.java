package pt.ua.ibank.staff.components.listacontas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DTO.Transferencia;

/**
 * Implementação tabela de Transferencias a ser usada na interface de staff.
 * ultima modificação: 04 Janeiro 2024
 */
public class ContasTableModel extends AbstractTableModel {

    private List<String> header = null;
    private List<Transferencia> data = null;

    public ContasTableModel() {
        data = new ArrayList<>();
        data.add(new Transferencia(2, 2.0, 2, 2, "String"));
        header = new ArrayList<>(Arrays.asList(
                "ID", "Autor", "Recetor", "Valor", "Descricao", "Açao"));
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
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1 -> {
                data.get(rowIndex).getTransferenciaID();
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
