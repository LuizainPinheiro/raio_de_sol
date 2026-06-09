package com.raiodesol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    private String nome;

    @NotBlank(message = "O cpf é obrigatorio")
    @Column(unique = true)
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatoria")
    private LocalDate dtNascimento;

    @NotNull(message = "A matricula é obrigatorio")
    private Boolean matriculaAtiva;

    @NotNull(message = "O plano é obrigatorio")
    @Enumerated(EnumType.STRING)
    private Plano plano;
}
