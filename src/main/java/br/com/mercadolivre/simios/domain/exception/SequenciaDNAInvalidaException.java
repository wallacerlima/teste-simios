package br.com.mercadolivre.simios.domain.exception;

public class SequenciaDNAInvalidaException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SequenciaDNAInvalidaException(String mensagem) {
		super(mensagem);
	}
	
}
