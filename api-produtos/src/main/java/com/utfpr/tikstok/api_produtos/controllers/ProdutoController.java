package com.utfpr.tikstok.api_produtos.controllers;

import com.utfpr.tikstok.api_produtos.dtos.ProdutoDTO;
import com.utfpr.tikstok.api_produtos.models.Produto;
import com.utfpr.tikstok.api_produtos.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAll(){
        return ResponseEntity.ok(produtoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.getById(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addOne(@Valid @RequestBody ProdutoDTO produtoDTO){

        Produto produto = produtoService.createProduto(produtoDTO);

        if(produto != null)
            return ResponseEntity.created(null).body(produto);
        else
            return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produtoAtualizado = produtoService.updateProduto(id, produtoDTO);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boolean deleted = produtoService.deleteProduto(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}