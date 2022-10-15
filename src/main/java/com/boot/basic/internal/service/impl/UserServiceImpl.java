package com.boot.basic.internal.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.basic.common.base.PageFinalResult;
import com.boot.basic.internal.dto.AddUserDTO;
import com.boot.basic.internal.dto.ListUserDTO;
import com.boot.basic.internal.dto.PageUserDTO;
import com.boot.basic.internal.entity.User;
import com.boot.basic.internal.mapper.UserMapper;
import com.boot.basic.internal.service.IUserService;
import com.boot.basic.internal.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author HTuoZhou
 * @since 2022-03-22
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    @Cacheable
    public List<UserVO> listUser(ListUserDTO listUserDto) {
        String startTime = listUserDto.getStartTime();
        String endTime = listUserDto.getEndTime();

        List<UserVO> userVOList = new ArrayList<>();

        List<User> userList = this.list(Wrappers.<User>lambdaQuery()
                .between(User::getCreateTime, startTime, endTime));
        userList.forEach((x) -> {
            UserVO userVo = new UserVO();
            BeanUtils.copyProperties(x, userVo);
            userVOList.add(userVo);
        });

        return userVOList;
    }

    @Override
    public PageFinalResult<UserVO> pageUser(PageUserDTO pageUserDTO) {
        Integer pageNum = pageUserDTO.getPageNum();
        Integer pageSize = pageUserDTO.getPageSize();

        ListUserDTO listUserDto = new ListUserDTO();
        BeanUtils.copyProperties(pageUserDTO, listUserDto);
        List<UserVO> userVOList = this.listUser(listUserDto);

        return new PageFinalResult<>(pageNum, pageSize, userVOList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addUser(AddUserDTO addUserDTO) {
        User user = new User();
        user.setUsername(addUserDTO.getUsername())
                .setPassword(addUserDTO.getPassword());
        this.save(user);
        return "新增用户信息成功";
    }
}
