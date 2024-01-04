package pt.ua.ibank.staff.components.listacontas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pt.ua.ibank.staff.componentes.perfil.*;
import pt.ua.ibank.staff.componentes.perfil.PerfilActionPanel;
import com.mysql.cj.conf.ConnectionUrlParser;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import pt.ua.ibank.staff.componentes.perfil.PerfilTableActionEvent;

/**
 *
 * @author ricar
 */
public class PerfilCellEditor extends DefaultCellEditor {

    private PerfilTableActionEvent event;

    public PerfilCellEditor(PerfilTableActionEvent event) {
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
