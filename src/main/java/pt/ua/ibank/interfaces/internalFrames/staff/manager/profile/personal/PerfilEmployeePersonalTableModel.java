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
import pt.ua.ibank.DAO.EmployeeDAO;
import pt.ua.ibank.DTO.Employee;
import static pt.ua.ibank.utilities.Configs.EMAIL_REGEX;
import static pt.ua.ibank.utilities.Configs.NAME_REGEX;
import static pt.ua.ibank.utilities.Configs.NINE_DIGIT_REGEX;
import pt.ua.ibank.utilities.TableElement;

public final class PerfilEmployeePersonalTableModel extends AbstractTableModel {

    private final List<TableElement> data;
    public final Employee employee;

    public PerfilEmployeePersonalTableModel(int funcionarioID) {
        employee = EmployeeDAO.getEmployeeByID(funcionarioID);
        data = new ArrayList<>();

        setupData();
    }

    public void setupData() {
        data.add(new TableElement("ID: ", Integer.toString(employee.empNum)));
        data.add(new TableElement("Responsavel: ", getNomeGerente()));
        data.add(new TableElement("Nome:", employee.name));
        data.add(new TableElement("Email:", employee.email));
        data.add(new TableElement("Telemovel:", employee.phoneNumber));
        data.add(new TableElement("NIF: ", employee.nif));
        data.add(new TableElement("Morada:", employee.address));
        data.add(new TableElement("Demitido: ", employee.dismissed ?
                                                "Sim" :
                                                "Não"));
    }

    public String getNomeGerente() {
        if (!employee.isManager()) {
            return EmployeeDAO.getEmployeeNameByID(employee.managerNum);
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

    public void setValue(Object aValue, int row) {
        switch (row) {
            case 0 -> {
                employee.empNum = (int) aValue;
            }
            case 1 -> {
                employee.managerNum = (Integer) aValue;
            }
            case 2 -> {
                employee.name = (String) aValue;
            }
            case 3 -> {
                employee.email = (String) aValue;
            }
            case 4 -> {
                employee.phoneNumber = (String) aValue;
            }
            case 5 -> {
                employee.nif = (String) aValue;
            }
            case 6 -> {
                employee.address = (String) aValue;
            }

        }
        fireTableRowsUpdated(data.size() - 1, data.size() - 1);
        updateClient();
    }

//    updateEmployee(String name,
//                                     String address, String email,
//                                     String phone, String nif,
//                                     String old_email)
    public void updateClient() {
        EmployeeDAO.updateEmployee(employee.name,
                                   employee.address,
                                   employee.email,
                                   employee.phoneNumber,
                                   employee.nif);
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return data.get(linha);
    }

    public boolean apagarFuncionario() {
        return EmployeeDAO.dismissEmployee(employee.empNum);
    }

    public boolean promoverFuncionario() {
        return EmployeeDAO.promoteEmployee(employee.empNum);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0; // only the second column should be clickable
    }

}
