package org.example.controller;

import org.example.entity.ProductSupply;
import org.example.service.ProductSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplies")
public class ProductSupplyController {

    private final ProductSupplyService productSupplyService;

    @Autowired
    public ProductSupplyController(ProductSupplyService productSupplyService) {
        this.productSupplyService = productSupplyService;
    }

    @PostMapping
    public ResponseEntity<ProductSupply> createSupply(@RequestParam String documentName, @RequestParam Long productId, @RequestParam int quantity) {
        ProductSupply supply = productSupplyService.createSupply(documentName, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(supply);
    }
}

