package com.zhengrz.meetingfilm.hall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengrz.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.zhengrz.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.zhengrz.meetingfilm.hall.controller.vo.HallsReqVO;
import com.zhengrz.meetingfilm.hall.controller.vo.HallsRespVO;
import com.zhengrz.meetingfilm.hall.dao.entity.TbField;
import com.zhengrz.meetingfilm.hall.dao.entity.TbHallFilmInfo;
import com.zhengrz.meetingfilm.hall.dao.mapper.TbFieldMapper;
import com.zhengrz.meetingfilm.hall.dao.mapper.TbHallFilmInfoMapper;
import com.zhengrz.meetingfilm.hall.feign.FilmFeignApi;
import com.zhengrz.meetingfilm.hall.service.IHallService;
import com.zhengrz.meetingfilm.utils.common.vo.BaseResponseVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import com.zhengrz.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class HallService implements IHallService {

    @Resource
    private FilmFeignApi filmFeignApi;

    @Resource
    private TbFieldMapper fieldMapper;

    @Resource
    private TbHallFilmInfoMapper hallFilmInfoMapper;

    @Override
    public IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) {

        QueryWrapper wrapper = new QueryWrapper();
        if (ToolUtils.strIsNotNul(hallsReqVO.getCinemaId())) {
            wrapper.eq("cinema_id", hallsReqVO.getCinemaId());
        }

        IPage<HallsRespVO> result = fieldMapper.describeHalls(new Page<>(hallsReqVO.getCurrent(), hallsReqVO.getPageSize()), wrapper);

        return result;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHall(HallSavedReqVO reqVO) throws CommonServiceException {
        // 播放厅的列表数据
        TbField field = new TbField();
        field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
        field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
        field.setBeginTime(reqVO.getBeginTime());
        field.setEndTime(reqVO.getEndTime());
        field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
        field.setHallName(reqVO.getHallName());
        field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));
        fieldMapper.insert(field);

        // 播放厅对应的影片数据，影片冗余数据，缓存里有一份
        TbHallFilmInfo hallFilmInfo = describeFilmInfo(reqVO.getFilmId());
        hallFilmInfoMapper.insert(hallFilmInfo);
    }


    // 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
    private TbHallFilmInfo describeFilmInfo(String filmId) throws CommonServiceException {
        BaseResponseVO<DescribeFilmRespVO> describeFilmResp = filmFeignApi.describeFilmById(filmId);
        DescribeFilmRespVO describeFilm = describeFilmResp.getData();
        if (describeFilm == null || describeFilm.getFilmId() == null) throw new CommonServiceException(400, "抱歉，未能找到对应的电影信息，filmId : "+filmId);
        // 组织参数
        TbHallFilmInfo hallFilmInfo = new TbHallFilmInfo();

        hallFilmInfo.setFilmId(ToolUtils.str2Int(describeFilm.getFilmId()));
        hallFilmInfo.setFilmName(describeFilm.getFilmName());
        hallFilmInfo.setFilmLength(describeFilm.getFilmLength());
        hallFilmInfo.setFilmCats(describeFilm.getFilmCats());
        hallFilmInfo.setActors(describeFilm.getActors());
        hallFilmInfo.setImgAddress(describeFilm.getImgAddress());

        return hallFilmInfo;
    }
}
