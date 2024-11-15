package org.example.service;

import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.repository.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getFilteredProducts(String name, Double minPrice, Double maxPrice, Boolean inStock, String sortBy, Integer limit) {

        Specification<Product> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and(ProductSpecifications.nameContains(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessThanOrEqualTo(maxPrice));
        }
        if (inStock != null) {
            spec = spec.and(ProductSpecifications.isInStock(inStock));
        }

        Sort sort = Sort.unsorted();
        if ("name".equalsIgnoreCase(sortBy)) {
            sort = Sort.by("name");
        } else if ("price".equalsIgnoreCase(sortBy)) {
            sort = Sort.by("price");
        }

        Pageable pageable = PageRequest.of(0, limit, sort);
        return productRepository.findAll(spec, pageable).getContent();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setInStock(productDetails.isInStock());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
