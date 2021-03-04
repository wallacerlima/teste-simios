package br.com.mercadolivre.simios.domain.exception;

public class NaoESimioException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NaoESimioException(String mensagem) {
		super(mensagem);
	}
	
}
