/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.modcomment;

import java.sql.Timestamp;
import java.time.LocalDate;

import pt.ua.ibank.DTO.ModeratorComment;

/**
 *
 * @author ricar
 */
public class PerfilCommentTableModel extends javax.swing.table.AbstractTableModel {

    private final java.util.List<ModeratorComment> data;
    String clientEmail;

    public PerfilCommentTableModel(String clientEmail) {
        this.clientEmail = clientEmail;
        data = null;
        // data = CommentsDAO.getProfileCommentListFromUserID(clientID);
    }

    public PerfilCommentTableModel() {
        data = new java.util.ArrayList<>();

    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    public boolean adicionarComentario(String comment) {
        data.add(new ModeratorComment(data.size(), "admin", comment,
                new Timestamp(2)));
        //return CommentsDAO.addNewComment(this.clientEmail, c);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
        return true;
    }

    public boolean removerComentario(int row) {
        data.remove(row);
        //int id = data.get(row).getId();
        //return CommentsDAO.deleteComment(id);
        fireTableRowsDeleted(row, row);
        return true;
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
