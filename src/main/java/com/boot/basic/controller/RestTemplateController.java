package com.boot.basic.controller;

import com.boot.basic.common.annotation.WebLog;
import com.boot.basic.common.base.ApiFinalResult;
import com.boot.basic.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HTuoZhou
 */
@RestController
@RequestMapping("/restTemplate")
@Api(tags = "restTemplate控制器")
public class RestTemplateController extends BaseController {

    @ApiOperation("天气查询")
    @GetMapping("/listWeather")
    @WebLog
    public ApiFinalResult<String> listWeather() {
        return ApiFinalResult.success(restTemplate.getForObject("http://aider.meizu.com/app/weather/listWeather?cityIds=101280101", String.class));
    }

}
