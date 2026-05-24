package com.hospitalar.controller;

import com.hospitalar.dto.PacienteDTO;
import com.hospitalar.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO paciente = pacienteService.criarPaciente(pacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obterPaciente(@PathVariable Long id) {
        PacienteDTO paciente = pacienteService.obterPacienteById(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteDTO> obterPacienteByCpf(@PathVariable String cpf) {
        PacienteDTO paciente = pacienteService.obterPacienteByCpf(cpf);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarTodosPacientes() {
        List<PacienteDTO> pacientes = pacienteService.listarTodosPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO paciente = pacienteService.atualizarPaciente(id, pacienteDTO);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }

}
