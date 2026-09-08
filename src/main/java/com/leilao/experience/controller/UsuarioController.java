package com.leilao.experience.controller;

import com.leilao.experience.dto.UsuarioRequest;
import com.leilao.experience.dto.UsuarioResponse;
import com.leilao.experience.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leilao/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody @Valid UsuarioRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(request));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> buscarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuarioPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuarioId(@PathVariable Long id, @RequestBody @Valid UsuarioRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizarUsuarioId(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuarioId(@PathVariable Long id){
        usuarioService.deletarUsuarioPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
