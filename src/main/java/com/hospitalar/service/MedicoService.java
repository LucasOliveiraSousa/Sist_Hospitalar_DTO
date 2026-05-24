package com.hospitalar.service;

import com.hospitalar.dto.MedicoDTO;
import com.hospitalar.entity.Medico;
import com.hospitalar.mapper.MedicoMapper;
import com.hospitalar.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    public MedicoDTO criarMedico(MedicoDTO medicoDTO) {
        Medico medico = medicoMapper.toMedico(medicoDTO);
        medico.setDataCadastro(LocalDate.now());
        medico.setStatus("ATIVO");
        Medico medicoSalvo = medicoRepository.save(medico);
        return medicoMapper.toMedicoDTO(medicoSalvo);
    }

    @Transactional(readOnly = true)
    public MedicoDTO obterMedicoById(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com id: " + id));
        return medicoMapper.toMedicoDTO(medico);
    }

    @Transactional(readOnly = true)
    public MedicoDTO obterMedicoByCrm(String crm) {
        Medico medico = medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com crm: " + crm));
        return medicoMapper.toMedicoDTO(medico);
    }

    @Transactional(readOnly = true)
    public List<MedicoDTO> listarTodosMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicoMapper.toMedicoDTOList(medicos);
    }

    @Transactional(readOnly = true)
    public List<MedicoDTO> listarMedicosPorEspecialidade(String especialidade) {
        List<Medico> medicos = medicoRepository.findByEspecialidade(especialidade);
        return medicoMapper.toMedicoDTOList(medicos);
    }

    public MedicoDTO atualizarMedico(Long id, MedicoDTO medicoDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com id: " + id));

        medico.setNome(medicoDTO.getNome());
        medico.setEspecialidade(medicoDTO.getEspecialidade());
        medico.setEmail(medicoDTO.getEmail());
        medico.setTelefone(medicoDTO.getTelefone());
        medico.setDataNascimento(medicoDTO.getDataNascimento());
        medico.setEndereco(medicoDTO.getEndereco());
        medico.setNumeroResidencia(medicoDTO.getNumeroResidencia());
        medico.setCidade(medicoDTO.getCidade());
        medico.setEstado(medicoDTO.getEstado());
        medico.setCep(medicoDTO.getCep());
        medico.setStatus(medicoDTO.getStatus());

        Medico medicoAtualizado = medicoRepository.save(medico);
        return medicoMapper.toMedicoDTO(medicoAtualizado);
    }

    public void deletarMedico(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com id: " + id));
        medicoRepository.delete(medico);
    }

}
