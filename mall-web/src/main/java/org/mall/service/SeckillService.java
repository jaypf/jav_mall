package org.mall.service;

import org.jay.mall.utils.response.HttpResponseBody;

/**
 * @ClassName KillGoodsService
 * @Description TODO
 * @Author 33505
 * @Date 2020/11/8 0:50
 * @Version 1.0
 */
 interface SeckillService {

    /** ehcache测试 */
     HttpResponseBody ehcacheTest(Integer productId);

    /** seata测试 */
     HttpResponseBody seataTest(Integer productId,String name);

    /** redisInvry测试 */
     HttpResponseBody redisInrbryTest(Integer productId,Integer userId);

    /** redisLua测试 */
     HttpResponseBody redisLuaTest(Integer productId,Integer userId);
}
