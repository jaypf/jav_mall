package org.mall.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jay.mall.utils.response.HttpResponseBody;
import org.mall.service.IProductService;
import org.springframework.stereotype.Service;

/**
 * @ClassName IProductApiService
 * @Description TODO
 * @Author 33505
 * @Date 2020/11/8 2:43
 * @Version 1.0
 */
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {


    @Override
    public HttpResponseBody getProductDetailById(Integer productId) {
        return null;
    }
}
