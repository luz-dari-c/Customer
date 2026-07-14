package com.sysnet.pruebaTecnica.controller;


import com.sysnet.pruebaTecnica.dto.request.ClienteRequest;
import com.sysnet.pruebaTecnica.dto.response.ClienteResponse;
import com.sysnet.pruebaTecnica.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> guardar(@Valid @RequestBody ClienteRequest request) {

        ClienteResponse cliente = clienteService.guardar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() {

        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequest request) {

        ClienteResponse cliente = clienteService.actualizar(id, request);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        clienteService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportarExcel() {

        byte[] excel = clienteService.exportarExcel();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientes.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);

    }
}