package com.zhengrz.meetingfilm.hall.controller.vo;

import com.zhengrz.meetingfilm.utils.common.vo.BasePageVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class HallsReqVO extends BasePageVO {
    private String cinemaId;

    @Override
    public void checkParam() throws CommonServiceException {
        super.checkParam();
    }
}
