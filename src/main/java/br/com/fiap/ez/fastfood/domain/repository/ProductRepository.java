package br.com.fiap.ez.fastfood.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.fiap.ez.fastfood.domain.model.Product;

public interface ProductRepository {
	
	Product save(Product product);
    Product update(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);

}
