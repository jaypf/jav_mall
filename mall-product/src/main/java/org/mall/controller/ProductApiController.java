package org.mall.controller;

import lombok.extern.slf4j.Slf4j;
import org.jay.mall.pojo.domain.ProductEntity;
import org.jay.mall.utils.response.HttpResponseBody;
import org.mall.api.service.mall.product.IProductApiService;
import org.mall.mapper.ProductEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author 33505
 * @Date 2020/11/8 3:51
 * @Version 1.0
 */
@Slf4j
@RestController
public class ProductApiController implements IProductApiService {

    @Autowired
    private ProductEntityMapper productEntityMapper;

    @Override
    public HttpResponseBody getProductDetailByIdApi(Integer productId) {
        ProductEntity productEntity = productEntityMapper.selectByPrimaryKey(productId);
        log.debug("获取到商品【{}】详情:{}",productId,productEntity.toString());
        return HttpResponseBody.successResponse(productEntity);
    }
}
