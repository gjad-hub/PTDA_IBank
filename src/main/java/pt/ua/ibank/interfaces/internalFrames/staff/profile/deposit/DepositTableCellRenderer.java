/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.deposit;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import pt.ua.ibank.DTO.Deposit;

public class DepositTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, int row,
                                                   int column) {
        Component comm = super.getTableCellRendererComponent(table, value,
                                                             isSelected,
                                                             hasFocus, row,
                                                             column);
        var currClientObject = (Deposit) value;

        DepositTableElementPanel depositChildElement;

        if (currClientObject.pendingApproval) {
            depositChildElement = new DepositTableElementPanel(
            Integer.toString(currClientObject.depositID),
            Double.toString(currClientObject.value)
    );
        } else {
            depositChildElement = new DepositTableElementPanel(
            Integer.toString(currClientObject.depositID),
            Double.toString(currClientObject.value),
            currClientObject.approved
    );
        }

        depositChildElement.setBackground(Color.WHITE);
        return depositChildElement;
    }

}
