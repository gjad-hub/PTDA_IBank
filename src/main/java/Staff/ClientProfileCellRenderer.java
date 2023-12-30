/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Staff;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author ricar
 */
public class ClientProfileCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {

        var currClientObject = (Pair<String, String>) value;

        JPanel profileChildElement = new ClientProfileListElementPanel(
                false,
                currClientObject.left,
                currClientObject.right);
        if (isSelected) {
            profileChildElement.setBackground(new Color(0xc7, 0xc6, 0xc5));
        }
        //return currClientPanel;
        return profileChildElement;
    }

}
