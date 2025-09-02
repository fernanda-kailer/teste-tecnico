package com.neoapp.crud.dto;

import com.neoapp.crud.infrastructure.etitys.Usuario;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class UsuarioDTO {
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private int idade;

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
        this.idade = calcularIdade(usuario.getDataNascimento());
    }

    private int calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) return 0;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
