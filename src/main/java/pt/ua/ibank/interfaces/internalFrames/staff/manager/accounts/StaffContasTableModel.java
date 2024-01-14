package pt.ua.ibank.interfaces.internalFrames.staff.manager.accounts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.CardsDAO;
import pt.ua.ibank.DAO.DepositsDAO;
import pt.ua.ibank.DAO.FuncionarioDAO;
import pt.ua.ibank.DTO.Funcionario;

/**
 * Implementação tabela de Transferencias a ser usada na interface de staff.
 * ultima modificação: 04 Janeiro 2024
 */
public class StaffContasTableModel extends AbstractTableModel {

    private List<String> header = null;
    private List<Funcionario> data = null;

    public StaffContasTableModel() {
        data = FuncionarioDAO.getFuncionarioList();
        header = new ArrayList<>(Arrays.asList(
        "N.Funcionario", "N.Gerente", "Nome",
        "Email", "NIF", "Operacoes Feitas", ""));
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

    public Funcionario getAccount(int rowIndex) {
        return data.get(rowIndex);
    }

    public void resetSearchFilters() {
        data.clear();
        if ((data = FuncionarioDAO.getFuncionarioList()) != null) {
            fireTableRowsDeleted(0, data.size() - 1);
        }
    }

    public String obterNomeGerente(int id) {
        return FuncionarioDAO.getFuncionarioNomeByID(id);
    }

    public String obterDepositosAprovados(int id) {
        return FuncionarioDAO.getFuncionarioNumDepositosAprovados(id);
    }

    public void searchForClient(String value, String searchType) {
        switch (searchType) {
            case "ID" -> {
                data.clear();
                Funcionario result;
                if ((result = FuncionarioDAO.getFuncionarioByID(
                     Integer.parseInt(value))) != null) {
                    data.add(result);
                    fireTableRowsDeleted(0, data.size() - 1);
                }
            }
            case "Email" -> {
                data.clear();
                Funcionario result;
                if ((result = FuncionarioDAO.getFuncionarioByEmail(value)) != null) {
                    data.add(result);
                    fireTableRowsDeleted(0, data.size() - 1);
                }
            }
            case "NIF" -> {
                data.clear();
                Funcionario result;
                if ((result = FuncionarioDAO.getFuncionarioByNIF(value)) != null) {
                    data.add(result);
                    fireTableRowsDeleted(0, data.size() - 1);
                }
            }
            case "Morada" -> {
                data.clear();
                if ((data = FuncionarioDAO.getFuncionarioListByAddress(value)) != null) {
                    fireTableRowsDeleted(0, data.size() - 1);
                }
            }
            default -> {
                System.out.println("Invalid search type.");
            }
        }
    }

    public int getDepositAmountNumberFromID(int id) {
        return DepositsDAO.getDepositCount(id);

    }

    public int getCardAmountNumberFromID(int id) {
        return CardsDAO.getCardAmountByID(id);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return data.get(rowIndex).numFun;
            }
            case 1 -> {
                return data.get(rowIndex).gerente;
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
            case 5 -> {
                var id = data.get(rowIndex).numFun;
                return FuncionarioDAO.getFuncionarioNumDepositosAprovados(id);
            }
            default -> {
                return -1;
            }
        }
    }

}
