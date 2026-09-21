package com.leilao.experience.controller;

import com.leilao.experience.dto.LanceRequest;
import com.leilao.experience.dto.LanceResponse;
import com.leilao.experience.dto.LeilaoResponse;
import com.leilao.experience.dto.LeilaoUpdate;
import com.leilao.experience.service.LanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leilao/lance")
@RequiredArgsConstructor
public class LanceController {

    private final LanceService lanceService;

    @PostMapping
    public ResponseEntity realizarLance(@RequestBody LanceRequest request){
        return lanceService.realizarLance(request);
    }

    @GetMapping
    public ResponseEntity<List<LanceResponse>> buscarLances(){
        return ResponseEntity.ok().body(lanceService.buscarLances());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarLancePorUsuario(@PathVariable Long id){
        return ResponseEntity.ok().body(lanceService.buscarLancesPorUsuario(id));
    }


}
