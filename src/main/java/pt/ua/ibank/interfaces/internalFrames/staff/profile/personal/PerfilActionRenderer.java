/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.personal;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import pt.ua.ibank.utilities.TableElement;

public class PerfilActionRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, int row,
                                                   int column) {
        Component comm = super.getTableCellRendererComponent(table, "",
                                                             isSelected,
                                                             hasFocus, row,
                                                             column);

        TableElement componentValue =
                     ((TableElement) table.getModel().getValueAt(row, 0));

        if (componentValue.key.contains(": ")) {
            comm.setEnabled(false);
            comm.setBackground(Color.WHITE);
            return comm;
        }

        PerfilActionPanelElement action = new PerfilActionPanelElement(
                                 componentValue.pair);
        action.setBackground(Color.WHITE);
        return action;

    }

}
