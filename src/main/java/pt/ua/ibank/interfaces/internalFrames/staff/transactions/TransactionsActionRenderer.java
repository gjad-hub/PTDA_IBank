/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.transactions;

import pt.ua.ibank.interfaces.internalFrames.staff.accounts.*;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ricar
 */
public class TransactionsActionRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component comm = super.getTableCellRendererComponent(table, value,
                isSelected,
                hasFocus, row, column);

        TransactionsActionPanel action = new TransactionsActionPanel();

        return action;

    }

}
