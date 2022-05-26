package com.example.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ProductsRate")
public class ProductRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductRate;

    @Column(name = "value")
    private Integer values;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
}
