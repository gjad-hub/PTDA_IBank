/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.deposit;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.ClientDAO;
import pt.ua.ibank.DAO.DepositsDAO;
import pt.ua.ibank.DTO.Client;
import pt.ua.ibank.DTO.Deposit;
import static pt.ua.ibank.utilities.Configs.LocalEmployee;

public class DepositTableModel extends AbstractTableModel {

    private final List<Deposit> data;
    public Client client = null;

    public DepositTableModel(int clientID) {
        data = DepositsDAO.getDeposits(clientID);
        client = ClientDAO.getClientByID(clientID);
    }

    public void aprovarDeposito(int row) {
        if (DepositsDAO.approveDeposit(data.get(row).depositID,
                                       LocalEmployee.empNum
        )) {
            data.get(row).pendingApproval = false;
            data.get(row).approved = true;

            client.pendingBalance =
            (client.pendingBalance - data.get(row).value) == 0 ?
            0 :
            (client.pendingBalance - data.get(row).value);

            this.client.balance += data.get(row).value;
            fireTableRowsInserted(data.size() - 1, data.size() - 1);
        }

    }

    public void reprovarDeposito(int row) {
        DepositsDAO.denyDeposit(data.get(row).depositID,
                                LocalEmployee.empNum
        );

        data.get(row).pendingApproval = false;
        data.get(row).approved = false;
        client.pendingBalance =
        (client.pendingBalance - data.get(row).value) == 0 ?
        0 :
        (client.pendingBalance - data.get(row).value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    @Override
    public int getRowCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0 -> {
                return data.get(linha);
            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
