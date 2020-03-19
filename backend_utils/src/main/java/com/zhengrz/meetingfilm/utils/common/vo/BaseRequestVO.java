package com.zhengrz.meetingfilm.utils.common.vo;

import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;

public abstract class BaseRequestVO {

    public abstract void checkParam() throws CommonServiceException;

}
