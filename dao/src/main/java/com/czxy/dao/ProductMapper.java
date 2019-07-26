package com.czxy.dao;

import com.czxy.domain.Product;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@org.apache.ibatis.annotations.Mapper
@Repository
public interface ProductMapper extends Mapper<Product> {
}
