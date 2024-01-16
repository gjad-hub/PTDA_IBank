/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.manager.profile.personal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.FuncionarioDAO;
import pt.ua.ibank.DTO.Funcionario;
import pt.ua.ibank.utilities.TableElement;

/**
 *
 * @author ricar
 */
public final class PerfilEmployeePersonalTableModel extends AbstractTableModel {

    private final List<TableElement> data;
    public final Funcionario funcionario;

    public PerfilEmployeePersonalTableModel(int funcionarioID) {
        funcionario = FuncionarioDAO.getFuncionarioByID(funcionarioID);
        data = new ArrayList<>();

        setupData();
    }

    public void setupData() {
        data.add(new TableElement("ID: ", Integer.toString(funcionario.numFun)));
        data.add(new TableElement("Responsavel: ", getNomeGerente()));
        data.add(new TableElement("Nome:", funcionario.nome));
        data.add(new TableElement("Email:", funcionario.email));
        data.add(new TableElement("Telemovel:", funcionario.telemovel));
        data.add(new TableElement("NIF: ", funcionario.nif));
        data.add(new TableElement("Morada:", funcionario.morada));
        data.add(new TableElement("Demitido: ", funcionario.foiDespedido ?
                                                "Sim" :
                                                "Não"));
    }

    public String getNomeGerente() {
        if (funcionario.gerente != 0) {
            return FuncionarioDAO.getFuncionarioNomeByID(
                    funcionario.gerente);
        }
        return "N/A";
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
        return data.get(row).key;
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

    public boolean verificarDados(String input, int row) {

        Pattern p;
        Matcher m;
        final String key = data.get(row).key;
        switch (key) {
            case "Nome:" -> {
                p = Pattern.compile("^[a-zA-Z]+$");
                m = p.matcher(input);
                return m.matches();
            }
            case "Email:" -> {
                p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
                m = p.matcher(input);
                return m.matches();
            }
            case "NIF:" -> {
                p = Pattern.compile("^\\d{9}$");
                m = p.matcher(input);
                return m.matches();
            }
            case "Telemovel:" -> {
                p = Pattern.compile("^\\d{9}$");
                m = p.matcher(input);
                return m.matches();
            }
        }
        System.out.println("Not Found!");
        return false;
    }

    public void setValue(Object aValue, int row) {
        switch (row) {
            case 0 -> {
                funcionario.numFun = (int) aValue;
            }
            case 1 -> {
                funcionario.gerente = (Integer) aValue;
            }
            case 2 -> {
                funcionario.nome = (String) aValue;
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
            case 6 -> {
                funcionario.morada = (String) aValue;
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

    public boolean apagarFuncionario() {
        return FuncionarioDAO.demitirFuncionario(funcionario.numFun);
    }

    public boolean promoverFuncionario() {
        return FuncionarioDAO.promoverFuncionario(funcionario.numFun);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0; // only the second column should be clickable
    }

}
