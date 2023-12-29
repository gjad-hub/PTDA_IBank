/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Staff;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import pt.ua.ibank.DTO.Funcionario;

/**
 *
 * @author ricar
 */
public class ClientProfileCompanyDataListModel extends AbstractListModel {

    Funcionario funcionario;
    List<String> data;

    public ClientProfileCompanyDataListModel(Funcionario client) {
        this.funcionario = client;
        data = new ArrayList<>();
        data.add("Nome:\t" + funcionario.getNome());
        data.add("Email:\t" + funcionario.getEmail());
        data.add("NIF:\t" + funcionario.getNif());
        data.add("Morada:\t " + funcionario.getMorada());
    }

    public ClientProfileCompanyDataListModel() {
        this.funcionario = null;
        data = new ArrayList<>();
        data.add("Nome:\t" + "email@email.pt");
        data.add("Email:\t" + "92312312");
        data.add("NIF:\t" + "1231245");
        data.add("Morada:\t " + "adas avenue 23da ");
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
