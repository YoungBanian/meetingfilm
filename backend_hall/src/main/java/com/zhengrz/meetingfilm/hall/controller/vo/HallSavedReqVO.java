package com.zhengrz.meetingfilm.hall.controller.vo;

import com.zhengrz.meetingfilm.utils.common.vo.BaseRequestVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class HallSavedReqVO extends BaseRequestVO {
    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
