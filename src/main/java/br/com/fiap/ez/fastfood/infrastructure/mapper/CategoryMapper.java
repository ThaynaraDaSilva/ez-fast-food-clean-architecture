package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CategoryEntity;

public class CategoryMapper {


    public static Category entityToDomain(CategoryEntity categoryEntity) {
        return new Category(
            categoryEntity.getId(),
            categoryEntity.getName()
        );
    }


    public static CategoryEntity domainToEntity(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        return entity;
    }
}
