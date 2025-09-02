package com.neoapp.crud.controller;

import com.neoapp.crud.infrastructure.etitys.Usuario;
import com.neoapp.crud.infrastructure.repository.UsuarioRepository;
import com.neoapp.crud.infrastructure.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String cpf) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getCpf().equals(cpf)) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(token);
    }
}
