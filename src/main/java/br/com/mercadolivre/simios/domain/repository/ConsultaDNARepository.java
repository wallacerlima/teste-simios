package br.com.mercadolivre.simios.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mercadolivre.simios.domain.model.ConsultaDNA;

@Repository
public interface ConsultaDNARepository extends JpaRepository<ConsultaDNA, String> {
	
	Integer countByEspecie(Character especie);
	
}
