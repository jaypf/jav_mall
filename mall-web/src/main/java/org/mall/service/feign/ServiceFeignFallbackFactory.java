package org.mall.service.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProductServiceFeignFallbackFactory
 * @Description  Product调用fallback处理
 * @Author Jay.Jia
 * @Date 2020/3/30 15:05
 * @Version 1.0
 */
@Slf4j
@Component
public class ServiceFeignFallbackFactory implements FallbackFactory {

    @Override
    public Object create(Throwable throwable) {
        return null;
    }
}
