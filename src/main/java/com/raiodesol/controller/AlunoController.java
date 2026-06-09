package com.raiodesol.controller;

import com.raiodesol.model.Aluno;
import com.raiodesol.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody Aluno aluno) {
        var salvo = repository.save(aluno);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Valid @RequestBody Aluno aluno){
        Optional<Aluno> alunoExist = repository.findById(id);

        if (alunoExist.isPresent()){
            Aluno alunoAtualizado = alunoExist.get();

            alunoAtualizado.setNome(aluno.getNome());
            alunoAtualizado.setCpf(aluno.getCpf());
            alunoAtualizado.setDtNascimento(aluno.getDtNascimento());
            alunoAtualizado.setPlano(aluno.getPlano());
            alunoAtualizado.setMatriculaAtiva(aluno.getMatriculaAtiva());

            Aluno salvo = repository.save(alunoAtualizado);
            return ResponseEntity.ok(salvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletar(@PathVariable Long id){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id){
        return repository.findById(id)
                .map(aluno -> ResponseEntity.ok(aluno))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricula/{id}")
    public ResponseEntity<String> matriculaAtiva(@PathVariable Long id){
        Optional<Aluno> aluno = repository.findById(id);

        if (aluno.isPresent()) {
            String status = aluno.get().getMatriculaAtiva()
                    ? "Matrícula ativa"
                    : "Matrícula inativa";

            return ResponseEntity.ok(status);
        }

        return ResponseEntity.notFound().build();
    }
}