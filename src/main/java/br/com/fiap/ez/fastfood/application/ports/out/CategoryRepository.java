package br.com.fiap.ez.fastfood.application.ports.out;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.domain.model.Category;

@Repository
public interface CategoryRepository {
	Category save(Category category);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void deleteById(Long id);
    Optional<Category> findByName(String name);
}
