package com.raiodesol.repository;

import com.raiodesol.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository  extends JpaRepository<Aluno,Long> {
}
