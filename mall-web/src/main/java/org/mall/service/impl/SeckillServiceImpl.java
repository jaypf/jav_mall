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
import org.mall.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
public class SeckillServiceImpl implements SeckillService {


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
                //查询到数据后放入本地缓存及redis(注意这里，ehcache的缓存失效失效配置的是1天，是小于refdis的，目的是为了减少GC压力)
                localCache.putIfAbsent(new Element(productDetailCacheKey, productEntity));
                redisTemplate.opsForValue().set(productDetailCacheKey, productEntity.toString(), 2, TimeUnit.DAYS);
            }else{
                //数据库也没查到，那么就要防止缓存穿透(这里设置短时间的缓存，空数据没必要占用内存空间)
                redisTemplate.opsForValue().set(productDetailCacheKey, "null", 5, TimeUnit.SECONDS);
            }

        }
        return HttpResponseBody.successResponse(productEntity);
    }

    /**
     * @Description 这里做分布式事务seata测试
     * @Param [productId, name]
     * @Author Jay
     * @Date 2020/11/16 1:18
     * @return org.jay.mall.utils.response.HttpResponseBody
     **/
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

    /**
     * @Description 这里做redis incrby测试
     * @Param [productId, userId]
     * @Author Jay
     * @Date 2020/11/16 1:18
     * @return org.jay.mall.utils.response.HttpResponseBody
     **/
    @Override
    public HttpResponseBody redisInrbryTest(Integer productId, Integer userId) {

        //redis中使用set记录已经参与过秒杀的用户信息
        String cacheKey = CacheConstant.KILLED_GOOD_USER + productId;
        //判断该用户是否参与过
        Boolean member = redisTemplate.opsForSet().isMember(cacheKey, userId);
        if(member){
            log.info("用户【{}】已经参与过商品【{}】的秒杀活动！",userId, productId);
            return HttpResponseBody.successResponse("重复参与！");
        }

        //库存key
        String countCacheKey = CacheConstant.KILL_GOOD_COUNT + productId;
        //这里做的是库存的预扣减操作，当用户由于特殊原因支付失败，或者不支付时该库存不能补偿回来，就会成为一个大的负数，影响后边用户参与后一直提示库存不足
        Long increment = redisTemplate.opsForValue().increment(countCacheKey, -1);
        if(increment < 0){
            log.info("商品【{}】库存不足！", productId);
            return HttpResponseBody.failResponse("库存不足！");
        }

        //库存预扣减成功后把该用户记录到redis
        redisTemplate.opsForValue().append(cacheKey, userId.toString());
        return HttpResponseBody.successResponse();
    }

    @Override
    public HttpResponseBody redisLuaTest(Integer productId, Integer userId) {
        //redis中使用set记录已经参与过秒杀的用户信息
        String cacheKey = CacheConstant.KILLED_GOOD_USER + productId;
        //判断该用户是否参与过
        Boolean member = redisTemplate.opsForSet().isMember(cacheKey, userId);
        if(member){
            log.info("用户【{}】已经参与过商品【{}】的秒杀活动！",userId, productId);
            return HttpResponseBody.successResponse("重复参与！");
        }

        //库存key
        String countCacheKey = CacheConstant.KILL_GOOD_COUNT + productId;
        //stock(countCacheKey, 1, STOCK_LUA);

        return null;
    }

    /**
     * 扣库存
     *
     * @param key 库存key
     * @param num 扣减库存数量
     * @return 扣减之后剩余的库存【-3:库存未初始化; -2:库存不足; -1:不限库存; 大于等于0:扣减库存之后的剩余库存】
     */
    public Long stock(String key, int num, String script) {
        // 脚本里的KEYS参数
        List<String> keys = new ArrayList<>();
        keys.add(key);
        // 脚本里的ARGV参数
        List<String> args = new ArrayList<>();
        args.add(Integer.toString(num));

        long result = (long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(script, keys, args);
                }

                // 单机模式
                else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(script, keys, args);
                }
                /*else if (nativeConnection instanceof Redisson) {
                    Redisson redisson = (Redisson)nativeConnection;
                    return redisson.getScript().eval(RScript.Mode.READ_WRITE,STOCK_LUA,RScript.ReturnType.INTEGER, Collections.singletonList(keys), new List[]{args});
                }*/
                return CacheConstant.UNINITIALIZED_STOCK;
            }
        });
        return result;
    }


}
