package com.boot.basic.internal.controller;

import com.boot.basic.common.annotation.WebLog;
import com.boot.basic.common.base.ApiFinalResult;
import com.boot.basic.common.base.BaseController;
import com.boot.basic.common.base.PageFinalResult;
import com.boot.basic.internal.dto.AddUserDTO;
import com.boot.basic.internal.dto.ListUserDTO;
import com.boot.basic.internal.dto.PageUserDTO;
import com.boot.basic.internal.service.IUserService;
import com.boot.basic.internal.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author HTuoZhou
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器")
public class UserController extends BaseController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation("获取用户列表信息")
    @GetMapping("/listUser")
    @WebLog
    public ApiFinalResult<List<UserVO>> listUser(@Valid ListUserDTO listUserDTO) {
        return ApiFinalResult.success(userService.listUser(listUserDTO));
    }

    @ApiOperation("获取用户分页信息")
    @GetMapping("/pageUser")
    @WebLog
    public ApiFinalResult<PageFinalResult<UserVO>> pageUser(@Valid PageUserDTO pageUserDTO) {
        return ApiFinalResult.success(userService.pageUser(pageUserDTO));
    }

    @ApiOperation("新增用户信息")
    @PostMapping("/addUser")
    @WebLog
    public ApiFinalResult<String> addUser(@RequestBody @Valid AddUserDTO addUserDTO) {
        return ApiFinalResult.success(userService.addUser(addUserDTO));
    }

}
