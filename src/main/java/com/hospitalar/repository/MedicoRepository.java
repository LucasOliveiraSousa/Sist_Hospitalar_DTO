package com.hospitalar.repository;

import com.hospitalar.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByCpf(String cpf);

    Optional<Medico> findByCrm(String crm);

    Optional<Medico> findByEmail(String email);

    List<Medico> findByEspecialidade(String especialidade);

}
