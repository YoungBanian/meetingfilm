package com.zhengrz.meetingfilm.cinema.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.zhengrz.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.zhengrz.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.zhengrz.meetingfilm.cinema.service.ICinemaService;
import com.zhengrz.meetingfilm.utils.common.vo.BasePageVO;
import com.zhengrz.meetingfilm.utils.common.vo.BaseResponseVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 影院模块表现层
 */
@RestController
@RequestMapping("/cinemas")
public class CinemaController {


    @Autowired
    private ICinemaService cinemaService;


    /**
     * 新增影院
     * @param cinemaSavedReqVO
     * @return
     * @throws CommonServiceException
     */
    @PostMapping("/cinema:add")
    public BaseResponseVO saveCinema(@RequestBody CinemaSavedReqVO cinemaSavedReqVO) throws CommonServiceException {

        cinemaSavedReqVO.checkParam();

        cinemaService.saveCinema(cinemaSavedReqVO);

        return BaseResponseVO.success();
    }

    /**
     * 影院列表
     * @param basePageVO
     * @return
     * @throws CommonServiceException
     */
    @GetMapping("")
    public BaseResponseVO describeCinemas(BasePageVO basePageVO) throws CommonServiceException {

        basePageVO.checkParam();

        IPage<DescribeCinemasRespVO> cinemas = cinemaService.describeCinemas(basePageVO.getCurrent(), basePageVO.getPageSize());

        return BaseResponseVO.success(descrbePageResult(cinemas, "cinemas"));
    }

    private Map<String, Object> descrbePageResult(IPage page, String title) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("totalSize",page.getTotal());
        result.put("totalPages",page.getPages());
        result.put("pageSize",page.getSize());
        result.put("nowPage",page.getCurrent());
        result.put(title, page.getRecords());
        return result;
    }


}
