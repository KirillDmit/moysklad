package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Product {

    private Long id;

    @NotEmpty(message = "Product name is required")
    @Size(max = 255, message = "Product name should be less than 255 characters")
    private String name;

    @Size(max = 4096, message = "Description should be less than 4096 characters")
    private String description;

    @Min(value = 0, message = "Price should be greater than or equal to 0")
    private double price = 0.0;

    private boolean inStock = false;
}

