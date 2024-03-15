package br.com.fiap.cashflowpro.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cashflowpro.model.Categoria;
import br.com.fiap.cashflowpro.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("categoria")
@Slf4j
public class CategoriaController {

    // List<Categoria> repository = new ArrayList<>();

    @Autowired // CDI - Injeção de Dependência
    CategoriaRepository repository;

    @GetMapping
    public List<Categoria> index() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @PostMapping
    @ResponseStatus(CREATED)
    public Categoria create(@RequestBody Categoria categoria) {
        log.info("cadastrando categoria: {}", categoria);
        return repository.save(categoria);
    }

    @SuppressWarnings("null")
    @GetMapping("{id}")
    public ResponseEntity<Categoria> get(@PathVariable Long id) {
        log.info("buscando categoria com id {}", id);

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @SuppressWarnings("null")
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando categoria {}", id);
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "id da categoria nao encontrado"));
        repository.deleteById(id);
    }

    @SuppressWarnings("null")
    @PutMapping("{id}")
    @ResponseStatus(OK)
    public Categoria update(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {
        log.info("atualizando categoria com id {} para {}", id, categoria);

        VerififcarSeExisteCategoria(id);

        var categoriaAtualizada = new Categoria();

        BeanUtils.copyProperties(categoria, categoriaAtualizada);

        categoriaAtualizada.setId(id);
        return repository.save(categoriaAtualizada);

    }

    @SuppressWarnings("null")
    private void VerififcarSeExisteCategoria(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Nao foi encontrado o id incerido"));
    }

}
