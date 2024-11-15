package org.example.controller;

import org.example.entity.ProductSale;
import org.example.service.ProductSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/sales")
public class ProductSaleController {

    private final ProductSaleService productSaleService;

    @Autowired
    public ProductSaleController(ProductSaleService productSaleService) {
        this.productSaleService = productSaleService;
    }

    @PostMapping
    public ResponseEntity<ProductSale> createSale(@RequestParam String documentName, @RequestParam Long productId, @RequestParam int quantity, @RequestParam BigDecimal purchasePrice) {
        ProductSale sale = productSaleService.createSale(documentName, productId, quantity, purchasePrice);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }
}

