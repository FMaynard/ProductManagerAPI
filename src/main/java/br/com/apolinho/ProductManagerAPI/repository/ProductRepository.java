package br.com.apolinho.ProductManagerAPI.repository;

import br.com.apolinho.ProductManagerAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
