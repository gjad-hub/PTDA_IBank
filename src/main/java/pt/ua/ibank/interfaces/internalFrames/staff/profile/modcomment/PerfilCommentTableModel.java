/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.modcomment;

import java.sql.Timestamp;
import pt.ua.ibank.DAO.CommentsDAO;
import pt.ua.ibank.DTO.ModeratorComment;
import static pt.ua.ibank.utilities.Configs.LocalEmployee;

public class PerfilCommentTableModel extends javax.swing.table.AbstractTableModel {

    private final java.util.List<ModeratorComment> data;
    private final int clientID;

    public PerfilCommentTableModel(int clientID) {
        this.clientID = clientID;
        data = CommentsDAO.getCommentListByID(clientID);
        System.out.println(data);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    public boolean adicionarComentario(String comment) {

        if (CommentsDAO.addNewComment(LocalEmployee.numFun,
                clientID,
                comment)) {
            data.add(new ModeratorComment(data.size(),
                                         LocalEmployee.name,
                                         comment,
                                         new Timestamp(
                                                 System.currentTimeMillis())));

            fireTableRowsInserted(data.size() - 1, data.size() - 1);
            return true;
        }
        return false;
    }

    public boolean apagarComentario(int row) {
        if (LocalEmployee.isManager()
                && CommentsDAO.deleteComment(data.get(row).getId())) {
            data.remove(row);
            fireTableRowsDeleted(data.size() - 1, data.size() - 1);
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return data.get(linha);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
