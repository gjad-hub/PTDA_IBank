/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.card;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import pt.ua.ibank.DTO.Cartao;

/**
 *
 * @author ricar
 */
public class PerfilCardTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component comm = super.getTableCellRendererComponent(table, value,
                isSelected,
                hasFocus, row, column);
        var currClientObject = (Cartao) value;

        SimpleDateFormat dateDisplay = new SimpleDateFormat("MM/yy");
        String expireDate = dateDisplay.format(currClientObject.dataValidade);
        String cardNumber = currClientObject.numCartao.substring(
                currClientObject.numCartao.length() - 4,
                currClientObject.numCartao.length());

        var profileChildElement = new PerfilCardTableElementPanel(
                cardNumber,
                expireDate,
                currClientObject.saldo_credito,
                currClientObject.estado
        );

        return profileChildElement;
    }

}
