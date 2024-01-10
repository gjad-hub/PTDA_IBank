/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.deposit;

import pt.ua.ibank.interfaces.internalFrames.staff.profile.personal.*;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.ClientDAO;
import pt.ua.ibank.DTO.Cliente;
import pt.ua.ibank.DTO.Deposito;

/**
 *
 * @author ricar
 */
public class DepositTableModel extends AbstractTableModel {

    private final List<Deposito> data;
    public Cliente client = null;

    public DepositTableModel(String clientEmail) {
        data = new ArrayList<>();
        client = ClientDAO.getClientByEmail(clientEmail);
    }

    public DepositTableModel() {
        data = new ArrayList<>();
        data.add(new Deposito(1, 2.0));
        data.add(new Deposito(2, 2.0, false));
        data.add(new Deposito(3, 2.0, true));
    }

    public void aprovarDeposito(int row) {
        data.get(row).pendenteAprovacao = false;
        data.get(row).aprovado = true;

        //this.client.saldo_cativo -= data.get(row).valor;
        this.client.saldo += data.get(row).valor;
    }

    public void reprovarDeposito(int row) {
        data.get(row).pendenteAprovacao = false;
        data.get(row).aprovado = false;

        //this.client.saldo_cativo -= data.get(row).valor;
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
