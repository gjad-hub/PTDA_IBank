/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.modcomment;

import java.time.LocalDateTime;
import pt.ua.ibank.interfaces.internalFrames.staff.profile.card.*;
import pt.ua.ibank.DTO.Cartao;
import pt.ua.ibank.DTO.ModeratorComment;

/**
 *
 * @author ricar
 */
public class PerfilCommentTableModel extends javax.swing.table.AbstractTableModel {

    private final java.util.List<ModeratorComment> data;

    public PerfilCommentTableModel(String clientEmail) {
        data = null;
        // data = CommentsDAO.getProfileCommentListFromUserID(clientID);
    }

    public PerfilCommentTableModel() {
        data = new java.util.ArrayList<>();
        //data.add(new ModeratorComment(
        //        2,
        //          " this user is very awesomeASDKLJASJKLDJASKLDJSKLDJSKLDJADKLSJAL ",
        //            "String",new Date;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

//    public boolean adicionarComentario() {
//
//    }
//
//    public boolean removerComentario() {
//        CommentsDAO.deleteBook(0)
//    }
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0 -> {
                return data.get(linha);
            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
