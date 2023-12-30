/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.Staff;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import pt.ua.ibank.DTO.Cliente;

/**
 *
 * @author ricar
 */
public class ClientProfilePersonalDataListModel extends AbstractListModel {

    private Cliente client;
    private List<Pair<String, String>> data;

    public ClientProfilePersonalDataListModel(Cliente client) {
        this.client = client;
        data = new ArrayList<>();
        init();
    }
//nteger numCliente, String nome, String morada, String email, String telemovel, String nif, String password, String numConta, Double saldo

    public ClientProfilePersonalDataListModel() {
        this.client = new Cliente(1, "jose", "Avenida das coves", "asd",
                "123123", "2131", "3",
                "39", 20.4);
        data = new ArrayList<>();
        init();

    }

    public void init() {
        data.add(new Pair<>("Nome:", client.nome));
        data.add(new Pair<>("Email:", client.telemovel));
        data.add(new Pair<>("NIF:", client.nif));
        data.add(new Pair<>("Morada:", client.morada));
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
