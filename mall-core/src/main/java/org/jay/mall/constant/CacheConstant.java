package org.jay.mall.constant;

/**
 * @ClassName CacheConstant
 * @Description TODO
 * @Author 33505
 * @Date 2020/11/8 1:26
 * @Version 1.0
 */
public class CacheConstant {

   //===========================================EhCache=================================
    /**
     * 商品详情EhCache key
     * 所有商品都缓存在该key下，使用该key返回Cache对象
     */
    public static final String EHCACHE_KEY = "product_detail";



   //===========================================商品使用缓存key=================================
    /**商品详情缓存key统一前缀*/
    public static final String KILLGOOD_DETAIL = "KILL_GOOD_DETAIL_";
}
