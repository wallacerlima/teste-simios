package br.com.mercadolivre.simios.domain.model;

import lombok.Getter;

@Getter
public enum Especie {

	HUMANO('H'), SIMIO('S');

	private char value;

	private Especie(char c) {
		this.value = c;
	}

}
