/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.modcomment;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import pt.ua.ibank.DTO.Funcionario;

/**
 *
 * @author ricar
 */
public class PerfilCommentCellEditor extends DefaultCellEditor {

    DeleteCommentActionEvent event = null;

    public PerfilCommentCellEditor(DeleteCommentActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row,
                                                 int column) {

        if (Funcionario.LocalFuncionario.isManager()) {
            DeleteCommentPanel action = new DeleteCommentPanel();

            action.InitEvent(event, row);
            action.setBackground(table.getSelectionBackground());
            return action;
        }
        return new Component() {
        };
    }

}
