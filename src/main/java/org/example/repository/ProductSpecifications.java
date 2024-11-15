package org.example.repository;

import org.example.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> nameContains(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> priceGreaterThanOrEqualTo(Double price) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessThanOrEqualTo(Double price) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> isInStock(Boolean inStock) {
        return (root, query, cb) -> cb.equal(root.get("inStock"), inStock);
    }
}

