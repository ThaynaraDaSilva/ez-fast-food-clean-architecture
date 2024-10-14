package br.com.fiap.ez.fastfood.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.domain.repository.CategoryRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
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
        	product.setCategory(category);
        }else {
        	throw new BusinessException("Categoria escolhida não existe.");
        }
        product = productRepository.save(product);
        return ProductMapper.domainToResponseDto(product);
    }
    
    public ProductResponseDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produto escolhido não foi encontrado."));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        Category category = categoryRepository.findById(productDTO.getCategoryId());
       
        if(category !=null) {
        	product.setCategory(category);
        }else {
        	throw new BusinessException("Categoria não existe");
        }
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return new ProductResponseDTO(updatedProduct.getId(), updatedProduct.getName(),
                updatedProduct.getDescription(), updatedProduct.getCategory().getName(), updatedProduct.getPrice());
    }


    public ProductResponseDTO findById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::domainToResponseDto)
                .orElseThrow(() -> new BusinessException("Produto não foi encontrado."));
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::domainToResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (productRepository.existsById(id)) {
        	if(productRepository.isProductAssociatedWithOrderItems(id)) {
        		throw new BusinessException("Esse produto não pode ser excluído,uma vez que já há pedido(s).");
        	}else {
        		productRepository.deleteById(id);
        	}
        } else {
            throw new EntityNotFoundException("Produto escolhido não foi encontrado.");
        }
    }
    
    public List<ProductResponseDTO> findProductByCategoryId(Long id){
    	List <Product> products =  productRepository.findProductByCategoryId(id);
    	
    	if(products.isEmpty()) {
    		throw new BusinessException("Não há produtos cadastrados com esta categoria.");
    	}
    	return products.stream().map(ProductMapper::domainToResponseDto).collect(Collectors.toList());
    }

}