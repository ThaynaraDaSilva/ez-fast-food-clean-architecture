package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.PRODUCT WHERE id = :id")
	ProductEntity findProductById(@Param("id") Long id);
}
