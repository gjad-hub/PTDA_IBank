/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.staff.componentes.perfil;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DTO.Cartao;
import pt.ua.ibank.DTO.Funcionario;

/**
 *
 * @author ricar
 */
public class PerfilCardTableModel extends AbstractTableModel {

    private final Funcionario funcionario;
    private final List<Cartao> data;

    public PerfilCardTableModel(Funcionario client) {
        this.funcionario = client;
        data = new ArrayList<>();
        //data.add(new Pair<>("Nome:", funcionario.getNome()));
        //data.add(new Pair<>("ID:", funcionario.getEmail()));
        //data.add(new Pair<>("Cargo:", funcionario.getNif()));
        //data.add(new Pair<>("Morada:", funcionario.getMorada()));
    }

    public PerfilCardTableModel() {
        this.funcionario = null;
        data = new ArrayList<>();
        //data.add(new Pair<>("Nome:\t", "email@email.pt"));
        //data.add(new Pair<>("ID:\t", "92312312"));
        //data.add(new Pair<>("Cargo:\t", "1231245"));
        //data.add(new Pair<>("Morada:\t ", "adas avenue 23da "));
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    //idk dude, i didnt even want to use this shit interface :shrug:
    @Override
    public int getColumnCount() {
        return 2;
    }

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
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
