package com.czxy.controller;

import com.czxy.domain.Cart;
import com.czxy.domain.CartItem;
import com.czxy.domain.Product;
import com.czxy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("pro")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        try {
            List<Product> all = productService.findAll();
            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> add(@PathVariable("id") String id, HttpServletRequest httpServletRequest) {
        try {
            Product byId = productService.findById(id);
            Cart cart = (Cart) httpServletRequest.getSession().getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }
            cart.addCart(byId, 1);
            httpServletRequest.getSession().setAttribute("cart", cart);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/ck")
    public ResponseEntity<List<CartItem>> ck(HttpServletRequest httpServletRequest) {
        try {
            Cart cart1 = (Cart) httpServletRequest.getSession().getAttribute("cart");
            if (cart1 == null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            Map<Integer, CartItem> map = cart1.getMap();
            Set<Map.Entry<Integer, CartItem>> entries = map.entrySet();
            List<CartItem> cartItems = new ArrayList<>();
            for (Map.Entry<Integer, CartItem> entry : entries) {
                cartItems.add(entry.getValue());
            }
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ckk/{id}")
    public ResponseEntity<List<CartItem>> del(@PathVariable("id") String id, HttpServletRequest httpServletRequest) {
        try {
            Cart cart1 = (Cart) httpServletRequest.getSession().getAttribute("cart");
            if (cart1 == null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            Product product = productService.findById(id);
            cart1.delCart(product,1);
            Map<Integer, CartItem> map = cart1.getMap();
            Set<Map.Entry<Integer, CartItem>> entries = map.entrySet();
            List<CartItem> cartItems = new ArrayList<>();
            for (Map.Entry<Integer, CartItem> entry : entries) {
                CartItem value = entry.getValue();
                cartItems.add(value);
            }
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
