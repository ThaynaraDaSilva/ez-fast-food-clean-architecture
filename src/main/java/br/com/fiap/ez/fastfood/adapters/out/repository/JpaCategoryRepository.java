package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.ez.fastfood.infrastructure.persistence.CategoryEntity;


public interface JpaCategoryRepository  extends JpaRepository<CategoryEntity, Long>{

	
	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.CATEGORY WHERE id = :id")
	CategoryEntity findCategoryById(@Param("id") Long id);
}
