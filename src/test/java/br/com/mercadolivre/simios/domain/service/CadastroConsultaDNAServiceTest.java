package br.com.mercadolivre.simios.domain.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.mercadolivre.simios.domain.model.ConsultaDNA;
import br.com.mercadolivre.simios.domain.model.Especie;
import br.com.mercadolivre.simios.domain.repository.ConsultaDNARepository;

@ExtendWith(SpringExtension.class)
public class CadastroConsultaDNAServiceTest {
	
	@InjectMocks
	private CadastroConsultaDNAService cadastroConsulta;
	
	@Mock
	private ConsultaDNARepository consultaDNARepository;
	
	@Test
	public void deveSalvarUmaConsultaDeDNA() {
		
		String[] dna = { "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG" };
		
		cadastroConsulta.salvar(dna, Especie.SIMIO.getValue());
		
		ArgumentCaptor<ConsultaDNA> argumentCaptor = ArgumentCaptor.forClass(ConsultaDNA.class);
		
		verify(consultaDNARepository).save(argumentCaptor.capture());
	}
}
