package com.example.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "productsSize")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_product_size;

    @Column(name = "value")
    private Integer value;

    @Column(name = "currentNumber")
    private Integer currentNumber;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

}
