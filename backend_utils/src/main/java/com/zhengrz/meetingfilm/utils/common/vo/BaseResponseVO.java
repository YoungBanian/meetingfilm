package com.zhengrz.meetingfilm.utils.common.vo;

import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class BaseResponseVO<T> {

    private Integer code;
    private String message;
    private T Data;

    private BaseResponseVO(){};

    // 成功但是无参数
    public static BaseResponseVO success() {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        return response;
    }

    // 成功有参数
    public static<T> BaseResponseVO success(T data) {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    // 未登录异常
    public static<T> BaseResponseVO noLogin() {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(401);
        response.setMessage("请登录");
        return response;
    }

    public static<T> BaseResponseVO serviceException(CommonServiceException e) {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }



}
