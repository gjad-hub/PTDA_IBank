/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.manager.profile.personal.lastoperations;

import pt.ua.ibank.DAO.ClienteFuncionarioDAO;

/**
 *
 * @author ricar
 */
public class PerfilStaffLastOperationsTableModel extends javax.swing.table.AbstractTableModel {

    private final java.util.List<Integer> data;

    public PerfilStaffLastOperationsTableModel(int clientID) {
        data = ClienteFuncionarioDAO.getClientsInteractedListByID(clientID);
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
        return data.get(linha);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
