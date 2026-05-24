package com.hospitalar.service;

import com.hospitalar.dto.PacienteDTO;
import com.hospitalar.entity.Paciente;
import com.hospitalar.mapper.PacienteMapper;
import com.hospitalar.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteDTO criarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.toPaciente(pacienteDTO);
        paciente.setDataCadastro(LocalDate.now());
        paciente.setStatus("ATIVO");
        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        return pacienteMapper.toPacienteDTO(pacienteSalvo);
    }

    @Transactional(readOnly = true)
    public PacienteDTO obterPacienteById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id: " + id));
        return pacienteMapper.toPacienteDTO(paciente);
    }

    @Transactional(readOnly = true)
    public PacienteDTO obterPacienteByCpf(String cpf) {
        Paciente paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com cpf: " + cpf));
        return pacienteMapper.toPacienteDTO(paciente);
    }

    @Transactional(readOnly = true)
    public List<PacienteDTO> listarTodosPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacienteMapper.toPacienteDTOList(pacientes);
    }

    public PacienteDTO atualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id: " + id));

        paciente.setNome(pacienteDTO.getNome());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setTelefone(pacienteDTO.getTelefone());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        paciente.setEndereco(pacienteDTO.getEndereco());
        paciente.setNumeroResidencia(pacienteDTO.getNumeroResidencia());
        paciente.setCidade(pacienteDTO.getCidade());
        paciente.setEstado(pacienteDTO.getEstado());
        paciente.setCep(pacienteDTO.getCep());
        paciente.setStatus(pacienteDTO.getStatus());

        Paciente pacienteAtualizado = pacienteRepository.save(paciente);
        return pacienteMapper.toPacienteDTO(pacienteAtualizado);
    }

    public void deletarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id: " + id));
        pacienteRepository.delete(paciente);
    }

}
