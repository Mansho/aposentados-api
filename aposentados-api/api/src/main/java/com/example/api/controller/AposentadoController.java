package com.example.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.example.api.model.Aposentado;

@RestController
@RequestMapping("/api/aposentados")
@CrossOrigin(origins = "*") // Crucial para permitir o acesso do seu front-end
public class AposentadoController {

    // Banco de dados mockado em memória utilizando um Map thread-safe
    private final Map<String, Aposentado> bancoMockado = new ConcurrentHashMap<>();

    // Bloco estático para já iniciar com um registro de teste se quiser experimentar
    public AposentadoController() {
        bancoMockado.put("12345678900", new Aposentado("José da Silva", "12345678900"));
    }

    // 1. ENDPOINT DE CADASTRO (POST)
    @PostMapping
    public ResponseEntity<String> cadastrarAposentado(@RequestBody Aposentado aposentado) {
        if (aposentado.getCpf() == null || aposentado.getCpf().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF é obrigatório.");
        }
        
        // Salva (ou atualiza) no nosso Map na memória
        bancoMockado.put(aposentado.getCpf(), aposentado);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Aposentado cadastrado com sucesso!");
    }

    // 2. ENDPOINT DE CONSULTA POR CPF (GET)
    @GetMapping("/{cpf}")
    public ResponseEntity<Aposentado> consultarPorCpf(@PathVariable String cpf) {
        Aposentado aposentado = bancoMockado.get(cpf);
        
        if (aposentado == null) {
            // Retorna 404 se não achar o CPF informado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
        
        // Retorna o objeto (o Spring transforma automaticamente para o formato JSON)
        return ResponseEntity.ok(aposentado);
    }
}