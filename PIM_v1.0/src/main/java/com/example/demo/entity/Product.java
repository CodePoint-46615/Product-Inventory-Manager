package com.example.demo.entity;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;


public class Product {
    private int id;
    private ProductCategory productCategory;
    private String productName;
    private LocalDate expDate;
    private Double price;

    public Product(){}

    public Product(int id, ProductCategory productCategory, String productName, LocalDate expDate, Double price){
        this.id = id;
        this.productCategory = productCategory;
        this.productName = productName;
        this.expDate = expDate;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setProductCategory(ProductCategory productCategory){
        this.productCategory = productCategory;
    }
    public ProductCategory getProductCategory(){
        return productCategory;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }
    public String getProductName(){
        return productName;
    }

    public void setExpDate(LocalDate expDate){
        this.expDate = expDate;
    }
    public LocalDate getExpDate(){
        return expDate;
    }

    public void setPrice(Double price){
        this.price = price;
    }
    public Double getPrice(){
        return price;
    }
}
