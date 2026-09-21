package com.leilao.experience.controller;

import com.leilao.experience.dto.ProdutoRequest;
import com.leilao.experience.dto.ProdutoResponse;
import com.leilao.experience.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leilao/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody @Valid ProdutoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(request));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> buscarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarProdutos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarPorId(@PathVariable Long id,@RequestBody @Valid ProdutoRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizarProdutoPorId(id, request));
    }

    @DeleteMapping("/{id}")
        public ResponseEntity deletarPorId(@PathVariable Long id){
            produtoService.deletarProdutoPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
}
