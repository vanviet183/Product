package com.example.products.controller;

import com.example.products.base.BaseController;
import com.example.products.dtos.ProductDto;
import com.example.products.models.Product;
import com.example.products.service.Imp.ProductImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController extends BaseController<Product> {
   

    @Autowired
     ProductImp productImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProduct(){
        return this.resListSuccess(productImp.showListProduct());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewProduct(@Valid @RequestBody ProductDto productDto){
        return this.resSuccess(productImp.createNewProduct(productDto));
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ProductDto productDto ,
                                 @PathVariable("id") Long id){
        return this.resSuccess(productImp.updateProduct(productDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        return this.resSuccess(productImp.removeProduct(id));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchProductById(@PathVariable("id") Long id){
        return this.resSuccess(productImp.searchProductById(id));
    }
}
