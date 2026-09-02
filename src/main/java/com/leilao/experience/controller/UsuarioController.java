package com.leilao.experience.controller;

import com.leilao.experience.dto.UsuarioRequest;
import com.leilao.experience.dto.UsuarioResponse;
import com.leilao.experience.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leilao/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioResponse criarUsuario(@RequestBody UsuarioRequest request){
        return usuarioService.criarUsuario(request);
    }

    @GetMapping
    public List<UsuarioResponse> buscarUsuarios(){
        return usuarioService.buscarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponse buscarUsuarioId(@PathVariable Long id){
        return usuarioService.buscarUsuarioPorId(id);
    }

    @PutMapping("/{id}")
    public UsuarioResponse atualizarUsuarioId(@PathVariable Long id,@RequestBody UsuarioRequest request){
        return usuarioService.atualizarUsuarioId(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuarioId(@PathVariable Long id){
        usuarioService.deletarUsuarioPorId(id);
    }
}
