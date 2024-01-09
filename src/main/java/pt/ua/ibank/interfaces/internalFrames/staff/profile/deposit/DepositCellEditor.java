package pt.ua.ibank.interfaces.internalFrames.staff.profile.deposit;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pt.ua.ibank.interfaces.internalFrames.staff.profile.personal.*;
import pt.ua.ibank.interfaces.internalFrames.staff.profile.personal.PerfilTableActionEvent;
import pt.ua.ibank.interfaces.internalFrames.staff.profile.personal.PerfilActionPanelElement;
import com.mysql.cj.conf.ConnectionUrlParser;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author ricar
 */
public class DepositCellEditor extends DefaultCellEditor {

    private PerfilTableActionEvent event;

    public DepositCellEditor(PerfilTableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        ConnectionUrlParser.Pair<String, String> componentValue = (ConnectionUrlParser.Pair) table.getModel().getValueAt(
                row, 0);

        if (componentValue.left.contains(": ")) {
            return new Component() {
            };
        }

        PerfilActionPanelElement action = new PerfilActionPanelElement();
        action.InitEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }

}
