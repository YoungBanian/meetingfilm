package com.zhengrz.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;

public interface IFilmService {

    // 获取演员列表
    IPage<DescribeActorsRespVO> describeActors(int current, int pageSize) throws CommonServiceException;

    // 获取电影列表
    IPage<DescribeFilmsRespVO> describeFilms(int current, int pageSize) throws CommonServiceException;

    // 根据主键获取电影信息
    DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException;

    // 保存电影信息
    void saveFilm(FilmSavedReqVO filmSavedReqVO) throws CommonServiceException;

}
