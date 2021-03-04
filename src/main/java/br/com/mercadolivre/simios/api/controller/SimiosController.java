package br.com.mercadolivre.simios.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.simios.api.model.DNARequest;
import br.com.mercadolivre.simios.api.model.EstatisticasResponse;
import br.com.mercadolivre.simios.domain.model.Especie;
import br.com.mercadolivre.simios.domain.repository.ConsultaDNARepository;
import br.com.mercadolivre.simios.domain.service.AnaliseDNAService;

@RestController
public class SimiosController {

	@Autowired
	private AnaliseDNAService analiseDNAService;
	
	@Autowired
	private ConsultaDNARepository dnaRepository;
	
	@PostMapping(value = "/simian", produces = MediaType.APPLICATION_JSON_VALUE)
	public void isSimian(@RequestBody DNARequest DNARequest) {
		analiseDNAService.analisar(DNARequest.getDna());	
	}
	
	@GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstatisticasResponse> estatisticas() {
		
		EstatisticasResponse estatisticasResponse = new EstatisticasResponse();
		
		Integer totalSimios = dnaRepository.countByEspecie(Especie.SIMIO.getValue());
		Integer totalHumanos = dnaRepository.countByEspecie(Especie.HUMANO.getValue());
		
		estatisticasResponse.setTotalSimios(totalSimios);
		estatisticasResponse.setTotalHumanos(totalHumanos);
		estatisticasResponse.setRazao(totalSimios.doubleValue() / totalHumanos.doubleValue());
		
		return ResponseEntity.ok(estatisticasResponse);
	}

}
