package org.example.repository;

import org.example.entity.ProductSupply;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductSupplyRepository extends JpaRepository<ProductSupply, Long> {

}
