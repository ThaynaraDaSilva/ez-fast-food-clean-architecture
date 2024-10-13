package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CustomerEntity;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;
import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CustomerMapper;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;
import br.com.fiap.ez.fastfood.infrastructure.mapper.ProductMapper;

public class ProductRepositoryImpl implements ProductRepository {

	private final JpaProductRepository productJpaRepository;

	public ProductRepositoryImpl(JpaProductRepository productJpaRepository) {
		this.productJpaRepository = productJpaRepository;
	}

	@Override
	public Product save(Product product) {
		ProductEntity productEntity = ProductMapper.domainToEntity(product);
		if (product.getId() != null) {
			productEntity.setId(product.getId());
		}
		ProductEntity savedEntity = productJpaRepository.save(productEntity);
		product.setId(savedEntity.getId());
		return product;
	}

	@Override
	public Product update(Product product) {
		ProductEntity productEntity = ProductMapper.domainToEntity(product);
		if (product.getId() != null) {
			productEntity.setId(product.getId());
		}
		ProductEntity updatedEntity = productJpaRepository.save(productEntity);
		product.setId(updatedEntity.getId());
		return product;
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productJpaRepository.findById(id).map(ProductMapper::entityToDomain);

	}

	@Override
	public List<Product> findAll() {
		return productJpaRepository.findAll().stream().map(ProductMapper::entityToDomain).collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long id) {
		productJpaRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return productJpaRepository.existsById(id);
	}

	@Override
	public Product findProductById(Long id) {
		return ProductMapper.entityToDomain(productJpaRepository.findProductById(id));

	}

	@Override
	public List<Product> findProductByCategoryId(Long id) {
		return productJpaRepository.findProductByCategoryId(id).stream().map(ProductMapper::entityToDomain)
				.collect(Collectors.toList());
	}

	@Override
	public boolean isProductAssociatedWithOrderItems(Long id) {
		return productJpaRepository.isProductAssociatedWithOrderItems(id);
	}

}
