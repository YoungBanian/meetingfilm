package com.zhengrz.meetingfilm.user.controller;

import com.zhengrz.meetingfilm.user.controller.vo.LoginReqVO;
import com.zhengrz.meetingfilm.user.service.IUserService;
import com.zhengrz.meetingfilm.utils.common.vo.BaseResponseVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import com.zhengrz.meetingfilm.utils.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ResponseBody
    public BaseResponseVO login(@RequestBody LoginReqVO loginReqVO) throws CommonServiceException {

        // 数据校验
        loginReqVO.checkParam();

        // 验证用户名与密码
        String userId = userService.checkUserLogin(loginReqVO.getUsername(), loginReqVO.getPassword());

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

        String randomKey = jwtTokenUtil.getRandomKey();

        String token = jwtTokenUtil.generateToken(userId, randomKey);

        Map<String, String> result = new HashMap<>();
        result.put("randomKey", randomKey);
        result.put("token", token);

        return BaseResponseVO.success(result);
    }


}
