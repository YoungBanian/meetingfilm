package com.zhengrz.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.zhengrz.meetingfilm.film.dao.entity.TbFilm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author zhengrz
 * @since 2020-03-11
 */
public interface TbFilmMapper extends BaseMapper<TbFilm> {

    IPage<DescribeFilmsRespVO> describeFilms(Page<DescribeFilmRespVO> page);

    DescribeFilmRespVO descrbeFilmById(@Param("filmId") String filmId);

}
