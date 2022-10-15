package com.boot.basic.netty;

import com.boot.basic.common.annotation.WebLog;
import com.boot.basic.common.base.ApiFinalResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HTuoZhou
 */
@Api(tags = "Netty控制器")
@RestController
@RequestMapping("/netty")
public class NettyController {

    private final NettyTask nettyTask;

    public NettyController(NettyTask nettyTask) {
        this.nettyTask = nettyTask;
    }

    @ApiOperation("测试")
    @GetMapping("/test")
    @WebLog
    public ApiFinalResult<String> test() {
        return ApiFinalResult.success(nettyTask.test());
    }

}
