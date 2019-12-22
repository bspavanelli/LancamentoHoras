package br.com.bspavanelli.lancamento_horas.services.exceptions;

public class StartTimeGreaterThanEndTimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StartTimeGreaterThanEndTimeException(String msg) {
		super(msg);
	}

	public StartTimeGreaterThanEndTimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
