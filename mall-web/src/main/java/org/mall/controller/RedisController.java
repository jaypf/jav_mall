package org.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jay.mall.utils.response.HttpResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RedisController
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/2/2 17:03
 * @Version 1.0
 */
@Api(value = "Redis",tags={"Redis"})
@Slf4j
@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value="redis测试GEO->经纬度")
    @RequestMapping(value = "/v1/geo", method = RequestMethod.GET)
    public HttpResponseBody redisTest(){

        GeoOperations geoOperations = redisTemplate.opsForGeo();
        //首先存入客户端上传的经纬度和指定地点的经纬度
        Map<String,Object> map = new HashMap<>();
        // 假如客户端的传的是北京西站的经纬度,指定地点是北京南站
        map.put("BJXZ",new Point(116.321275,39.895096));
        map.put("BJNZ",new Point(116.378438,39.864666));
        // 将这些地址数据保存到redis中
        geoOperations.geoAdd("addr",map);
        double value = geoOperations.geoDist("addr", "BJXZ", "BJNZ", RedisGeoCommands.DistanceUnit.METERS).getValue();

        return HttpResponseBody.successResponse(value+"");
    }


}
