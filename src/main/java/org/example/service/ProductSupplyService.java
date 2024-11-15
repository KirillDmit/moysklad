package org.example.service;

import org.example.entity.Product;
import org.example.entity.ProductSupply;
import org.example.exception.ProductNotFoundException;
import org.example.repository.ProductRepository;
import org.example.repository.ProductSupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSupplyService {

    private final ProductSupplyRepository productSupplyRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductSupplyService(ProductSupplyRepository productSupplyRepository, ProductRepository productRepository) {
        this.productSupplyRepository = productSupplyRepository;
        this.productRepository = productRepository;
    }

    public ProductSupply createSupply(String documentName, Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
        ProductSupply supply = new ProductSupply();
        supply.setDocumentName(documentName);
        supply.setProduct(product);
        supply.setQuantity(quantity);
        product.setStockQuantity(product.getStockQuantity() + quantity); // обновление количества товара
        productRepository.save(product);
        return productSupplyRepository.save(supply);
    }
}
