package br.com.fiap.ez.fastfood.domain.repository;

import br.com.fiap.ez.fastfood.domain.model.Category;

public interface CategoryRepository {
	
	 Category findById(Long id);
}
