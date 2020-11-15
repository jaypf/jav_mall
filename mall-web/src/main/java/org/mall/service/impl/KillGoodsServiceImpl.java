package org.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.seata.spring.annotation.GlobalTransactional;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private IProductApiService iProductApiService;

    /**
     * @Description
     * 这里对高并发场景进行QPS测试
     * 1，先从本地缓存ehcache里边拿，如果拿到返回
     * 2,1级缓存里没拿到就从二级缓存redis里拿
     * 3，如果都没拿到，就从mysql里查询，如果查到保存到缓存中并返回结果。
     * @Param [productId]
     * @Author Jay
     * @Date 2020/11/10 1:09
     * @return org.jay.mall.utils.response.HttpResponseBody
     **/
    @Override
    public HttpResponseBody ehcacheTest(Integer productId) {
        //商品实体信息
        ProductEntity productEntity = null;
        //该商品对应的缓存key
        String productDetailCacheKey = CacheConstant.KILLGOOD_DETAIL + productId;

        //1、从本地缓存里面查询数据
        Cache localCache = cacheManager.getCache(CacheConstant.EHCACHE_KEY);
        Element productElement = localCache.get(productDetailCacheKey);
        if(!ObjectUtils.isEmpty(productElement)){
            log.info(Thread.currentThread().getName() + "--------ehcache缓存里面得到数据-------");
            productEntity = (ProductEntity) productElement.getObjectValue();
            return HttpResponseBody.successResponse(productEntity);
        }

        //2、从redis里查询数据
        Object redisCache = redisTemplate.opsForValue().get(productDetailCacheKey);
        if(!ObjectUtils.isEmpty(redisCache)){
            log.info(Thread.currentThread().getName() + "--------redfis缓存里面得到数据-------");
            productEntity = (ProductEntity) redisCache;
            ProductEntity productEntity1 = JSONObject.parseObject(redisCache.toString(), ProductEntity.class);
            return HttpResponseBody.successResponse(productEntity);
        }

        //3、从数据库获取 (类似秒杀场景，一般情况都应该事先预热缓存数据，查询时就会直接从缓存中获取，而这里从数据库查询是为了程序的健壮性考虑)
        // TODO-Jay 这里要加一个分布式锁(先用jvm锁代替)
        synchronized (iProductApiService){
            //从本地缓存里面查询数据
            Cache localCache_ = cacheManager.getCache(CacheConstant.EHCACHE_KEY);
            Element productElement_ = localCache_.get(productDetailCacheKey);
            if(!ObjectUtils.isEmpty(productElement_)){
                log.info(Thread.currentThread().getName() + "--------ehcache缓存里面得到数据-------");
                productEntity = (ProductEntity) productElement_.getObjectValue();
                return HttpResponseBody.successResponse(productEntity);
            }

            //从redis里查询数据
            Object redisCache_ = redisTemplate.opsForValue().get(productDetailCacheKey);
            if(!ObjectUtils.isEmpty(redisCache_)){
                log.info(Thread.currentThread().getName() + "--------redfis缓存里面得到数据-------");
                productEntity = (ProductEntity) redisCache;
                ProductEntity productEntity1 = JSONObject.parseObject(redisCache_.toString(), ProductEntity.class);
                return HttpResponseBody.successResponse(productEntity);
            }

            HttpResponseBody productDetailByIdApi = iProductApiService.getProductDetailByIdApi(productId);
            if(HttpResponseBody.isSuccess(productDetailByIdApi)){
                productEntity = JSONObject.parseObject(JSONObject.toJSON(productDetailByIdApi.getData()).toString(), ProductEntity.class);
                //查询到数据后放入本地缓存及redis
                localCache.putIfAbsent(new Element(productDetailCacheKey, productEntity));
                redisTemplate.opsForValue().set(productDetailCacheKey, productEntity.toString(), 2, TimeUnit.DAYS);
            }else{
                //数据库也没查到，那么就要防止缓存穿透(这里设置短时间的缓存，空数据没必要占用内存空间)
                redisTemplate.opsForValue().set(productDetailCacheKey, "null", 5, TimeUnit.SECONDS);
            }

        }
        return HttpResponseBody.successResponse(productEntity);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public HttpResponseBody seataTest(Integer productId, String name) {
        ProductEntity build = ProductEntity.builder().goodsId(productId).goodsName(name).build();
        HttpResponseBody httpResponseBody = iProductApiService.updateProductDetailByIdApi(build);
        if(productId > 1){
            int a = 1/0;
        }
        return httpResponseBody;
    }


}
