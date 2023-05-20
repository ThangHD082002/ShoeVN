/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<ProductCart> proCart;
    
    public Cart() {
        proCart = new ArrayList<>();
    }

    public List<ProductCart> getProCart() {
        return proCart;
    }

    public int getQuantityById(int id) {
        return getProductCartById(id).getQuantity();
    }

    public ProductCart getProductCartById(int id) {
        for (ProductCart p : proCart) {
            if (p.getProduct().getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public ProductCart getProductCartByIdSize(int id, String size){
        for (ProductCart p : proCart) {
            if (p.getProduct().getId() == id && p.getSize().equals(size)) {
                return p;
            }
        }
        return null;
    }
    
    public ProductCart getProductCartBySize(String size){
        for(ProductCart p : proCart){
            if(p.getSize().equals(size)){
                return p;
            }
        }
        return null;
    }

    public void addProducCart(ProductCart p) {
        if (getProductCartById(p.getProduct().getId()) != null && getProductCartBySize(p.getSize()) != null) {
            ProductCart m = getProductCartById(p.getProduct().getId());
            m.setQuantity(m.getQuantity() + p.getQuantity());
        } else {
            proCart.add(p);
        }
    }

    public void remove(int id) {
        if (getProductCartById(id) != null) {
            proCart.remove(getProductCartById(id));
        }
    }
    
    public void removeIdSize(int id, String size) {
        if (getProductCartByIdSize(id,size) != null) {
            ProductCart p = getProductCartByIdSize(id, size);
            proCart.remove(p);
        }
    }
    

    public double getTotalMoneyCart() {
        double t = 0;
        for (ProductCart p : proCart) {
            t += p.getQuantity() * p.getProduct().getPrice();
        }
        return t;
    }

    private Product getProductById(int id, List<Product> list) {
        for (Product i : list) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public Cart(String txt, List<Product> list) {
        proCart = new ArrayList<>();
        if (txt != null && txt.length() != 0) {

            String[] s = txt.split("a");
            for (String x : s) {
                String[] n = x.split(":");
                int id = Integer.parseInt(n[0]);
                int quantity = Integer.parseInt(n[1]);
                String size = n[2];
                Product p = getProductById(id, list);
                ProductCart pc = new ProductCart(p, quantity, p.getPrice(),size);
                addProducCart(pc);
            }

        }
    }
}
