package org.mall.api.service.mall.product;

import org.jay.mall.utils.response.HttpResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName IProductApiService
 * @Description TODO
 * @Author 33505
 * @Date 2020/11/8 2:20
 * @Version 1.0
 */
@RequestMapping("/product/killGoods/service")
public interface IProductApiService {

    @RequestMapping(value = "/detailById", method = RequestMethod.GET)
    HttpResponseBody getProductDetailByIdApi(@RequestParam("productId") Integer productId);

}
