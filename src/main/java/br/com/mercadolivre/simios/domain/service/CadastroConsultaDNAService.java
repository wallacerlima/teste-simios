package br.com.mercadolivre.simios.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mercadolivre.simios.domain.model.ConsultaDNA;
import br.com.mercadolivre.simios.domain.repository.ConsultaDNARepository;

@Service
public class CadastroConsultaDNAService {
	
	@Autowired
	private ConsultaDNARepository consultaDNARepository;
	
	@Transactional
	public void salvar(String[] dna, Character especie) {
		
		ConsultaDNA consultaDNA = new ConsultaDNA();
		consultaDNA.setDna(String.join("", dna));
		consultaDNA.setEspecie(especie);
		consultaDNA.setDataConsulta(LocalDateTime.now());
		
		consultaDNARepository.save(consultaDNA);
	}
}
