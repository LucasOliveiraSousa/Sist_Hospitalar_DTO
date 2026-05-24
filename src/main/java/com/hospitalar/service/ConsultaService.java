package com.hospitalar.service;

import com.hospitalar.dto.ConsultaDTO;
import com.hospitalar.entity.Consulta;
import com.hospitalar.entity.Medico;
import com.hospitalar.entity.Paciente;
import com.hospitalar.mapper.ConsultaMapper;
import com.hospitalar.repository.ConsultaRepository;
import com.hospitalar.repository.MedicoRepository;
import com.hospitalar.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ConsultaMapper consultaMapper;

    public ConsultaDTO criarConsulta(ConsultaDTO consultaDTO) {
        Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id: " + consultaDTO.getPacienteId()));

        Medico medico = medicoRepository.findById(consultaDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com id: " + consultaDTO.getMedicoId()));

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setDataHoraConsulta(consultaDTO.getDataHoraConsulta());
        consulta.setDescricao(consultaDTO.getDescricao());
        consulta.setStatus("AGENDADA");
        consulta.setDataCadastro(LocalDateTime.now());

        Consulta consultaSalva = consultaRepository.save(consulta);
        return consultaMapper.toConsultaDTO(consultaSalva);
    }

    @Transactional(readOnly = true)
    public ConsultaDTO obterConsultaById(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com id: " + id));
        return consultaMapper.toConsultaDTO(consulta);
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> listarTodasConsultas() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultaMapper.toConsultaDTOList(consultas);
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> listarConsultasPorPaciente(Long pacienteId) {
        List<Consulta> consultas = consultaRepository.findByPacienteId(pacienteId);
        return consultaMapper.toConsultaDTOList(consultas);
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> listarConsultasPorMedico(Long medicoId) {
        List<Consulta> consultas = consultaRepository.findByMedicoId(medicoId);
        return consultaMapper.toConsultaDTOList(consultas);
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> listarConsultasPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        List<Consulta> consultas = consultaRepository.findByDataHoraConsultaBetween(inicio, fim);
        return consultaMapper.toConsultaDTOList(consultas);
    }

    public ConsultaDTO atualizarConsulta(Long id, ConsultaDTO consultaDTO) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com id: " + id));

        consulta.setDataHoraConsulta(consultaDTO.getDataHoraConsulta());
        consulta.setDescricao(consultaDTO.getDescricao());
        consulta.setStatus(consultaDTO.getStatus());

        Consulta consultaAtualizada = consultaRepository.save(consulta);
        return consultaMapper.toConsultaDTO(consultaAtualizada);
    }

    public void deletarConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com id: " + id));
        consultaRepository.delete(consulta);
    }

}
