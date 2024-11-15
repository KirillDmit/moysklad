package org.example.repository;

import org.example.entity.ProductSale;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
    
}
