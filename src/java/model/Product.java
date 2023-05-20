/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thắng đẹp trai
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private String img;
    private ProductGroup productGroup;
    private Brand brand;
    private List<Size> listSize;
    public Product() {
    }
    
    public Product(int id, String name, double price, String img, ProductGroup productGroup, Brand brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.productGroup = productGroup;
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    
    public List<Size> getListSize() {
        return listSize;
    }

    public void setListSize(List<Size> listSize) {
        this.listSize = new ArrayList<>();
        this.listSize = listSize;
    }
    
    
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }
    
    
}
