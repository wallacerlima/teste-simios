package br.com.mercadolivre.simios.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstatisticasResponse {
	
	@JsonProperty("count_mutant_dna")
	private Integer totalSimios;
	
	@JsonProperty("count_human_dna")
	private Integer totalHumanos;
	
	@JsonProperty("ratio")
	private Double razao;
	
}
