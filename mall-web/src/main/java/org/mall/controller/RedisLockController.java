package org.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jay.mall.utils.response.HttpResponseBody;
import org.mall.service.KillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RedisLockController
 * @Description TODO
 * @Author Jay
 * @Date 2020/11/16 1:07
 * @Version 1.0
 */
@Api(value = "RedisLock",tags={"RedisLock"})
@Slf4j
@RestController
@RequestMapping("/api/redisLock")
public class RedisLockController {

    @Autowired
    private KillGoodsService killGoodsService;


    /**
     * @Description redis_invry做库存预扣减测试
     * 这里的userId应该是从token中获取，这里做测试，所以直接写到了参数里
     * @Param [productId, name]
     * @Author Jay
     * @Date 2020/11/16 1:08
     * @return org.jay.mall.utils.response.HttpResponseBody
     **/
    @ApiOperation(value="ehcache测试获取商品详情")
    @RequestMapping(value = "/v1/redis_invry", method = RequestMethod.GET)
    public HttpResponseBody redis_invry(@RequestParam("product") Integer productId, @RequestParam("userId") Integer userId){
        HttpResponseBody httpResponseBody = killGoodsService.redisInrbryTest(productId, userId);
        return httpResponseBody;
    }

    /**
     * @Description redis+lua分布式锁做库存扣减测试
     * 这里的userId应该是从token中获取，这里做测试，所以直接写到了参数里
     * @Param [productId, name]
     * @Author Jay
     * @Date 2020/11/16 1:08
     * @return org.jay.mall.utils.response.HttpResponseBody
     **/
    @ApiOperation(value="ehcache测试获取商品详情")
    @RequestMapping(value = "/v1/redis_lua", method = RequestMethod.GET)
    public HttpResponseBody redisLuaLock(@RequestParam("product") Integer productId, @RequestParam("userId") Integer userId){
        HttpResponseBody httpResponseBody = killGoodsService.redisLuaTest(productId, userId);
        return httpResponseBody;
    }

}
