package br.com.fiap.ez.fastfood.application.usecases.product;

import java.util.List;
import java.util.Optional;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.ProductMapper;

public class ProductUseCase {

    private final br.com.fiap.ez.fastfood.domain.repository.ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toDomain(productDTO);

//        if (product.isValid()) {
            productRepository.save(product);
            return ProductMapper.domainToDto(product);
//        } else {
//            throw new IllegalArgumentException("Dados inválidos");
//        }
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(productDTO.getName());
            productToUpdate.setDescription(productDTO.getDescription());
            productToUpdate.setPrice(productDTO.getPrice());
            productRepository.save(productToUpdate);

            return ProductMapper.domainToDto(productToUpdate);
        } else {
            throw new BusinessException("Produto não encontrado");
        }
    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new BusinessException("Produto não encontrado");
        }
    }

    public ProductDTO findProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::domainToDto)
                .orElseThrow(() -> new BusinessException("Produto não encontrado"));
    }

    public List<ProductDTO> listProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::domainToDto).toList();
    }
}
