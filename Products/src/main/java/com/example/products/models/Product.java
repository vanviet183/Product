package com.example.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Product;

    @Column(name = "title")
    //@NotBlank(message = "Not empty")
    private String title;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "price")
    //@Min(0)
    private float price;

    @Column(name = "slug")
    private String slug;

    @Column(name = "brand")
   // @NotNull
    private String brand;

   /// @Min(0)
    @Column(name = "status")
    private Integer status;

    @Column(name = "type")
    private String type;

    @Column(name = "Gender")
    //@NotNull
    private String gender;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @OneToMany(mappedBy = "product" ,  cascade =  CascadeType.ALL)
    // whenever you do any operation on the parent all those operations would also get cascaded to the child.
    private List<ProductColor> productColorList;

    //Product Size
    @OneToMany(mappedBy = "product" ,  cascade =  CascadeType.ALL)
    private List<ProductSize> productSizes;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<ProductRate> productRates;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<Image> imageList;
    //Will get initialized only when we explicitly call it,
    // using a getter or some other method:
    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<Detail_Bill> detailBillList;

}
