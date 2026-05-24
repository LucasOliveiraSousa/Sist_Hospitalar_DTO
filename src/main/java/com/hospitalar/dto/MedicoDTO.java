package com.hospitalar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String crm;
    private String especialidade;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String endereco;
    private String numeroResidencia;
    private String cidade;
    private String estado;
    private String cep;
    private LocalDate dataCadastro;
    private String status;

}
