package org.mall.service;

import org.jay.mall.utils.response.HttpResponseBody;

/**
 * @ClassName IProductService
 * @Description TODO
 * @Author 33505
 * @Date 2020/11/8 3:55
 * @Version 1.0
 */
public interface IProductService {

    HttpResponseBody getProductDetailById(Integer productId);

}
