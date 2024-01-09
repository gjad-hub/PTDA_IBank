/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.DTO;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author ricar
 */
public class ModCommentProfile {

    private String funcionarioID;
    private String descricao;
    private LocalDateTime dataFeita;

    public ModCommentProfile(String funcionarioID, String descricao,
            LocalDateTime dataFeita) {
        this.funcionarioID = funcionarioID;
        this.descricao = descricao;
        this.dataFeita = dataFeita;
    }

    public String getFuncionarioID() {
        return funcionarioID;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataFeita() {
        return dataFeita;
    }

}
