/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thắng đẹp trai
 */
public class ProductCart {
    private Product product;
    private int quantity;
    private double price;
    private String size;
    public ProductCart() {
    }

    public ProductCart(Product product, int quantity, double price,String size) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.size = size;

    }
    

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    
    
    
}
