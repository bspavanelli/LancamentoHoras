package br.com.bspavanelli.lancamento_horas.services.exceptions;

public class StartTimeEqualsToEndTimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StartTimeEqualsToEndTimeException(String msg) {
		super(msg);
	}

	public StartTimeEqualsToEndTimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
