package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.domain.repository.CategoryRepository;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CategoryMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CategoryEntity;


public class CategoryRepositoryImpl  implements CategoryRepository{
	
	private final JpaCategoryRepository jpaCategoryRepository;

	

	public CategoryRepositoryImpl(JpaCategoryRepository jpaCategoryRepository) {
		super();
		this.jpaCategoryRepository = jpaCategoryRepository;
	}



	@Override
	public Category findById(Long id) {
		CategoryEntity entity = jpaCategoryRepository.findCategoryById(id);
        return CategoryMapper.entityToDomain(entity);
	}

	
}
