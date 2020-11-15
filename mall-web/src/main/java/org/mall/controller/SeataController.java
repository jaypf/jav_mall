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
 * @ClassName SeataController
 * @Description TODO
 * @Author Jay
 * @Date 2020/11/15 3:54
 * @Version 1.0
 */
@Api(value = "seata",tags={"seata"})
@Slf4j
@RestController
@RequestMapping("/api/seata")
public class SeataController {

    @Autowired
    private KillGoodsService killGoodsService;

    /**
     * @Description  ehcache测试
     * @Param [productId]
     * @Author Jay
     * @Date 2020/11/8 0:57
     * @return org.jay.mall.utils.response.HttpResponseBody
     **/
    @ApiOperation(value="ehcache测试获取商品详情")
    @RequestMapping(value = "/v1/product_detail", method = RequestMethod.GET)
    public HttpResponseBody ehcacheTest(@RequestParam("product") Integer productId,@RequestParam("name") String name){
        HttpResponseBody httpResponseBody = killGoodsService.seataTest(productId, name);
        return httpResponseBody;
    }

}
