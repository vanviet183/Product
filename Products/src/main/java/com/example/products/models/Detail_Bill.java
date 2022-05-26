package com.example.products.models;

import javax.persistence.*;

@Entity
@Table(name = "Detail_Bill")
public class Detail_Bill {

    @EmbeddedId
    private ProductRatingKey id;

    @ManyToOne
    @MapsId("id_bill")
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "vat")
    private float vat;

}
