package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.PRODUCT WHERE id = :id")
	ProductEntity findProductById(@Param("id") Long id);

	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.PRODUCT WHERE category_id = :id")
	List<ProductEntity> findProductByCategoryId(@Param("id") Long id);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 " + "FROM EZ_FASTFOOD.ORDER_ITEMS oi "
			+ "JOIN EZ_FASTFOOD.PRODUCT p ON oi.product_id = p.id " + "WHERE p.id = :id")
	boolean isProductAssociatedWithOrderItems(@Param("id") Long productId);

}
