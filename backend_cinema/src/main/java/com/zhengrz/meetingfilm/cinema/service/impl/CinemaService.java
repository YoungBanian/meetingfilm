package com.zhengrz.meetingfilm.cinema.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengrz.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.zhengrz.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.zhengrz.meetingfilm.cinema.dao.entity.TbCinema;
import com.zhengrz.meetingfilm.cinema.dao.mapper.TbCinemaMapper;
import com.zhengrz.meetingfilm.cinema.service.ICinemaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@Service
public class CinemaService implements ICinemaService {

    @Resource
    private TbCinemaMapper cinemaMapper;

    @Override
    public void saveCinema(CinemaSavedReqVO cinemaSavedReqVO) {
        TbCinema cinema = new TbCinema();
        cinema.setCinemaName(cinemaSavedReqVO.getCinemaName());
        cinema.setCinemaPhone(cinemaSavedReqVO.getCinemaTele());
        cinema.setBrandId(Integer.parseInt(cinemaSavedReqVO.getBrandId()));
        cinema.setAreaId(Integer.parseInt(cinemaSavedReqVO.getAreaId()));
        cinema.setHallIds(cinemaSavedReqVO.getHallTypeIds());
        cinema.setImgAddress(cinemaSavedReqVO.getCinemaImgAddress());
        cinema.setCinemaAddress(cinemaSavedReqVO.getCinemaAddress());
        cinema.setMinimumPrice(Integer.parseInt(cinemaSavedReqVO.getCinemaPrice()));
        cinemaMapper.insert(cinema);
    }

    @Override
    public IPage<DescribeCinemasRespVO> describeCinemas(int current, int pageSize) {

        IPage<TbCinema> cinemas = cinemaMapper.selectPage(new Page<>(current, pageSize), null);

        IPage<DescribeCinemasRespVO> cinemasRespVOs = new Page<>();
        cinemasRespVOs.setCurrent(cinemas.getCurrent());
        cinemasRespVOs.setSize(cinemas.getSize());
        cinemasRespVOs.setPages(cinemas.getPages());
        cinemasRespVOs.setTotal(cinemas.getTotal());
        cinemasRespVOs.setRecords(cinemas.getRecords().stream().map(c -> {
            DescribeCinemasRespVO describeCinemas = new DescribeCinemasRespVO();
            describeCinemas.setCinemaName(c.getCinemaName());
            describeCinemas.setCinemaTele(c.getCinemaPhone());
            describeCinemas.setBrandId(c.getBrandId().toString());
            describeCinemas.setAreaId(c.getAreaId().toString());
            describeCinemas.setHallTypeIds(c.getHallIds());
            describeCinemas.setCinemaImgAddress(c.getImgAddress());
            describeCinemas.setCinemaAddress(c.getCinemaAddress());
            describeCinemas.setCinemaPrice(c.getMinimumPrice().toString());
            return describeCinemas;
        }).collect(Collectors.toList()));
        return cinemasRespVOs;
    }
}
