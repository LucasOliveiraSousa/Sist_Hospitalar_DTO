package com.hospitalar.controller;

import com.hospitalar.dto.MedicoDTO;
import com.hospitalar.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoDTO> criarMedico(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO medico = medicoService.criarMedico(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> obterMedico(@PathVariable Long id) {
        MedicoDTO medico = medicoService.obterMedicoById(id);
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<MedicoDTO> obterMedicoByCrm(@PathVariable String crm) {
        MedicoDTO medico = medicoService.obterMedicoByCrm(crm);
        return ResponseEntity.ok(medico);
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarTodosMedicos() {
        List<MedicoDTO> medicos = medicoService.listarTodosMedicos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<MedicoDTO>> listarMedicosPorEspecialidade(@PathVariable String especialidade) {
        List<MedicoDTO> medicos = medicoService.listarMedicosPorEspecialidade(especialidade);
        return ResponseEntity.ok(medicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedico(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO medico = medicoService.atualizarMedico(id, medicoDTO);
        return ResponseEntity.ok(medico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }

}
