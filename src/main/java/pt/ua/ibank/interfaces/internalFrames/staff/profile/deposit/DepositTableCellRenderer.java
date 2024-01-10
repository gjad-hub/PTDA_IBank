/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.deposit;

import pt.ua.ibank.interfaces.internalFrames.staff.profile.personal.*;
import pt.ua.ibank.interfaces.internalFrames.staff.profile.personal.PerfilTableElementPanel;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import pt.ua.ibank.DTO.Deposito;

/**
 *
 * @author ricar
 */
public class DepositTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component comm = super.getTableCellRendererComponent(table, value,
                isSelected,
                hasFocus, row, column);
        var currClientObject = (Deposito) value;

        DepositTableElementPanel depositChildElement;

        if (currClientObject.pendenteAprovacao) {
            depositChildElement = new DepositTableElementPanel(
                    Integer.toString(currClientObject.idDeposito),
                    Double.toString(currClientObject.valor)
            );
        } else {
            depositChildElement = new DepositTableElementPanel(
                    Integer.toString(currClientObject.idDeposito),
                    Double.toString(currClientObject.valor),
                    currClientObject.aprovado
            );
        }

        depositChildElement.setBackground(Color.WHITE);
        return depositChildElement;
    }

}
