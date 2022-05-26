package com.example.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBill;

    @CreationTimestamp
    private Timestamp created;

    private float total;

    @OneToMany(mappedBy = "bill" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Detail_Bill> detailBillList;
}
