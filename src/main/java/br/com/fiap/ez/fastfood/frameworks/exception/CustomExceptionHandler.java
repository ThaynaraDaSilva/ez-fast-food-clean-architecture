package br.com.fiap.ez.fastfood.frameworks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityNotFoundException;

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

	/*
	 * @ExceptionHandler(BusinessException.class) public
	 * ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
	 * String message = ex.getMessage(); HttpStatus status = HttpStatus.CONFLICT;
	 * 
	 * if (message.contains("Cliente não encontrado")) { status = HttpStatus.OK; }
	 * else if (message.contains("Dados inválidos")) { status =
	 * HttpStatus.BAD_REQUEST; }else if(message.contains("Cliente já cadastrado")) {
	 * status = HttpStatus.CONFLICT; }else
	 * if(message.contains("CPF ou senha errada.")){ status =
	 * HttpStatus.UNAUTHORIZED; }else if(message.contains("Lista de pedidos vazia"))
	 * { status = HttpStatus.BAD_REQUEST; }else if (message.
	 * contains("Produto não pode ser excluído,pois já faz parte de pedidos.")) {
	 * status = HttpStatus.CONFLICT; } else if(message.
	 * contains("Não há pedidos com status 'Pronto', 'Em preparação' ou 'Recebido'"
	 * )) { status = HttpStatus.OK; }
	 * 
	 * else { message = "Erro desconhecido"; status =
	 * HttpStatus.INTERNAL_SERVER_ERROR; // condicao padrao }
	 * 
	 * ErrorResponse errorResponse = new ErrorResponse(message); return new
	 * ResponseEntity<>(errorResponse, status); }
	 */

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Map<String, String>> handleBusinessException(BusinessException ex) {
		String message = ex.getMessage();
		HttpStatus status = mapBusinessExceptionToStatus(message); // Your existing method to map status

		Map<String, String> response = new HashMap<>();
		response.put("message", message);

		return new ResponseEntity<>(response, status);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
	}
	
	private HttpStatus mapBusinessExceptionToStatus(String message) {
        // Define specific mappings from message content to status codes
        Map<String, HttpStatus> statusMapping = new HashMap<>();
        
        //customer
        statusMapping.put("Cliente não encontrado", HttpStatus.OK);
        statusMapping.put("Dados inválidos", HttpStatus.BAD_REQUEST);
        statusMapping.put("Cliente já cadastrado", HttpStatus.CONFLICT);
        statusMapping.put("CPF ou senha errada.", HttpStatus.UNAUTHORIZED);
        statusMapping.put("Não há clientes cadastrados.", HttpStatus.OK);
        
        
        //order
        statusMapping.put("Lista de pedidos vazia.", HttpStatus.OK);
        statusMapping.put("Não há pedidos com status 'Pronto', 'Em preparação' ou 'Recebido'", HttpStatus.OK);
        statusMapping.put("Pedido não pode ser alterado, uma vez que o pagamento ainda não foi confirmado.",HttpStatus.OK);
        statusMapping.put("Pedido não pode ser atualizado, uma vez que está cancelado por falta de pagamento.",HttpStatus.OK);
        
        //product
        statusMapping.put("Produto não pode ser excluído, pois já faz parte de pedidos.", HttpStatus.OK);
        statusMapping.put("Produto escolhido não foi encontrado.", HttpStatus.OK);
        statusMapping.put("Produto não foi encontrado.", HttpStatus.OK);
        statusMapping.put("Esse produto não pode ser excluído,uma vez que já há pedido(s).", HttpStatus.OK);
        statusMapping.put("Não há produtos cadastrados com esta categoria.", HttpStatus.OK);
       
        //Category
        statusMapping.put("Categoria escolhida não existe.", HttpStatus.OK);
        

        // Return the mapped status or INTERNAL_SERVER_ERROR if no match is found
        return statusMapping.getOrDefault(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
