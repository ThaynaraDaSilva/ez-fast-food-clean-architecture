package br.com.fiap.ez.fastfood.application.ports.in;

import java.util.List;

import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.domain.model.Product;

public interface CategoryService {
	
	Category createCategory(Category category);
    List<Category> listCategories();
    Category findById(Long id);
    void deleteCategory(Long id);
    Category updateCategory(Long id, Category category);
    Category getCategoryByName(String name);
    List<Product> findByCategoryId(Long categoryId);
    boolean existsByCategoryId(Long categoryId);
}
