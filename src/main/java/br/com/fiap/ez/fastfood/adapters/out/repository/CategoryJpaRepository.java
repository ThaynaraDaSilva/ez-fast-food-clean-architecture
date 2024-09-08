package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.domain.model.Category;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(String name);
}
