/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.staff.components.contas;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author ricar
 */
public class ContasCellEditor extends DefaultCellEditor {

    ContasActionEvent event = null;

    public ContasCellEditor(ContasActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        ContasActionPanel action = new ContasActionPanel();

        action.InitEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }

}
