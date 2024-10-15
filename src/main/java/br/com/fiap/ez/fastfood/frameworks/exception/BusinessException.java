package br.com.fiap.ez.fastfood.frameworks.exception;

public class BusinessException extends RuntimeException {
	//private final HttpStatus status;
	
	public BusinessException(String message) {
        super(message);
    }
}
