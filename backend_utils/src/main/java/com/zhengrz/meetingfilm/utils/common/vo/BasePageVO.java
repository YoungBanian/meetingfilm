package com.zhengrz.meetingfilm.utils.common.vo;

import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class BasePageVO extends BaseRequestVO {

    private Integer current = 1;
    private Integer pageSize = 10;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
