package com.hospitalar.controller;

import com.hospitalar.dto.ConsultaDTO;
import com.hospitalar.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDTO> criarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        ConsultaDTO consulta = consultaService.criarConsulta(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> obterConsulta(@PathVariable Long id) {
        ConsultaDTO consulta = consultaService.obterConsultaById(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarTodasConsultas() {
        List<ConsultaDTO> consultas = consultaService.listarTodasConsultas();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorPaciente(@PathVariable Long pacienteId) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorPaciente(pacienteId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorMedico(@PathVariable Long medicoId) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorMedico(medicoId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorPeriodo(inicio, fim);
        return ResponseEntity.ok(consultas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> atualizarConsulta(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {
        ConsultaDTO consulta = consultaService.atualizarConsulta(id, consultaDTO);
        return ResponseEntity.ok(consulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }

}
