package br.com.mercadolivre.simios.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Setter;

@Setter
@Entity(name = "consulta_dna")
public class ConsultaDNA {
	
	@Id
	private String dna;
	
	@Column(nullable = false)
	private Character especie;
	
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataConsulta;
}
