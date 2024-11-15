package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String documentName;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Positive
    @Column(nullable = false)
    private int quantity;

    @DecimalMin("0.01")
    @Column(nullable = false)
    private BigDecimal purchasePrice;
}
