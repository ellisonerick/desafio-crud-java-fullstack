package com.nexum.desafio.pessoas.controller;

import com.nexum.desafio.pessoas.model.Pessoa;
import com.nexum.desafio.pessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*") // libera acesso do frontend
public class PessoaController {
    
    @Autowired
    private PessoaRepository repository;

    // Listar todas as pessoas
    @GetMapping
    public List<Pessoa> listar() {
        return repository.findAll();
    }

    // Buscar pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar nova pessoa
    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }

    // Atualizar pessoa existente
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa dadosAtualizados) {
        return repository.findById(id)
                .map(pessoa -> {
                    pessoa.setNome(dadosAtualizados.getNome());
                    pessoa.setCpfCnpj(dadosAtualizados.getCpfCnpj());
                    pessoa.setTelefone(dadosAtualizados.getTelefone());
                    pessoa.setEmail(dadosAtualizados.getEmail());
                    repository.save(pessoa);
                    return ResponseEntity.ok(pessoa);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir pessoa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

// Teste git