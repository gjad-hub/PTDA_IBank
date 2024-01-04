/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.staff.componentes.perfil;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pt.ua.ibank.DTO.Cliente;

/**
 *
 * @author ricar
 */
public class PerfilPersonalTableModel extends AbstractTableModel {

    private Cliente client;
    private List<Pair<String, String>> data;

    public PerfilPersonalTableModel(Cliente client) {
        this.client = client;
        data = new ArrayList<>();
        init();
    }

    public PerfilPersonalTableModel() {
        this.client = new Cliente(1, "jose", "Avenida das coves", "asd",
                "123123", "2131", "3",
                "39", 20.4);
        data = new ArrayList<>();
        init();

    }

    public void init() {
        data.add(new Pair<>("Nome:", client.nome));
        data.add(new Pair<>("Email:", client.telemovel));
        data.add(new Pair<>("*NIF:", client.nif));
        data.add(new Pair<>("Morada:", client.morada));
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
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            data.set(rowIndex, (Pair<String, String>) aValue);
            System.out.println(
                    "value: " + data.get(rowIndex).left + "on row " + rowIndex + " on column " + columnIndex + "changed! to " + data.get(
                    rowIndex).right);

        } catch (java.lang.ClassCastException e) {
            //nothing troll
        }
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
