package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
