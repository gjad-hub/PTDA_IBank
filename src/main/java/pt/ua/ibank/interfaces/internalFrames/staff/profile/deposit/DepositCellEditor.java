package pt.ua.ibank.interfaces.internalFrames.staff.profile.deposit;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit
 * this template
 */
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import pt.ua.ibank.DTO.Funcionario;
import pt.ua.ibank.interfaces.internalFrames.staff.profile.modcomment.DeleteCommentPanel;

/**
 *
 * @author ricar
 */
public class DepositCellEditor extends DefaultCellEditor {

    private DepositTableActionEvent event;

    public DepositCellEditor(DepositTableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row,
                                                 int column) {

        if (Funcionario.LocalFuncionario.isManager()) {
            return new DeleteCommentPanel();
        }

        DepositActionPanelElement action = new DepositActionPanelElement();
        action.InitEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }

}
