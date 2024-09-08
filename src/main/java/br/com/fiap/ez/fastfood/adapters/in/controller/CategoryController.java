package br.com.fiap.ez.fastfood.adapters.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.ez.fastfood.application.ports.in.CategoryService;
import br.com.fiap.ez.fastfood.domain.model.Category;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/categories")
@Tag(name = "Category Operations", description = "Operations related to category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

	@Operation(summary = "List all categories")
	@GetMapping(path = "/list-all", produces = "application/json")
    public ResponseEntity<List<Category>> listCategories() {
        List<Category> categories = categoryService.listCategories();
        return ResponseEntity.ok(categories);
    }
	
	@Hidden
	@Operation(summary = "Create a new Category")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categoria criada"),
	@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping(path = "/create-new", produces = "application/json")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

	@Hidden
	@Operation(summary = "Find Category by ID")
	@GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

	@Hidden
	@Operation(summary = "Remove Category by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categoria removida"),
	@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
	
	@Hidden
	@Operation(summary = "Modify Category by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categoria alterada"),
	@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

}
