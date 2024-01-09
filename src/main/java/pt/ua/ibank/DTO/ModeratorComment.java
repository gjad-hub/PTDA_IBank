/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.DTO;

/**
 *
 * @author ricar
 */
public class ModeratorComment {

    private final int id;
    private final String nomeFuncionario;
    private final String descricao;
    private final java.sql.Date dataFeita;

    public ModeratorComment(int id, String nomeFuncionario, String descricao,
            java.sql.Date dataFeita) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.descricao = descricao;
        this.dataFeita = dataFeita;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getDescricao() {
        return descricao;
    }

    public java.sql.Date getDataFeita() {
        return dataFeita;
    }

    public int getId() {
        return id;
    }

}
