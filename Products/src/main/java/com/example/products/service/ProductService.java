package com.example.products.service;

import com.example.products.dtos.ProductDto;
import com.example.products.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<Product> showListProduct();
    public Product createNewProduct(ProductDto productDto);
    public Product updateProduct(ProductDto productDto, Long idProduct);
    public Product removeProduct(Long idProduct);
    public Product searchProductById(Long idProduct);

}
