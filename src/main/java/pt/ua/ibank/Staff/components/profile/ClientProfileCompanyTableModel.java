/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.Staff.components.profile;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import pt.ua.ibank.DTO.Funcionario;

/**
 *
 * @author ricar
 */
public class ClientProfileCompanyTableModel extends AbstractListModel {

    Funcionario funcionario;
    List<Pair<String, String>> data;

    public ClientProfileCompanyTableModel(Funcionario client) {
        this.funcionario = client;
        data = new ArrayList<>();
        data.add(new Pair<>("Nome:", funcionario.getNome()));
        data.add(new Pair<>("Email:", funcionario.getEmail()));
        data.add(new Pair<>("NIF:", funcionario.getNif()));
        data.add(new Pair<>("Morada:", funcionario.getMorada()));
    }

    public ClientProfileCompanyTableModel() {
        this.funcionario = null;
        data = new ArrayList<>();
        data.add(new Pair<>("Nome:\t", "email@email.pt"));
        data.add(new Pair<>("Email:\t", "92312312"));
        data.add(new Pair<>("NIF:\t", "1231245"));
        data.add(new Pair<>("Morada:\t ", "adas avenue 23da "));
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Object getElementAt(int index) {
        return data.get(index);
    }

}
