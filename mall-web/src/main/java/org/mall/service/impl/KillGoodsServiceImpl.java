package org.mall.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.jay.mall.constant.CacheConstant;
import org.jay.mall.pojo.domain.ProductEntity;
import org.jay.mall.utils.response.HttpResponseBody;
import org.mall.api.service.mall.product.IProductApiService;
import org.mall.service.KillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName KillGoodsServiceImpl
 * @Description TODO
 * @Author 33505
 * @Date 2020/11/8 0:51
 * @Version 1.0
 */
@Slf4j
@Service
public class KillGoodsServiceImpl implements KillGoodsService {


    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private IProductApiService iProductApiService;

    @Override
    public HttpResponseBody ehcacheTest(Integer productId) {
        //该商品对应的缓存key
        String productDetailCacheKey = CacheConstant.EHCACHE_KEY + productId;

        //1、从本地缓存里面查询数据
        Cache cache = cacheManager.getCache(CacheConstant.EHCACHE_KEY);
        Element productElement = cache.get(productDetailCacheKey);
        if(!ObjectUtils.isEmpty(productElement)){
            log.info(Thread.currentThread().getName() + "--------ehcache缓存里面的到数据-------");
            ProductEntity productEntity = (ProductEntity) productElement.getObjectValue();
            return HttpResponseBody.successResponse(productEntity);
        }
        HttpResponseBody productDetailByIdApi = iProductApiService.getProductDetailByIdApi(productId);

        return productDetailByIdApi;
    }



}
