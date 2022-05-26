package com.example.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String title;
    private String shortDescription;
    private String longDescription;
    private float price;
    private String slug;
    private String brand;
    private Integer status;
    private String type;
    private String gender;
}
