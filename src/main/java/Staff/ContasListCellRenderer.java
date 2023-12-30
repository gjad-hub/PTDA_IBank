/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Staff;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JPanel;
import Staff.ClientOverview;

/**
 *
 * @author ricar
 */
public class ContasListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {

        var currClientObject = (ClientOverview) value;

        JPanel currClientPanel = new ContasListPanel(
                currClientObject.getNome(),
                currClientObject.getSaldo(),
                currClientObject.getDataCriacao());

        if (isSelected) {
            currClientPanel.setBackground(new Color(0xc7, 0xc6, 0xc5));
        }
        return currClientPanel;
    }

}
