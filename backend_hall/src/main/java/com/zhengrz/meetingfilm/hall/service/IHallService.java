package com.zhengrz.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengrz.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.zhengrz.meetingfilm.hall.controller.vo.HallsReqVO;
import com.zhengrz.meetingfilm.hall.controller.vo.HallsRespVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;

public interface IHallService {

    void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException;

    IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO);

}
