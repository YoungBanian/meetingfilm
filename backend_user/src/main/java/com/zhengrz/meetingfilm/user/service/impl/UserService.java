package com.zhengrz.meetingfilm.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhengrz.meetingfilm.user.dao.entity.TbUser;
import com.zhengrz.meetingfilm.user.dao.mapper.TbUserMapper;
import com.zhengrz.meetingfilm.user.service.IUserService;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import com.zhengrz.meetingfilm.utils.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Resource
    private TbUserMapper userMapper;


    @Override
    public String checkUserLogin(String username, String password) throws CommonServiceException {

        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);

        List<TbUser> users = userMapper.selectList(wrapper);

        TbUser user = null;

        if (users == null || users.size() == 0) {
            throw new CommonServiceException(404, "用户名输入有误");
        } else {
            user = users.stream().findFirst().get();
        }

        String encrypt = MD5Util.encrypt(password);

        if (!encrypt.equals(user.getUserPwd())) {
            throw new CommonServiceException(500, "用户密码输入有误");
        } else {
            System.out.println(user);
            return user.getUuid().toString();
        }
    }

}
