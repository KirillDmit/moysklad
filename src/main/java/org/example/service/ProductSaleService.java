package org.example.service;

import org.example.entity.Product;
import org.example.entity.ProductSale;
import org.example.exception.ProductNotFoundException;
import org.example.repository.ProductRepository;
import org.example.repository.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductSaleService {

    private final ProductSaleRepository productSaleRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductSaleService(ProductSaleRepository productSaleRepository, ProductRepository productRepository) {
        this.productSaleRepository = productSaleRepository;
        this.productRepository = productRepository;
    }

    public ProductSale createSale(String documentName, Long productId, int quantity, BigDecimal purchasePrice) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
        if (product.getStockQuantity() < quantity) throw new IllegalArgumentException("Not enough stock to sell");
        ProductSale sale = new ProductSale();
        sale.setDocumentName(documentName);
        sale.setProduct(product);
        sale.setQuantity(quantity);
        sale.setPurchasePrice(purchasePrice);
        product.setStockQuantity(product.getStockQuantity() - quantity); // уменьшение количества товара
        productRepository.save(product);
        return productSaleRepository.save(sale);
    }
}

