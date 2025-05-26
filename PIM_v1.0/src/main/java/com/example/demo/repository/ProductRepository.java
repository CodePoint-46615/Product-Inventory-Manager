package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private static  final String GET_ALL = "SELECT * FROM products";
    private static final String INSERT_ONE = "INSERT INTO products(productCategory, productName, expDate, price) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_ONE = "UPDATE products SET productCategory=?, productName=?, expDate=?, price=? WHERE id=?";

    private static final String CATEGORY_FILTER = "SELECT * FROM products WHERE productCategory = ?";
    private JdbcTemplate jdbcTemplate;


    public ProductRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Product> getAll(){
        return jdbcTemplate.query(GET_ALL,
                (rs, rowNum) -> new Product(
                        rs.getInt("id"),
                        ProductCategory.valueOf(rs.getString("productCategory")),
                        rs.getString("productName"),
                        rs.getDate("expDate").toLocalDate(),
                        rs.getDouble("price")
                )
        );
    }

    public void insertProductOne(Product product){
        jdbcTemplate.update(INSERT_ONE,
                product.getProductCategory().toString(),
                product.getProductName(),
                product.getExpDate(),
                product.getPrice()
        );
    }

    public void UpdateProduct(Product product){
        jdbcTemplate.update(UPDATE_ONE,
                product.getProductCategory().toString(),
                product.getProductName(),
                product.getExpDate(),
                product.getPrice(),
                product.getId()
        );
    }

    public List<Product> getProductByCategory(ProductCategory productCategory) {
        return jdbcTemplate.query(
                CATEGORY_FILTER,
                new Object[] {productCategory.name()},  // Use name() instead of toString()
                (rs, rowNum) -> new Product(
                        rs.getInt("id"),
                        productCategory,  // Use the parameter since we already know the category
                        rs.getString("productName"),
                        rs.getDate("expDate").toLocalDate(),
                        rs.getDouble("price")
                )
        );
    }
}
