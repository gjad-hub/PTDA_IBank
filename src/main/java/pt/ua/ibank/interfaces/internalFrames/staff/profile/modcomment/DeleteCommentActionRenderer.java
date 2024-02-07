/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.modcomment;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import static pt.ua.ibank.utilities.Configs.LocalEmployee;

public class DeleteCommentActionRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, int row,
                                                   int column) {
        Component comm = super.getTableCellRendererComponent(table, "",
                                                             isSelected,
                                                             hasFocus, row,
                                                             column);
        if (LocalEmployee.isManager()) {
            return new DeleteCommentPanel();
        }
        return comm;
    }
}
