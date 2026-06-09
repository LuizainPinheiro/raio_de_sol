package com.raiodesol.repository;

import com.raiodesol.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository  extends JpaRepository<Aluno,Long> {
    boolean existsByCpf(String cpf);
}
