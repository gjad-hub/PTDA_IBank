/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.modcomment;

import java.awt.Component;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import pt.ua.ibank.DTO.ModeratorComment;

/**
 *
 * @author ricar
 */
public class PerfilCommentTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component comm = super.getTableCellRendererComponent(table, value,
                isSelected,
                hasFocus, row, column);
        var currCommentObject = (ModeratorComment) value;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm");
        // String str = formatter.format(currCommentObject.getDataFeita());
        //System.out.println(str);
        //var profileChildElement = new PerfilCommentTableElementPanel(
        //      currCommentObject.getFuncionarioID(),
        //    currCommentObject.getDescricao(),
        //  str
        //);
        return comm;
        //return profileChildElement;
        //return profileChildElement;
    }

}