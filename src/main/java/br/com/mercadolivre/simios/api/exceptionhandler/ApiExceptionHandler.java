package br.com.mercadolivre.simios.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mercadolivre.simios.domain.exception.NaoESimioException;
import br.com.mercadolivre.simios.domain.exception.SequenciaDNAInvalidaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NaoESimioException.class)
	public ResponseEntity<?> handleNaoESimio(NaoESimioException e, WebRequest req) {
		
		HttpStatus status = HttpStatus.FORBIDDEN;
		Problem problem = createProblemBuilder(status, ProblemType.NAO_E_SIMIO, e.getMessage()).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.FORBIDDEN, req);
	}
	
	@ExceptionHandler(SequenciaDNAInvalidaException.class)
	public ResponseEntity<?> handleSequenciaDNAInvalida(SequenciaDNAInvalidaException e, WebRequest req) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problem problem = createProblemBuilder(status, ProblemType.SEQUENCIA_DNA_INVALIDA, e.getMessage()).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
			if (body == null) {
				body = Problem.builder()
						.title(status.getReasonPhrase())
						.status(status.value())
						.build();
			} else if (body instanceof String) {
				body = Problem.builder()
						.title((String) body)
						.status(status.value())
						.build();
			}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder()
				.status(status.value())
				.title(problemType.getTitle())
				.detail(detail)
				.timestamp(LocalDateTime.now());
	}
}
