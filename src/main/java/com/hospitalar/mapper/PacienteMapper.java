package com.hospitalar.mapper;

import com.hospitalar.dto.PacienteDTO;
import com.hospitalar.entity.Paciente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteDTO toPacienteDTO(Paciente paciente);

    Paciente toPaciente(PacienteDTO pacienteDTO);

    List<PacienteDTO> toPacienteDTOList(List<Paciente> pacientes);

    List<Paciente> toPacienteList(List<PacienteDTO> pacienteDTOs);

}
