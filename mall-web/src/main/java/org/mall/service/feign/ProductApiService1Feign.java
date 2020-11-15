package org.mall.service.feign;

import org.mall.api.service.mall.product.IProductApiService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName ProductApiService1Feign
 * @Description TODO
 * @Author 33505
 * @Date 2020/11/8 3:33
 * @Version 1.0
 */
@FeignClient(name = "mall-product"/*, path = "/product"*/
        /*fallback = ProductServiceFeignFallback.class,不能获取具体异常*/
        ,fallbackFactory = ServiceFeignFallbackFactory.class)
public interface ProductApiService1Feign extends IProductApiService {

}
