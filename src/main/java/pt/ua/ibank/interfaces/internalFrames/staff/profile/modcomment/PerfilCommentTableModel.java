/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.modcomment;

import java.sql.Timestamp;
import pt.ua.ibank.DAO.CommentsDAO;
import pt.ua.ibank.DTO.Funcionario;

import pt.ua.ibank.DTO.ModeratorComment;

/**
 *
 * @author ricar
 */
public class PerfilCommentTableModel extends javax.swing.table.AbstractTableModel {

    private final java.util.List<ModeratorComment> data;
    private final int clientID;

    public PerfilCommentTableModel(int clientID) {
        this.clientID = clientID;
        data = CommentsDAO.getCommentListByID(clientID);
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

        if (CommentsDAO.addNewComment(
                clientID,
                Funcionario.LocalFuncionario.getNumFun(),
                comment)) {
            data.add(
                    new ModeratorComment(data.size(),
                            Funcionario.LocalFuncionario.nome,
                            comment,
                            new Timestamp(System.currentTimeMillis())));

            fireTableRowsInserted(data.size() - 1, data.size() - 1);
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
