package br.com.fiap.ez.fastfood.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.domain.repository.CategoryRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;
import br.com.fiap.ez.fastfood.infrastructure.mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;

public class ProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductUseCase(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository  = categoryRepository;
    }

    public ProductResponseDTO save(ProductDTO productDTO) {
    	
        Product product = ProductMapper.toDomain(productDTO);
        Category category = categoryRepository.findById(productDTO.getCategoryId());
        if(category !=null) {
        	System.out.println("ENTREI NO IF DE PRODUCT CASE. VALOR DO CATEGORY: " + category.getName());
        	product.setCategory(category);
        }
        product = productRepository.save(product);
        return ProductMapper.domainToResponseDto(product);
    }
    
    public ProductResponseDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id " + id));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        Category category = categoryRepository.findById(productDTO.getCategoryId());
        if(category !=null) {
        	System.out.println("ENTREI NO IF DE PRODUCT CASE. VALOR DO CATEGORY: " + category.getName());
        }
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return new ProductResponseDTO(updatedProduct.getId(), updatedProduct.getName(),
                updatedProduct.getDescription(), updatedProduct.getPrice());
    }


    public ProductResponseDTO findById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::domainToResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id " + id));
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::domainToResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Produto não encontrado com id " + id);
        }
    }
    
    public List<ProductResponseDTO> findProductByCategoryId(Long id){
    	List <Product> products =  productRepository.findProductByCategoryId(id);
    	return products.stream().map(ProductMapper::domainToResponseDto).collect(Collectors.toList());
    }

}