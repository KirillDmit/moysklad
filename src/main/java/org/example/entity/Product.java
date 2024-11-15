package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Product name is required")
    @Size(max = 255, message = "Product name should be less than 255 characters")
    @Column(nullable = false, length = 255)
    private String name;

    @Size(max = 4096, message = "Description should be less than 4096 characters")
    @Column(length = 4096)
    private String description;

    @Min(value = 0, message = "Price should be greater than or equal to 0")
    @Column(nullable = false)
    private double price = 0.0;

    @Column(nullable = false)
    private boolean inStock = false;

    @PositiveOrZero
    @Column(nullable = false)
    private int stockQuantity;
}

