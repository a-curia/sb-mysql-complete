package com.dbbyte.model;


import javax.persistence.*;

@Entity
public class Product{
    private int id;
    private String name;
    private ProductCategory productCategory;

    public Product() {

    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, ProductCategory productCategory) {
        this.name = name;
        this.productCategory = productCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}