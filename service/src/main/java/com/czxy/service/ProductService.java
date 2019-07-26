package com.czxy.service;

import com.czxy.dao.ProductMapper;
import com.czxy.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    public List<Product> findAll(){
        List<Product> products = productMapper.selectAll();
        return products;
    }

    public Product findById(String id){
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }
}
