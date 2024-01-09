/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.personal;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DAO.ClientDAO;
import pt.ua.ibank.DTO.Cliente;

/**
 *
 * @author ricar
 */
public class PerfilPersonalTableModel extends AbstractTableModel {

    private final List<Pair<String, String>> data;
    public Cliente client = null;

    public PerfilPersonalTableModel(String clientEmail) {
        data = new ArrayList<>();
        client = ClientDAO.getClientByEmail(clientEmail);
        setupData();
    }

    public PerfilPersonalTableModel() {
        data = new ArrayList<>();
        client = new Cliente(1, "jose",
                "Avenida das coves", "asd",
                "123123", "2131", "3",
                "39", 20.4);
        setupData();
    }

    public void setupData() {
        data.add(new Pair<>("ID: ", Integer.toString(client.numCliente)));
        data.add(new Pair<>("Nome:", client.nome));
        data.add(new Pair<>("Email:", client.telemovel));
        data.add(new Pair<>("NIF: ", client.nif));
        data.add(new Pair<>("Morada:", client.morada));
        data.add(new Pair<>(
                "Cartao default: ", client.cartaoDefault));
        data.add(new Pair<>(
                "Saldo: ", Double.toString(client.saldo)));
    }

    public void aprovarDepositoLocalmente(int saldoCativoAprovado) {
        this.client.saldo += saldoCativoAprovado;
        this.client.saldo_cativo -= saldoCativoAprovado;
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
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            String oldEmail = client.email;
            data.set(rowIndex, (Pair<String, String>) aValue);

            System.out.println(
                    "value: " + data.get(rowIndex).left
                    + "on row " + rowIndex + " on column " + columnIndex
                    + "changed! to " + data.get(rowIndex).right);

            //ClientDAO.UpdateClient(client.nome, client.morada,
            //      client.email, client.telemovel,
            //    client.nif, oldEmail);
        } catch (java.lang.ClassCastException e) {
            //nothing
        }
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
        return true; // only the second column should be clickable
    }

}
