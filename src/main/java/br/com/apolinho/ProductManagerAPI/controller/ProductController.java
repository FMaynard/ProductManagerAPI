package br.com.apolinho.ProductManagerAPI.controller;

import br.com.apolinho.ProductManagerAPI.model.Product;
import br.com.apolinho.ProductManagerAPI.service.ProductService;
import br.com.apolinho.ProductManagerAPI.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private S3Service s3Service;

    @PostMapping
    public Product createProduct(@RequestBody Product product) throws IOException {
        Product createdProduct = productService.saveProduct(product);
        s3Service.uploadProductData(createdProduct);
        return createdProduct;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) throws IOException {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        s3Service.uploadProductData(updatedProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
