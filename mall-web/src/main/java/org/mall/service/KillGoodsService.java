package org.mall.service;

import org.jay.mall.utils.response.HttpResponseBody;

/**
 * @ClassName KillGoodsService
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/1/14 14:51
 * @Version 1.0
 */
public interface KillGoodsService {

    /** ehcache测试 */
    HttpResponseBody ehcacheTest(Integer productId);

    /** seata测试 */
    HttpResponseBody seataTest(Integer productId,String name);

    /** redisInvry测试 */
    HttpResponseBody redisInrbryTest(Integer productId,Integer userId);

    /** redisLua测试 */
    HttpResponseBody redisLuaTest(Integer productId,Integer userId);

}
