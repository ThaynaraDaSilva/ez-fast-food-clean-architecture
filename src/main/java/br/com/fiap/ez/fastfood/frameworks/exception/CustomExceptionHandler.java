package br.com.fiap.ez.fastfood.frameworks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuilder errors = new StringBuilder();
		ex.getBindingResult().getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
		ErrorResponse errorResponse = new ErrorResponse(errors.toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ErrorResponse> handleIllegalArgumentExceptions(IllegalArgumentException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	 @ExceptionHandler(BusinessException.class)
	    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
	        String message = ex.getMessage();
	        HttpStatus status = HttpStatus.CONFLICT;

	        if (message.contains("Cliente não encontrado")) {
	            status = HttpStatus.NOT_FOUND;
	        } else if (message.contains("Dados inválidos")) {
	            status = HttpStatus.BAD_REQUEST;
	        }else if(message.contains("Cliente já cadastrado")) {
	        	status = HttpStatus.CONFLICT;
	        }else if(message.contains("CPF ou senha errada.")){
	        	status = HttpStatus.UNAUTHORIZED;
	        }else if(message.contains("Lista de pedidos vazia")) {
	        	status = HttpStatus.BAD_REQUEST;
	        }
	        
	        else { 
	        	message = "Erro desconhecido";
	        	status = HttpStatus.INTERNAL_SERVER_ERROR; // condicao padrao 
	        }
			 
	        ErrorResponse errorResponse = new ErrorResponse(message);
	        return new ResponseEntity<>(errorResponse, status);
	    }
}
