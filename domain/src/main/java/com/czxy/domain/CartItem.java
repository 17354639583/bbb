package com.czxy.domain;

public class CartItem {
    private Product product;
    private Integer num;

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", num=" + num +
                '}';
    }

    public CartItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public CartItem(Product product, Integer num) {
        this.product = product;
        this.num = num;
    }
}
