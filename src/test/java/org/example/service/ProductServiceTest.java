package org.example.service;

import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void testGetFilteredProducts_byNameAndPrice() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setInStock(true);
        List<Product> mockProducts = List.of(product);

        List<Product> result = productService.getFilteredProducts("Test", 50.0, 1000.0, null, "price", 10);

        assertNotNull(result);
        assertEquals(10, result.size());
        assertEquals("Test Product", result.get(0).getName());
    }
}
