package br.com.fiap.ez.fastfood.application.ports.out;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.domain.model.Product;

@Repository
public interface ProductRepository {

	Product save(Product product);
	List<Product> findAll();
	//Optional<Product> findById(Long id);
	Product findById(Long id);
    void deleteById(Long id);
    List<Product> findByCategoryId(Long categoryId);
    boolean existsByCategoryId(Long categoryId);
    Optional<Product> findByName(String name);
}
