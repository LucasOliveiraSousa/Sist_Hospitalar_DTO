package com.hospitalar.mapper;

import com.hospitalar.dto.ConsultaDTO;
import com.hospitalar.entity.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    @Mapping(source = "paciente.id", target = "pacienteId")
    @Mapping(source = "paciente.nome", target = "pacienteNome")
    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "medico.nome", target = "medicoNome")
    ConsultaDTO toConsultaDTO(Consulta consulta);

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "medico", ignore = true)
    Consulta toConsulta(ConsultaDTO consultaDTO);

    List<ConsultaDTO> toConsultaDTOList(List<Consulta> consultas);

    List<Consulta> toConsultaList(List<ConsultaDTO> consultaDTOs);

}
