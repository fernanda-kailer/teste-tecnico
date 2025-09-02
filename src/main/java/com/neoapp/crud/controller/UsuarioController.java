package com.neoapp.crud.controller;


import com.neoapp.crud.business.UsuarioService;
import com.neoapp.crud.dto.UsuarioDTO;
import com.neoapp.crud.infrastructure.etitys.Usuario;
import com.neoapp.crud.infrastructure.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario){
        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/email")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam String email){
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        UsuarioDTO dto = new UsuarioDTO(usuario);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuarioPorId(@PathVariable Long id,
                                                      @RequestBody Usuario usuario){
        usuarioService.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Long id){
        usuarioService.deletarUsuarioPorId(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listarUsuarios(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable,
            @RequestParam(required = false) String nome) {
        Page<Usuario> usuariosPage;
        if (nome != null && !nome.isEmpty()) {
            usuariosPage = usuarioService.buscarUsuariosPorNome(nome, pageable);
        } else {
            usuariosPage = usuarioService.listarTodosUsuarios(pageable);
        }
        return ResponseEntity.ok(usuariosPage.map(UsuarioDTO::new));
    }
}
