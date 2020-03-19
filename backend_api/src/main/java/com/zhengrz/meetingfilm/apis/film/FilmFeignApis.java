package com.zhengrz.meetingfilm.apis.film;

import com.zhengrz.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.zhengrz.meetingfilm.utils.common.vo.BaseResponseVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface FilmFeignApis {
    @RequestMapping(value = "/films/{filmId}", method = RequestMethod.GET)
    BaseResponseVO<DescribeFilmRespVO> describeFilmById(@PathVariable("filmId") String filmId);
}
