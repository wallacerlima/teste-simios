package br.com.mercadolivre.simios.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	SEQUENCIA_DNA_INVALIDA("A Sequência do DNA é inválida"),
	NAO_E_SIMIO("Não é um Símio");
	
	private String title;
	
	private ProblemType(String title) {
		this.title = title;
	}
}
