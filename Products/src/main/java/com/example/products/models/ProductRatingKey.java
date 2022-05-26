package com.example.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable//nhung vao empty ma no su dung
public class ProductRatingKey implements Serializable {
    @Column(name = "id_product")
    private Long id_product;

    @Column(name = "id_bill")
    private Long id_bill;
}
