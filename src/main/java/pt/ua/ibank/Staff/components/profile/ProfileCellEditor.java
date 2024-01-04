package pt.ua.ibank.Staff.components.profile;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pt.ua.ibank.Staff.components.profile.ProfileActionPanel;
import com.mysql.cj.conf.ConnectionUrlParser;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ricar
 */
public class ProfileCellEditor extends DefaultCellEditor {

    private ProfileTableActionEvent event;

    public ProfileCellEditor(ProfileTableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        ConnectionUrlParser.Pair<String, String> componentValue = (ConnectionUrlParser.Pair) table.getModel().getValueAt(
                row, 0);
//AAAAAAAAA CENAS
        if (componentValue.left.contains("*")) {
            return new Component() {
            };
        }

        ProfileActionPanel action = new ProfileActionPanel();
        action.InitEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }

}
