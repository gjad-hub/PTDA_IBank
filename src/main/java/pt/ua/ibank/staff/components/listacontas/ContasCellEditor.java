/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.staff.components.listacontas;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author ricar
 */
public class ContasCellEditor extends DefaultCellEditor {

    public ContasCellEditor(JCheckBox checkBox) {
        super(checkBox);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        ContasActionPanel action = new ContasActionPanel();

        //ction.InitEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }

}
