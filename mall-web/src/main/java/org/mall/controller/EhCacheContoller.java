package org.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jay.mall.utils.response.HttpResponseBody;
import org.mall.service.KillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName KillGoodsContoller
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/9/27 15:39
 * @Version 1.0
 */
@Api(value = "EhCache",tags={"EhCache"})
@Slf4j
@RestController
@RequestMapping("/api/ehcache")
public class EhCacheContoller {

    @Autowired
    private KillGoodsService killGoodsService;

    @PostMapping("/kill")
    public HttpResponseBody kill(){

        return HttpResponseBody.successResponse("啦啦");
    }

    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
    public HttpResponseBody kill1(){

        return HttpResponseBody.successResponse("啦啦11");
    }


    /**
     * @Description  ehcache测试
     * @Param [productId]
     * @Author Jay
     * @Date 2020/11/8 0:57
     * @return org.jay.mall.utils.response.HttpResponseBody
     **/
    @ApiOperation(value="ehcache测试获取商品详情")
    @RequestMapping(value = "/v1/product_detail", method = RequestMethod.GET)
    public HttpResponseBody ehcacheTest(@RequestParam("product") Integer productId){
        HttpResponseBody httpResponseBody = killGoodsService.ehcacheTest(productId);
        return httpResponseBody;
    }




}
