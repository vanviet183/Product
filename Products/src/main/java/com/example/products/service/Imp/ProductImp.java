package com.example.products.service.Imp;

import com.example.products.dtos.ProductDto;
import com.example.products.exception.NotFoundException;
import com.example.products.models.Product;
import com.example.products.repository.ProductRepository;
import com.example.products.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImp implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductImp(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<Product> showListProduct() {
        List<Product> listPr = productRepository.findAll();
        return listPr;
    }

    @Override
    public Product createNewProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(ProductDto productDto, Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isEmpty()) {
            throw new NotFoundException("Id product not exists");
        }
        modelMapper.map(productDto, product.get());
        return product.get();
    }

    @Override
    public Product removeProduct(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isEmpty()) {
            throw new NotFoundException("Id product not exists");
        }
        productRepository.deleteById(idProduct);
        return product.get();
    }

    @Override
    public Product searchProductById(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isEmpty()) {
            throw new NotFoundException("Id product not exists");
        }
        return product.get();
    }
}
