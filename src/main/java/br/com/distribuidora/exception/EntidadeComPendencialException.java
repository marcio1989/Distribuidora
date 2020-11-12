package br.com.distribuidora.exception;
/**
 * @marcio
 */
public class EntidadeComPendencialException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeComPendencialException(String mensagem) {
		super(mensagem);
	}
}
