package com.lanakarine.catalogolivros;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Livro> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro) {
        if (!dadosValidos(livro)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro dados) {
        if (!dadosValidos(dados)) {
            return ResponseEntity.badRequest().build();
        }
        return repository.findById(id).map(livro -> {
            livro.setTitulo(dados.getTitulo());
            livro.setAutor(dados.getAutor());
            livro.setAnoPublicacao(dados.getAnoPublicacao());
            return ResponseEntity.ok(repository.save(livro));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private boolean dadosValidos(Livro livro) {
        return livro != null
                && livro.getTitulo() != null && !livro.getTitulo().isBlank()
                && livro.getAutor() != null && !livro.getAutor().isBlank()
                && livro.getAnoPublicacao() != null && livro.getAnoPublicacao() > 0;
    }
}
