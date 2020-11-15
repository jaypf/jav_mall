package org.mall.pay.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.jay.mall.pojo.domain.ProductEntity;

@Mapper
public interface ProductEntityMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(ProductEntity record);

    int insertSelective(ProductEntity record);

    ProductEntity selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(ProductEntity record);

    int updateByPrimaryKey(ProductEntity record);
}