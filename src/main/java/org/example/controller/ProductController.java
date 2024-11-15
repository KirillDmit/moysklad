package org.example.controller;

import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<Product>> getFilteredProducts(
            @RequestParam(required = false) @Size(max = 50) String name,
            @RequestParam(required = false) @PositiveOrZero Double minPrice,
            @RequestParam(required = false) @Positive Double maxPrice,
            @RequestParam(required = false) Boolean inStock,
            @RequestParam(required = false) @Pattern(regexp = "name|price") String sortBy,
            @RequestParam(required = false, defaultValue = "10") @Max(100) Integer limit) {

        List<Product> products = productService.getFilteredProducts(name, minPrice, maxPrice, inStock, sortBy, limit);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
