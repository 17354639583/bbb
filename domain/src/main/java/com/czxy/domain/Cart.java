package com.czxy.domain;

import java.util.HashMap;
import java.util.Map;


public class Cart {
    private Map<Integer,CartItem>map=new HashMap<>();


    public void addCart(Product product,int num){
        CartItem cartItem = map.get(product.getId());
        if (cartItem==null){
            cartItem=new CartItem(product,num);
            map.put(product.getId(),cartItem);
        }else {
            cartItem.setNum(cartItem.getNum()+num);
        }
    }
    public void delCart(Product product,int num){
        CartItem cartItem = map.get(product.getId());
        if (cartItem.getNum()==1){
            map.remove(product.getId(),cartItem);
        }else {
            cartItem.setNum(cartItem.getNum()-num);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "map=" + map +
                '}';
    }

    public Map<Integer, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<Integer, CartItem> map) {
        this.map = map;
    }

    public Cart(Map<Integer, CartItem> map) {
        this.map = map;
    }

    public Cart() {
    }


}
