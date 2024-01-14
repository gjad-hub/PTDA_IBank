/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.manager.profile.personal;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.FuncionarioDAO;
import pt.ua.ibank.DTO.Funcionario;

/**
 *
 * @author ricar
 */
public final class PerfilEmployeePersonalTableModel extends AbstractTableModel {

    private final List<Pair<String, String>> data;
    public final Funcionario funcionario;

    public PerfilEmployeePersonalTableModel(int funcionarioID) {
        funcionario = FuncionarioDAO.getFuncionarioByID(funcionarioID);
        data = new ArrayList<>();
        setupData();
    }

    public void setupData() {
        data.add(new Pair<>("ID: ", Integer.toString(funcionario.numFun)));
        data.add(new Pair<>("Gerente:", getNomeGerente()));
        data.add(new Pair<>("Nome:", funcionario.nome));
        data.add(new Pair<>("Email:", funcionario.telemovel));
        data.add(new Pair<>("NIF: ", funcionario.nif));
        data.add(new Pair<>("Morada:", funcionario.morada));
    }

    public String getNomeGerente() {
        if (funcionario.gerente != 0) {
            return FuncionarioDAO.getFuncionarioNomeByID(
                    funcionario.gerente);
        }
        return "responsabilidade propria";
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
        return data.get(row).left;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            data.set(rowIndex, (Pair<String, String>) aValue);
            fireTableRowsUpdated(data.size() - 1, data.size() - 1);
        } catch (java.lang.ClassCastException e) {
            //nothing
        }
    }

    public void setValue(Object aValue, int row) {
        switch (row) {
            case 0 -> {
                funcionario.numFun = (int) aValue;
            }
            case 1 -> {
                funcionario.nome = (String) aValue;
            }
            case 2 -> {
                funcionario.morada = (String) aValue;
            }
            case 3 -> {
                funcionario.email = (String) aValue;
            }
            case 4 -> {
                funcionario.telemovel = (String) aValue;
            }
            case 5 -> {
                funcionario.nif = (String) aValue;
            }

        }
        fireTableRowsUpdated(data.size() - 1, data.size() - 1);
        updateClient();
    }

    public void updateClient() {
        FuncionarioDAO.UpdateFuncionario(funcionario.numFun, funcionario.nome,
                                         funcionario.morada,
                                         funcionario.email,
                                         funcionario.telemovel,
                                         funcionario.nif);
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return data.get(linha);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // only the second column should be clickable
    }

}
