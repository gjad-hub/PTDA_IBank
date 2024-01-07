/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DTO.Cartao;

/**
 *
 * @author ricar
 */
public class PerfilCardTableModel extends AbstractTableModel {

    private final List<Cartao> data;

    public PerfilCardTableModel(int clientID) {
        data = null;
        // data = CartaoDAO.getCardListFromUserID(clientID);
    }

    //Cartao(String numCartao, Date dataValidade, String estado, int cliente, boolean credito, double saldo_credito, Date data_venciemnto) {
    public PerfilCardTableModel() {
        data = new ArrayList<>();
        java.sql.Date ea = new java.sql.Date(1, 2, 2002);
        data.add(new Cartao("1231", ea, 2.2, "estado"));
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
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
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
