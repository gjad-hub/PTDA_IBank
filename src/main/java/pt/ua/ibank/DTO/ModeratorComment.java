package pt.ua.ibank.DTO;

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
