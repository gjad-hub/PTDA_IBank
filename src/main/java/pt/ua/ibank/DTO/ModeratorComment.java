package pt.ua.ibank.DTO;

import java.sql.Timestamp;

/**
 * Objeto de uma class que representa uma transação feita
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 9 de Dezembro, 2023
 */
public class ModeratorComment {

    private final int id;
    private final String nomeFuncionario;
    private final String conteudo;
    private final Timestamp dataFeita;

    /**
     * Constructor: uma instancia com id, nomeFuncionario,conteudo,dataFeita
     *
     * @param id              ID de instancia de comentario
     * @param nomeFuncionario Nome de Autor da mensagem
     * @param conteudo        Conteudo da mensagem
     * @param dataFeita       Data em que foi postado
     */
    public ModeratorComment(int id, String nomeFuncionario, String conteudo,
                            Timestamp dataFeita) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.conteudo = conteudo;
        this.dataFeita = dataFeita;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getDescricao() {
        return conteudo;
    }

    public Timestamp getDataFeita() {
        return dataFeita;
    }

    public int getId() {
        return id;
    }

}
