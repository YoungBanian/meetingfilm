package com.zhengrz.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengrz.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.zhengrz.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;

public interface ICinemaService {

    void saveCinema(CinemaSavedReqVO cinemaSavedReqVO);

    IPage<DescribeCinemasRespVO> describeCinemas(int current, int pageSize);

}
