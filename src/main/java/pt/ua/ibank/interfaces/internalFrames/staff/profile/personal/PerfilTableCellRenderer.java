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

/**
 *
 * @author ricar
 */
public class PerfilTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, int row,
                                                   int column) {
        Component comm = super.getTableCellRendererComponent(table, value,
                                                             isSelected,
                                                             hasFocus, row,
                                                             column);
        var currClientObject = (TableElement) value;

        var profileChildElement = new PerfilTableElementPanel(
        false,
        currClientObject.key,
        currClientObject.pair);

        if (isSelected && row % 2 == 0) {
            profileChildElement.setBackground(Color.WHITE);
        }
        return profileChildElement;
    }

}
