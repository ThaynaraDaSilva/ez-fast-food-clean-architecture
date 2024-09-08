package br.com.fiap.ez.fastfood.application.serviceremove;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ez.fastfood.application.dto.CreateProductDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductResponseDTO;
import br.com.fiap.ez.fastfood.application.ports.in.ProductService;
import br.com.fiap.ez.fastfood.application.ports.out.CategoryRepository;
import br.com.fiap.ez.fastfood.application.ports.out.ProductRepository;
import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductResponseDTO createProduct(CreateProductDTO createProductDTO) {
		Product product = new Product();
		product.setName(createProductDTO.getName());
		product.setDescription(createProductDTO.getDescription());
		product.setPrice(createProductDTO.getPrice());

		Category category = categoryRepository.findById(createProductDTO.getCategoryId())
				.orElseThrow(() -> new BusinessException("Categoria não encontrada"));
		product.setCategory(category);
		
		Product savedProduct = productRepository.save(product);
		return convertToDTO(savedProduct);
	}

	@Override
	public List<Product> listProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Long id) {

		Product product = productRepository.findById(id);

		if (product != null) {
			return product;
		} else {
			throw new BusinessException("Produto não encontrado");
		}

	}

	@Override
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id);

		if (product != null) {
			productRepository.deleteById(id);
		} else {
			throw new BusinessException("Produto não encontrado");
		}
	}

	@Override
	public ProductResponseDTO updateProduct(Long id, CreateProductDTO createProductDTO) {
		Product existingProduct = productRepository.findById(id);
		if (existingProduct != null) {
			existingProduct.setName(createProductDTO.getName());
			existingProduct.setDescription(createProductDTO.getDescription());
			existingProduct.setPrice(createProductDTO.getPrice());

			// Atualizar a categoria apenas com o ID
			Category category = categoryRepository.findById(createProductDTO.getCategoryId())
					.orElseThrow(() -> new BusinessException("Categoria não encontrada"));
			existingProduct.setCategory(category);
			
			Product updatedProduct = productRepository.save(existingProduct);

			return convertToDTO(updatedProduct);
		} else {
			throw new BusinessException("Produto não encontrado");
		}

	}

	@Override
	public List<Product> findProductsByCategoryName(String categoryName) {
		categoryName = categoryName.toUpperCase();
		Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
		if (optionalCategory.isEmpty()) {
			return null;
		}

		Category category = optionalCategory.get();
		List<Product> products = productRepository.findByCategoryId(category.getId());
		return products.isEmpty() ? null : products;
	}
	
	public ProductResponseDTO convertToDTO(Product product) {
	    ProductResponseDTO dto = new ProductResponseDTO();
	    dto.setId(product.getId());
	    dto.setName(product.getName());
	    dto.setPrice(product.getPrice());
	    dto.setDescription(product.getDescription());
	    dto.setCategoryId(product.getCategory().getId());
	    return dto;
	}

	
	

}
