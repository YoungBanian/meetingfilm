package com.zhengrz.meetingfilm.hall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.zhengrz.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.zhengrz.meetingfilm.hall.controller.vo.HallsReqVO;
import com.zhengrz.meetingfilm.hall.controller.vo.HallsRespVO;
import com.zhengrz.meetingfilm.hall.service.IHallService;
import com.zhengrz.meetingfilm.utils.common.vo.BaseResponseVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/halls")
public class HallController {


    @Autowired
    private IHallService hallService;

    /**
     * 新增播放厅
     * @param hallSavedReqVO
     * @return
     * @throws CommonServiceException
     */
    @PostMapping("/hall:add")
    public BaseResponseVO saveHall(@RequestBody HallSavedReqVO hallSavedReqVO) throws CommonServiceException {

        hallSavedReqVO.checkParam();

        hallService.saveHall(hallSavedReqVO);

        return BaseResponseVO.success();
    }

    /**
     * 影片播放厅列表
     * @param hallsReqVO
     * @return
     * @throws CommonServiceException
     */
    @GetMapping("")
    public BaseResponseVO describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {

        hallsReqVO.checkParam();

        IPage<HallsRespVO> page = hallService.describeHalls(hallsReqVO);

        return BaseResponseVO.success(descrbePageResult(page, "halls"));
    }


    /**
     * 获取分页对象
     * @param page
     * @param title
     * @return
     */
    private Map<String,Object> descrbePageResult(IPage page, String title){
        Map<String, Object> result = Maps.newHashMap();
        result.put("totalSize",page.getTotal());
        result.put("totalPages",page.getPages());
        result.put("pageSize",page.getSize());
        result.put("nowPage",page.getCurrent());
        result.put(title, page.getRecords());
        return result;
    }


}
