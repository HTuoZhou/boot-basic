package com.boot.basic.internal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.basic.common.base.PageFinalResult;
import com.boot.basic.internal.dto.AddUserDTO;
import com.boot.basic.internal.dto.ListUserDTO;
import com.boot.basic.internal.dto.PageUserDTO;
import com.boot.basic.internal.entity.User;
import com.boot.basic.internal.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author HTuoZhou
 * @since 2022-03-22
 */
public interface IUserService extends IService<User> {

    /**
     * 获取用户列表信息
     *
     * @param listUserDto ListUserDTO
     * @return List<UserVO>
     */
    List<UserVO> listUser(ListUserDTO listUserDto);

    /**
     * 获取用户分页信息
     *
     * @param pageUserDTO PageUserDTO
     * @return PageFinalResult<UserVO>
     */
    PageFinalResult<UserVO> pageUser(PageUserDTO pageUserDTO);

    /**
     * 新增用户信息
     *
     * @param addUserDTO AddUserDTO
     * @return String
     */
    String addUser(AddUserDTO addUserDTO);
}
