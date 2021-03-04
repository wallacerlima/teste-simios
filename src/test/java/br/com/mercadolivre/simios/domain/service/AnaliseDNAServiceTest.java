package br.com.mercadolivre.simios.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.mercadolivre.simios.domain.exception.NaoESimioException;
import br.com.mercadolivre.simios.domain.exception.SequenciaDNAInvalidaException;
import br.com.mercadolivre.simios.domain.model.Especie;

@ExtendWith(SpringExtension.class)
public class AnaliseDNAServiceTest {

	@InjectMocks
	private AnaliseDNAService analiseDNAService;

	@Mock
	private CadastroConsultaDNAService cadastroConsulta;

	@Test
	public void deveAnalisarDNADeUmSimioComSequenciaNaHorizontal() {

		String[] dna = { "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG" };
		
		analiseDNAService.analisar(dna);
		
		verify(cadastroConsulta).salvar(dna, Especie.SIMIO.getValue());
	}
	
	@Test
	public void deveAnalisarDNADeUmSimioComSequenciaNaVertical() {

		String[] dna = { "CTGAGA", "CTGAGC", "CATTGT", "CGAGAG", "CCTCTA", "TCACTG" };
		
		analiseDNAService.analisar(dna);
		
		verify(cadastroConsulta).salvar(dna, Especie.SIMIO.getValue());
	}
	
	@Test
	public void deveAnalisarDNADeUmSimioComSequenciaNaDiagonal() {

		String[] dna = { "CTGCGA", "CTCAGC", "ACTTGT", "CGAGAG", "ACTCTA", "TCACTG" };
		
		analiseDNAService.analisar(dna);
		
		verify(cadastroConsulta).salvar(dna, Especie.SIMIO.getValue());
	}

	@Test
	public void deveCriticarAoAnalisarUmDNAHumano() {

		String[] dna = { "CTFCGA", "TGAGGC", "TFTCGT", "GGFACG", "CCCGGA", "SCTCCG" };

		NaoESimioException thrown = assertThrows(NaoESimioException.class, () -> analiseDNAService.analisar(dna));

		assertTrue(thrown.getMessage().contains("O DNA informado não pertence a um Símio"));

	}
	
	@Test
	public void deveCriticarAoInformarUmDNAInvalido() {

		String[] dna = { "CTFCGAA", "TGAGGC", "TFTCGT", "GGFACG", "CCCGGA", "SCTCCG" };

		SequenciaDNAInvalidaException thrown = assertThrows(SequenciaDNAInvalidaException.class, () -> analiseDNAService.analisar(dna));

		assertTrue(thrown.getMessage().contains("A sequência do DNA informado possui um tamanho inválido."));

	}
}
