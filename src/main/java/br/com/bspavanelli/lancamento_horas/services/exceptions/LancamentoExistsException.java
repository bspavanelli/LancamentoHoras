package br.com.bspavanelli.lancamento_horas.services.exceptions;

public class LancamentoExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LancamentoExistsException(String msg) {
		super(msg);
	}

	public LancamentoExistsException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
