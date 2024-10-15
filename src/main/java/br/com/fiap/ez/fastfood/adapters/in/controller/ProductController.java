package br.com.fiap.ez.fastfood.adapters.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductResponseDTO;
import br.com.fiap.ez.fastfood.application.usecases.ProductUseCase;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Operations", description = "Operations related to products")
public class ProductController {

	@Autowired
	private final ProductUseCase productUseCase;

	public ProductController(ProductUseCase productUseCase) {
		this.productUseCase = productUseCase;
	}

	@Operation(summary = "Create a new product")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto criado"),
	@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping(path = "/create-new", produces = "application/json")
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductResponseDTO productResponseDTO = productUseCase.save(productDTO);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }



	@Operation(summary = "Modify Product by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto alterado"),
	@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PutMapping("update-by-id/{id}")
	public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
	    
	    return new ResponseEntity<>(productUseCase.updateProduct(id, productDTO), HttpStatus.OK);
	}

	@Operation(summary = "Remove Product by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto removido"),
	@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@DeleteMapping("delete-by-id/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productUseCase.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@Operation(summary = "List all products")
	@GetMapping(path = "/list-all", produces = "application/json")
	public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return new ResponseEntity<>(productUseCase.findAll(), HttpStatus.OK);
    }

	@Hidden
	@Operation(summary = "Find Product by ID")
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
  
        return new ResponseEntity<>(productUseCase.findById(id), HttpStatus.OK);
    }
	
	@Operation(summary = "Find Product by Category Id")
	@GetMapping(path = "/find-by-category-id/{id}", produces = "application/json")
	public ResponseEntity<?> getProductByCategoryId(@PathVariable Long id) {
	    
        return new ResponseEntity<>(productUseCase.findProductByCategoryId(id), HttpStatus.OK);
        
    }
}