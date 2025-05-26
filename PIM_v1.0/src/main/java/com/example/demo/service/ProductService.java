package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.JstlUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.getAll();
    }
    public void saveByOne(Product product){
        productRepository.insertProductOne(product);
    }

    public void UpdateProduct(Product product){
        productRepository.UpdateProduct(product);
    }

    public List<Product> getProductByCategory(ProductCategory productCategory){
        return productRepository.getProductByCategory(productCategory);
    }

    //Business Logic:
    public List<Product> Filter(){
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysLater = today.plusDays(7);

        return productRepository.getAll()
                .stream()
                .filter(product -> product.getExpDate() != null)
                .filter(product -> !product.getExpDate().isBefore(today))
                .filter(product -> !product.getExpDate().isAfter(sevenDaysLater))
                .collect(Collectors.toList());
    }

    public List<Product> Discount(){
        double discountRate = 0.20;

        return Filter()
                .stream()
                .map(product -> {
                    Product discount = new Product();
                    discount.setId(product.getId());
                    discount.setProductCategory(product.getProductCategory());
                    discount.setProductName(product.getProductName());
                    discount.setExpDate(product.getExpDate());
                    discount.setPrice(product.getPrice()*(1-discountRate));
                    return discount;
                })
                .collect(Collectors.toList());
    }

}
