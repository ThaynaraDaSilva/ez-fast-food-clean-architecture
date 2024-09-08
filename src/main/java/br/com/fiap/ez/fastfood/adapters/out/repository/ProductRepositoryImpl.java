package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.application.ports.out.ProductRepository;
import br.com.fiap.ez.fastfood.domain.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	private final ProductJpaRepository productJpaRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public ProductRepositoryImpl(ProductJpaRepository productJpaRepository) {
		this.productJpaRepository = productJpaRepository;
	}
	
	@Override
	public Product save(Product product) {
		return productJpaRepository.save(product);
	}

	@Override
	public List<Product> findAll() {
		return productJpaRepository.findAll();
	}

	/*
	 * @Override public Product findById(Long id) { return
	 * productJpaRepository.findById(id); }
	 */

    @Override
    public void deleteById(Long id) {
        productJpaRepository.deleteById(id);
    }
    
    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productJpaRepository.findByCategoryId(categoryId);
    }
    
    @Override
    public boolean existsByCategoryId(Long categoryId) {
        return productJpaRepository.existsByCategoryId(categoryId);
    }
    
    @Override
    public Optional<Product> findByName(String name) {
        return productJpaRepository.findByName(name);
    }

	@Override
	public Product findById(Long id) {
		return productJpaRepository.findProductById(id);
	}


}
