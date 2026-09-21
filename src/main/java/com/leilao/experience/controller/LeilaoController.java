package com.leilao.experience.controller;

import com.leilao.experience.dto.LeilaoRequest;
import com.leilao.experience.dto.LeilaoResponse;
import com.leilao.experience.service.LeilaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leilao")
@RequiredArgsConstructor
public class LeilaoController {

    private final LeilaoService leilaoService;

    @PostMapping
    public ResponseEntity<LeilaoResponse> adicionarLeilao(@RequestBody LeilaoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(leilaoService.adicionarNoLeilao(request));
    }

    @GetMapping
    public ResponseEntity<List<LeilaoResponse>> buscarItensLeiloados(){
        return ResponseEntity.ok().body(leilaoService.buscarItensLeilao());
    }
}
