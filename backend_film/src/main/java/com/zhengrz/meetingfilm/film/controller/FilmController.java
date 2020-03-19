package com.zhengrz.meetingfilm.film.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.zhengrz.meetingfilm.film.service.IFilmService;
import com.zhengrz.meetingfilm.utils.common.vo.BasePageVO;
import com.zhengrz.meetingfilm.utils.common.vo.BaseResponseVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * 影片模块
 */
@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private IFilmService filmService;

    /**
     * 获取演员列表
     * @param basePageVO
     * @return
     * @throws CommonServiceException
     */
    @GetMapping("/actors")
    public BaseResponseVO describeActors(BasePageVO basePageVO) throws CommonServiceException {

        // 检查入参
        basePageVO.checkParam();

        IPage<DescribeActorsRespVO> actors = filmService.describeActors(basePageVO.getCurrent(), basePageVO.getPageSize());

        return BaseResponseVO.success(descrbePageResult(actors, "actors"));
    }

    /**
     * 获取电影列表
     * @param request
     * @param basePageVO
     * @return
     * @throws CommonServiceException
     */
    @GetMapping("")
    public BaseResponseVO describeFilms(HttpServletRequest request, BasePageVO basePageVO) throws CommonServiceException {
        basePageVO.checkParam();
        // 调用逻辑层，获取返回参数
        IPage<DescribeFilmsRespVO> results = filmService.describeFilms(basePageVO.getCurrent(),basePageVO.getPageSize());
        return BaseResponseVO.success(descrbePageResult(results, "films"));
    }

    /**
     * 根据电影ID获取电影详情
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    @GetMapping("/{filmId}")
    public BaseResponseVO describeFilmById(@PathVariable("filmId")String filmId) throws CommonServiceException {

        DescribeFilmRespVO describeFilmRespVO = filmService.describeFilmById(filmId);

        if (describeFilmRespVO == null) return BaseResponseVO.success();

        return BaseResponseVO.success(describeFilmRespVO);
    }

    /**
     * 添加影片
     * @param filmSavedReqVO
     * @return
     * @throws CommonServiceException
     */
    @PostMapping("/film:add")
    public BaseResponseVO saveFilmInfo(@RequestBody FilmSavedReqVO filmSavedReqVO) throws CommonServiceException {

        filmService.saveFilm(filmSavedReqVO);

        return BaseResponseVO.success();
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
