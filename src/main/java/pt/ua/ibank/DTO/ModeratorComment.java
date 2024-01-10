package pt.ua.ibank.DTO;

import java.sql.Timestamp;

public class ModeratorComment {

    private final int id;
    private final String nomeFuncionario;
    private final String descricao;
    private final Timestamp dataFeita;

    public ModeratorComment(int id, String nomeFuncionario, String descricao,
            Timestamp dataFeita) {
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

    public Timestamp getDataFeita() {
        return dataFeita;
    }

    public int getId() {
        return id;
    }

}
