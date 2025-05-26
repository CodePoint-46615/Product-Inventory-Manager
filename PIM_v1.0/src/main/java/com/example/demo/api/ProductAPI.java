package com.example.demo.api;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductAPI {

    private ProductService productService;

    public ProductAPI(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @PostMapping("/products")
    public void saveByOne(@RequestBody Product product){
        productService.saveByOne(product);
    }

    @PatchMapping("/products/{id}")
    public void updateByOne(@PathVariable int id, @RequestBody Product product){
        product.setId(id);
        productService.UpdateProduct(product);
    }

    @GetMapping("/products/filter")
    public List<Product> Filter(){
        return productService.Filter();
    }

    @GetMapping("/products/discount")
    public List<Product> Discount(){
        return productService.Discount();
    }

    @GetMapping("/products/{productCategory}")
    public List<Product> getProductByCategory(@PathVariable ProductCategory productCategory){
        return productService.getProductByCategory(productCategory);
    }
}
