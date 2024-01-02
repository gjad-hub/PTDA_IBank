/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.Staff.components.accountList;

import java.sql.Date;

/**
 *
 * @author ricar
 */
public class ClientOverview {

    private final Integer id;
    private final String nome;
    private final Double saldo;
    private final Date dataCriacao;

    public ClientOverview(Integer id, String nome, Double saldo,
            Date dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

}
