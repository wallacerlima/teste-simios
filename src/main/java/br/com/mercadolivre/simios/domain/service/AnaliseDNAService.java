package br.com.mercadolivre.simios.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.simios.domain.exception.NaoESimioException;
import br.com.mercadolivre.simios.domain.exception.SequenciaDNAInvalidaException;
import br.com.mercadolivre.simios.domain.model.Especie;

@Service
public class AnaliseDNAService {
	
	@Autowired 
	private CadastroConsultaDNAService cadastroConsulta;
	
	public void analisar(String[] dna) {
		
		boolean ehSimio = isSimian(dna);
		
		if(ehSimio) {
			cadastroConsulta.salvar(dna, Especie.SIMIO.getValue());
		} else {
			cadastroConsulta.salvar(dna, Especie.HUMANO.getValue());
			throw new NaoESimioException("O DNA informado não pertence a um Símio");
		}
		
	}
	
	private boolean isSimian (String[] dna) {
		
		String[][] dnaMatriz = converterParaMatriz(dna);
		
		if(verificaSequenciaHorizontal(dnaMatriz)) {
			return true;
		}
		
		if(verificaSequenciaVertical(dnaMatriz)) {
			return true;
		}
		
		if(verificaSequenciaDiagonal(dnaMatriz)) {
			return true;
		}
		
		return false;
		
	}
	
	private boolean verificaSequenciaHorizontal(String[][] dna) {

		int contador = 1;

		for (int i = 0; i < dna.length; i++) {

			contador = 1;

			for (int j = 1; j < dna.length; j++) {

				int lin = i;
				int col = j;

				if (dna[lin][col].equals(dna[lin][col - 1])) {
					contador++;
				} else {
					contador = 1;
				}

				if (contador == 4) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean verificaSequenciaVertical(String[][] dna) {

		int contador = 1;

		for (int i = 0; i < dna.length; i++) {

			contador = 1;

			for (int j = 1; j < dna.length; j++) {

				int lin = j;
				int col = i;

				if (dna[lin][col].equals(dna[lin - 1][col])) {
					contador++;
				} else {
					contador = 1;
				}

				if (contador == 4) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean verificaSequenciaDiagonal(String[][] dna) {

		int contador = 1;
		String valorDaDiagonal = "";

		for (int i = 0; i < dna.length * 2; i++) {
			
			for (int j = 0; j <= i; j++) {

				int k = i - j;

				if (k < dna.length && j < dna.length) {

					if (contador == 4) {
						return true;
					}

					if (valorDaDiagonal.equals(dna[k][j])) {
						contador++;
					} else {
						contador = 1;
					}

					valorDaDiagonal = dna[k][j];

				}
			}
		}
		return false;
	}

	private String[][] converterParaMatriz(String[] dnaArray) {

		String[][] dnaMatriz = new String[dnaArray.length][dnaArray.length];
		
		int totLinhas = dnaMatriz.length;
		
		for (int i = 0; i < dnaArray.length; i++) {
			for (int j = 0; j < dnaArray[i].length(); j++) {
				
				if(j > totLinhas - 1) {
					throw new SequenciaDNAInvalidaException("A sequência do DNA informado possui um tamanho inválido.");
				}
				
				dnaMatriz[i][j] = String.valueOf(dnaArray[i].charAt(j));
				
			}
		}
		return dnaMatriz;
	}

}
