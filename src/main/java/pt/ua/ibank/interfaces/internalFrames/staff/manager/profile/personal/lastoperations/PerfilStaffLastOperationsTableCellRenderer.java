/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.manager.profile.personal.lastoperations;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import pt.ua.ibank.DAO.ClientDAO;

public class PerfilStaffLastOperationsTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, int row,
                                                   int column) {
        Component comm = super.getTableCellRendererComponent(table, value,
                                                             isSelected,
                                                             hasFocus, row,
                                                             column);
        int currClientID = (int) value;
        String currClientName = ClientDAO.getClientNameByID(currClientID);
        var profileChildElement = new PerfilStaffLastOperationsTableElementPanel(
        Integer.toString(currClientID),
        currClientName);
        return profileChildElement;
    }

}
