package org.mall.controller;

import lombok.extern.slf4j.Slf4j;
import org.jay.mall.utils.response.HttpResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName KillGoodsContoller
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/9/27 15:39
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/killgoodsSpec")
public class KillGoodsContoller {

    @PostMapping("/kill")
    public HttpResponseBody kill(){

        return HttpResponseBody.successResponse("啦啦");
    }

    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
    public HttpResponseBody kill1(){

        return HttpResponseBody.successResponse("啦啦11");
    }
}
