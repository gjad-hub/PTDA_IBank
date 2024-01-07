package pt.ua.ibank.interfaces.internalFrames.staff.transactions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pt.ua.ibank.interfaces.internalFrames.staff.accounts.*;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author ricar
 */
public class TransactionsCellEditor extends DefaultCellEditor {

    private TransactionsActionEvent event;

    public TransactionsCellEditor(TransactionsActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        TransactionsActionPanel action = new TransactionsActionPanel();
        action.InitEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }

}
