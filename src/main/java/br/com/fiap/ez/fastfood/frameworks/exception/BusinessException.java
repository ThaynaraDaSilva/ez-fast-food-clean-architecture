/*
 * package br.com.fiap.ez.fastfood.config.exception;
 * 
 * import org.springframework.http.HttpStatus;
 * 
 * public class BusinessException extends RuntimeException { private final
 * HttpStatus status;
 * 
 * public BusinessException(String message, HttpStatus status) { super(message);
 * this.status = status; }
 * 
 * public HttpStatus getStatus() { return status; } }
 */


package br.com.fiap.ez.fastfood.frameworks.exception;

public class BusinessException extends RuntimeException {
	//private final HttpStatus status;
	
	public BusinessException(String message) {
        super(message);
    }
}
