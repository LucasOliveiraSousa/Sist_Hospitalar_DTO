package com.hospitalar.mapper;

import com.hospitalar.dto.MedicoDTO;
import com.hospitalar.entity.Medico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    MedicoDTO toMedicoDTO(Medico medico);

    Medico toMedico(MedicoDTO medicoDTO);

    List<MedicoDTO> toMedicoDTOList(List<Medico> medicos);

    List<Medico> toMedicoList(List<MedicoDTO> medicoDTOs);

}
