/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.deposit;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.ClientDAO;
import pt.ua.ibank.DAO.DepositsDAO;
import pt.ua.ibank.DTO.Cliente;
import pt.ua.ibank.DTO.Deposito;
import pt.ua.ibank.DTO.Funcionario;
import static pt.ua.ibank.utilities.Configs.LocalFuncionario;

/**
 *
 * @author ricar
 */
public class DepositTableModel extends AbstractTableModel {

    private final List<Deposito> data;
    public Cliente client = null;

    public DepositTableModel(int clientID) {
        data = DepositsDAO.getDeposits(clientID);
        client = ClientDAO.getClientByID(clientID);
    }

    public void aprovarDeposito(int row) {
        if (DepositsDAO.aproveDeposit(
                data.get(row).idDeposito,
                LocalFuncionario.numFun
        )) {
            data.get(row).pendenteAprovacao = false;
            data.get(row).aprovado = true;

            client.saldo_cativo =
            (client.saldo_cativo - data.get(row).valor) == 0 ?
            0 :
            (client.saldo_cativo - data.get(row).valor);

            this.client.saldo += data.get(row).valor;
            fireTableRowsInserted(data.size() - 1, data.size() - 1);
        }

    }

    public void reprovarDeposito(int row) {
        DepositsDAO.denyDeposit(
                data.get(row).idDeposito,
                LocalFuncionario.numFun
        );

        data.get(row).pendenteAprovacao = false;
        data.get(row).aprovado = false;
        client.saldo_cativo =
        (client.saldo_cativo - data.get(row).valor) == 0 ?
        0 :
        (client.saldo_cativo - data.get(row).valor);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    @Override
    public int getRowCount() {
        return data.size();
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
