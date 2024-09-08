package br.com.fiap.ez.fastfood.application.ports.in;

import java.util.List;

import br.com.fiap.ez.fastfood.application.dto.CreateProductDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Product;

public interface ProductService {
	
	ProductResponseDTO createProduct(CreateProductDTO createProductDTO);
	List<Product> listProducts();
	Product findById(Long id);
	void deleteProduct(Long id);
	ProductResponseDTO updateProduct(Long id, CreateProductDTO createProductDTO);
    List<Product> findProductsByCategoryName(String categoryName);
}
